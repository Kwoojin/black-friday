package org.example.CatalogService.cassandra.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;

@Getter
@Table
public class ProductEntity {

    @PrimaryKey
    private Long productId;

    @Column
    private Long sellerId;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Long price;

    @Column
    private Long stockCount;

    @Column
    private List<String> tags;

    @Builder
    public ProductEntity(Long productId, Long sellerId, String name, String description, Long price, Long stockCount, List<String> tags) {
        this.productId = productId;
        this.sellerId = sellerId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockCount = stockCount;
        this.tags = tags;
    }

    public void setStockCount(Long stockCount) {
        this.stockCount = stockCount;
    }
}
