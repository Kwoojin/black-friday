package org.example.OrderService.dto;

import lombok.Builder;

import java.util.Map;

@Builder
public record ProductOrderAndDeliveryAndPaymentMethodDto(
    ProductOrderDto productOrder,
    Map<String, Object> paymentMethod,
    Map<String, Object> delivery
) {

}
