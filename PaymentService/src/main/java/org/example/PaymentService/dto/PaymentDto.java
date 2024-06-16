package org.example.PaymentService.dto;

import lombok.Builder;
import org.example.PaymentService.entity.PaymentEntity;
import org.example.PaymentService.enums.PaymentStatus;

@Builder
public record PaymentDto(
    Long paymentId,
    Long userId,
    Long orderId,
    Long amountKRW,
    PaymentStatus paymentStatus,
    Long referenceCode,
    Long paymentMethodId
) {
    public static PaymentDto from(PaymentEntity entity) {
        return PaymentDto.builder()
            .paymentId(entity.getPaymentId())
            .userId(entity.getUserId())
            .orderId(entity.getOrderId())
            .amountKRW(entity.getAmountKRW())
            .paymentStatus(entity.getPaymentStatus())
            .referenceCode(entity.getReferenceCode())
            .paymentMethodId(entity.getPaymentMethod().getPaymentMethodId())
            .build();
    }
}
