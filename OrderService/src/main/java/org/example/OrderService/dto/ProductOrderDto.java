package org.example.OrderService.dto;

import lombok.Builder;
import org.example.OrderService.entity.ProductOrderEntity;
import org.example.OrderService.enums.OrderStatus;

@Builder
public record ProductOrderDto(
    Long productOrderId,
    Long userId,
    Long productId,
    Long count,
    OrderStatus orderStatus,
    Long paymentId,
    Long deliveryId
) {
    public static ProductOrderDto from(ProductOrderEntity entity) {
        return ProductOrderDto.builder()
            .productOrderId(entity.getProductOrderId())
            .userId(entity.getUserId())
            .productId(entity.getProductId())
            .count(entity.getCount())
            .orderStatus(entity.getOrderStatus())
            .paymentId(entity.getPaymentId())
            .deliveryId(entity.getDeliveryId())
            .build();
    }
}
