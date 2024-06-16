package org.example.CatalogService.mysql.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Objects;

@SuppressWarnings("JpaDataSourceORMInspection")
@Getter
@Table(name = "seller_product")
@Entity
public class SellerProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_product_id")
    private Long SellerProductId;

    @Column(name = "seller_id")
    private Long sellerId;

    protected SellerProductEntity() {}

    public SellerProductEntity(Long sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SellerProductEntity that)) {
            return false;
        }
        return this.getSellerProductId() != null && this.getSellerProductId().equals(that.getSellerProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSellerProductId());
    }


}
