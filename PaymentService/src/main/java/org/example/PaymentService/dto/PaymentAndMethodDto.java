package org.example.PaymentService.dto;

import lombok.Builder;
import org.example.PaymentService.entity.PaymentEntity;

@Builder
public record PaymentAndMethodDto(
    PaymentDto payment,
    PaymentMethodDto paymentMethod
) {

    public static PaymentAndMethodDto from(PaymentEntity entity) {
        return PaymentAndMethodDto.builder()
            .payment(PaymentDto.from(entity))
            .paymentMethod(PaymentMethodDto.from(entity.getPaymentMethod()))
            .build();
    }

}
