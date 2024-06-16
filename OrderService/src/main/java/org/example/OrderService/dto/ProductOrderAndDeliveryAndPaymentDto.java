package org.example.OrderService.dto;

import lombok.Builder;

import java.util.Map;

@Builder
public record ProductOrderAndDeliveryAndPaymentDto(
    ProductOrderDto productOrder,
    Map<String, Object> payment,
    Map<String, Object> delivery
) {

}
