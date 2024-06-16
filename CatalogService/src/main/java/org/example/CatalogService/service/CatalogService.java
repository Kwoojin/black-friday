package org.example.CatalogService.service;

import blackfriday.protobuf.EdaMessage;
import lombok.RequiredArgsConstructor;
import org.example.CatalogService.cassandra.entity.ProductEntity;
import org.example.CatalogService.cassandra.repository.ProductRepository;
import org.example.CatalogService.dto.ProductDto;
import org.example.CatalogService.mysql.entity.SellerProductEntity;
import org.example.CatalogService.mysql.repository.SellerProductRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CatalogService {

    private final SellerProductRepository sellerProductRepository;

    private final ProductRepository productRepository;

//    private final SearchClient searchClient;

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    @Transactional
    public ProductDto registerProduct(
        Long sellerId,
        String name,
        String description,
        Long price,
        Long stockCount,
        List<String> tags
    ) {
        SellerProductEntity sellerProduct = new SellerProductEntity(sellerId);
        sellerProductRepository.save(sellerProduct);

        ProductEntity product = ProductEntity.builder()
            .productId(sellerProduct.getSellerProductId())
            .sellerId(sellerId)
            .name(name)
            .description(description)
            .price(price)
            .stockCount(stockCount)
            .tags(tags)
            .build();

//        ProductTagsDto addProductTags = ProductTagsDto.builder()
//            .tags(tags)
//            .productId(product.getProductId())
//            .build();
//        searchClient.addTagCache(addProductTags);

        EdaMessage.ProductTags message = EdaMessage.ProductTags.newBuilder()
            .setProductId(product.getProductId())
            .addAllTags(tags)
            .build();
        kafkaTemplate.send("product_tags_added", message.toByteArray());

        ProductEntity savedProduct = productRepository.save(product);
        return ProductDto.from(savedProduct);
    }

    @Transactional
    public void deleteProduct(Long productId) {
        Optional<ProductEntity> optionalProduct = productRepository.findById(productId);

        if(optionalProduct.isPresent()) {
            ProductEntity product = optionalProduct.get();
//            ProductTagsDto removeProductTags = ProductTagsDto.builder()
//                .tags(product.getTags())
//                .productId(product.getProductId())
//                .build();
//
//            searchClient.removeTagCache(removeProductTags);

            EdaMessage.ProductTags message = EdaMessage.ProductTags.newBuilder()
                    .setProductId(product.getProductId())
                    .addAllTags(product.getTags())
                    .build();
            kafkaTemplate.send("product_tags_removed", message.toByteArray());

        }

        productRepository.deleteById(productId);
        sellerProductRepository.deleteById(productId);
    }

    public List<ProductDto> getProductsBySellerId(Long sellerId) {
        List<SellerProductEntity> sellerProducts = sellerProductRepository.findBySellerId(sellerId);

        var products = new ArrayList<ProductEntity>();

        for(SellerProductEntity item: sellerProducts) {
            productRepository.findById(item.getSellerProductId())
                .ifPresent(products::add);
        }

        return products.stream()
            .map(ProductDto::from)
            .toList();
    }

    public ProductDto getProductById(Long productId) {
        return ProductDto.from(
            productRepository.findById(productId).orElseThrow(IllegalArgumentException::new)
        );
    }

    @Transactional
    public ProductDto decreaseStockCount(Long productId, Long decreaseCount) {
        ProductEntity product = productRepository.findById(productId)
            .orElseThrow(IllegalArgumentException::new);

        product.setStockCount(product.getStockCount() - decreaseCount);

        ProductEntity savedProduct = productRepository.save(product);

        return ProductDto.from(savedProduct);
    }

}
