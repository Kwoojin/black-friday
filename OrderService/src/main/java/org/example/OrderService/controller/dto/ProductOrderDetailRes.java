package org.example.OrderService.controller.dto;

import lombok.Builder;
import org.example.OrderService.dto.ProductOrderAndDeliveryAndPaymentDto;
import org.example.OrderService.enums.OrderStatus;

@Builder
public record ProductOrderDetailRes(
    Long id,
    Long userId,
    Long productId,
    Long paymentId,
    Long deliveryId,
    OrderStatus orderStatus,
    String paymentStatus,
    String deliveryStatus
) {
    public static ProductOrderDetailRes from(ProductOrderAndDeliveryAndPaymentDto dto) {
        return ProductOrderDetailRes.builder()
            .id(dto.productOrder().productOrderId())
            .userId(dto.productOrder().userId())
            .productId(dto.productOrder().productId())
            .paymentId(dto.productOrder().paymentId())
            .deliveryId(dto.productOrder().deliveryId())
            .orderStatus(dto.productOrder().orderStatus())
            .paymentStatus(dto.payment().get("paymentStatus").toString())
            .deliveryStatus(dto.delivery().get("status").toString())
            .build();
    }
}
