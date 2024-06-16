package org.example.PaymentService.dto;

import lombok.Builder;
import org.example.PaymentService.entity.PaymentMethodEntity;
import org.example.PaymentService.enums.PaymentMethodType;

@Builder
public record PaymentMethodDto(
    Long paymentMethodId,
    Long userId,
    PaymentMethodType paymentMethodType,
    String creditCardNumber
) {
    public static PaymentMethodDto from(PaymentMethodEntity entity) {
        return PaymentMethodDto.builder()
            .paymentMethodId(entity.getPaymentMethodId())
            .userId(entity.getUserId())
            .paymentMethodType(entity.getPaymentMethodType())
            .creditCardNumber(entity.getCreditCardNumber())
            .build();
    }
}
