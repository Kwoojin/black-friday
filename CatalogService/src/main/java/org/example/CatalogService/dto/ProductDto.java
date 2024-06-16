package org.example.CatalogService.dto;

import lombok.Builder;
import org.example.CatalogService.cassandra.entity.ProductEntity;

import java.util.List;

@Builder
public record ProductDto(
    Long productId,
    Long sellerId,
    String name,
    String description,
    Long price,
    Long stockCount,
    List<String> tags
) {
    public static ProductDto from(ProductEntity entity) {
        return ProductDto.builder()
            .productId(entity.getProductId())
            .sellerId(entity.getSellerId())
            .name(entity.getName())
            .description(entity.getDescription())
            .price(entity.getPrice())
            .stockCount(entity.getStockCount())
            .tags(entity.getTags())
            .build();
    }
}
