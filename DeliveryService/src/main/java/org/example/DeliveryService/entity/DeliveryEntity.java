package org.example.DeliveryService.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.example.DeliveryService.enums.DeliveryStatus;

import java.util.Objects;

@SuppressWarnings("JpaDataSourceORMInspection")
@Getter
@Table(
    name = "delivery",
    indexes = {@Index(name = "idx_delivery_order_id", columnList = "order_id")}
)
@Entity
public class DeliveryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long deliveryId;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_count")
    private Long productCount;

    @Column(name = "address")
    private String address;

    @Column(name = "reference_code")
    private Long referenceCode;

    @Column(name = "status")
    private DeliveryStatus status;

    protected DeliveryEntity() {}

    @Builder
    public DeliveryEntity(Long orderId, String productName, Long productCount, String address, Long referenceCode, DeliveryStatus status) {
        this.orderId = orderId;
        this.productName = productName;
        this.productCount = productCount;
        this.address = address;
        this.referenceCode = referenceCode;
        this.status = status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeliveryEntity that)) {
            return false;
        }
        return this.getDeliveryId() != null && this.getDeliveryId().equals(that.getDeliveryId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDeliveryId());
    }


}
