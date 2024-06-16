package org.example.OrderService.controller.dto;

import lombok.Builder;
import org.example.OrderService.dto.ProductOrderAndDeliveryAndPaymentMethodDto;

import java.util.Map;

@Builder
public record StartOrderRes(
    Long orderId,
    Map<String, Object> paymentMethod,
    Map<String, Object> address
) {

    public static StartOrderRes from(ProductOrderAndDeliveryAndPaymentMethodDto dto) {
        return StartOrderRes.builder()
            .orderId(dto.productOrder().productOrderId())
            .paymentMethod(dto.paymentMethod())
            .address(dto.delivery())
            .build();
    }
}
