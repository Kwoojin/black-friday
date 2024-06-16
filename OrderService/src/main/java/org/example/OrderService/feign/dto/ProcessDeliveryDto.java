package org.example.OrderService.feign.dto;

import lombok.Builder;

@Builder
public record ProcessDeliveryDto(
    Long orderId,
    String productName,
    Long productCount,
    String address
) {

}
