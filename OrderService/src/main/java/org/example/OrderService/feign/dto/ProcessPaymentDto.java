package org.example.OrderService.feign.dto;

import lombok.Builder;

@Builder
public record ProcessPaymentDto(
    Long orderId,
    Long userId,
    Long amountKRW,
    Long paymentMethodId
) {
}
