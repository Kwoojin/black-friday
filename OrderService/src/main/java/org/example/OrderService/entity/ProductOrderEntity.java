package org.example.OrderService.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.example.OrderService.enums.OrderStatus;

import java.util.Objects;

@SuppressWarnings("JpaDataSourceORMInspection")
@Getter
@Table(name = "product_order")
@Entity
public class ProductOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_order_id")
    private Long productOrderId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "product_id")
    private Long productId;  // 단일 상품에 대해서만 주문 가능하다고 가정

    @Column(name = "count")
    private Long count;

    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "delivery_id")
    private Long deliveryId;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    protected ProductOrderEntity() {}

    @Builder
    public ProductOrderEntity(Long userId, Long productId, Long count, OrderStatus orderStatus, Long paymentId, Long deliveryId, String deliveryAddress) {
        this.userId = userId;
        this.productId = productId;
        this.count = count;
        this.orderStatus = orderStatus;
        this.paymentId = paymentId;
        this.deliveryId = deliveryId;
        this.deliveryAddress = deliveryAddress;
    }

    public void deliveryStatusUpdate(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public void paymentRequested(OrderStatus orderStatus, String deliveryAddress) {
        this.orderStatus = orderStatus;
        this.deliveryAddress = deliveryAddress;
    }
    public void paymentResult(OrderStatus orderStatus, Long paymentId) {
        this.orderStatus = orderStatus;
        this.paymentId = paymentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductOrderEntity that)) {
            return false;
        }
        return this.getProductOrderId() != null && this.getProductOrderId().equals(that.getProductOrderId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductOrderId());
    }
}
