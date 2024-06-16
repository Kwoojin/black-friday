package org.example.CatalogService.controller;

import lombok.RequiredArgsConstructor;
import org.example.CatalogService.controller.dto.DecreaseStockCountReq;
import org.example.CatalogService.controller.dto.RegisterProductReq;
import org.example.CatalogService.dto.ProductDto;
import org.example.CatalogService.service.CatalogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CatalogController {

    private final CatalogService catalogService;

    @PostMapping("/catalog/products")
    public ProductDto registerProduct(@RequestBody RegisterProductReq dto) {
        return catalogService.registerProduct(
            dto.getSellerId(),
            dto.getName(),
            dto.getDescription(),
            dto.getPrice(),
            dto.getStockCount(),
            dto.getTags()
        );
    }

    @DeleteMapping("/catalog/products/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        catalogService.deleteProduct(productId);
    }

    @GetMapping("/catalog/products/{productId}")
    public ProductDto getProductById(@PathVariable Long productId) {
        return catalogService.getProductById(productId);
    }

    @GetMapping("/catalog/sellers/{sellerId}/products")
    public List<ProductDto> getProductsBySellerId(@PathVariable Long sellerId) {
        return catalogService.getProductsBySellerId(sellerId);
    }

    @PostMapping("/catalog/products/{productId}/decreaseStockCount")
    public ProductDto decreaseStockCount(@PathVariable Long productId, @RequestBody DecreaseStockCountReq dto) {
        return catalogService.decreaseStockCount(productId, dto.getDecreaseCount());
    }
}
