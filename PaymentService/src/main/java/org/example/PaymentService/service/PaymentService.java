package org.example.PaymentService.service;

import lombok.RequiredArgsConstructor;
import org.example.PaymentService.dto.PaymentAndMethodDto;
import org.example.PaymentService.dto.PaymentDto;
import org.example.PaymentService.dto.PaymentMethodDto;
import org.example.PaymentService.entity.PaymentEntity;
import org.example.PaymentService.entity.PaymentMethodEntity;
import org.example.PaymentService.enums.PaymentMethodType;
import org.example.PaymentService.enums.PaymentStatus;
import org.example.PaymentService.pg.CreditCardPaymentAdapter;
import org.example.PaymentService.repository.PaymentMethodRepository;
import org.example.PaymentService.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final PaymentMethodRepository paymentMethodRepository;

    private final CreditCardPaymentAdapter creditCardPaymentAdapter;

    @Transactional
    public PaymentMethodDto registerPaymentMethod(
        Long userId,
        PaymentMethodType paymentMethodType,
        String creditCardNumber
    ) {
        PaymentMethodEntity paymentMethod = PaymentMethodEntity.builder()
                .userId(userId)
                .paymentMethodType(paymentMethodType)
                .creditCardNumber(creditCardNumber)
                .build();
        PaymentMethodEntity savedPaymentMethod = paymentMethodRepository.save(paymentMethod);
        return PaymentMethodDto.from(savedPaymentMethod);
    }

    @Transactional
    public PaymentAndMethodDto processPayment(
            Long userId,
            Long orderId,
            Long amountKRW,
            Long paymentMethodId
    ) {
        PaymentMethodEntity paymentMethod = paymentMethodRepository.findById(paymentMethodId)
            .orElseThrow(IllegalArgumentException::new);

        if(paymentMethod.getPaymentMethodType() != PaymentMethodType.CREDIT_CARD) {
            throw new IllegalArgumentException("Unsupported payment method type");
        }

        final Long refCode = creditCardPaymentAdapter.processCreditCardPayment(amountKRW, paymentMethod.getCreditCardNumber());

        PaymentEntity payment = PaymentEntity.builder()
            .userId(userId)
            .orderId(orderId)
            .amountKRW(amountKRW)
            .paymentStatus(PaymentStatus.COMPLETED)
            .referenceCode(refCode)
            .paymentMethod(paymentMethod)
            .build();
        PaymentEntity savedPayment = paymentRepository.save(payment);

        return PaymentAndMethodDto.from(savedPayment);
    }

    public PaymentMethodDto getPaymentMethodByUser(Long userId) {
        return PaymentMethodDto.from(
            paymentMethodRepository.findByUserId(userId)
                .stream()
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
        );
    }

    public PaymentAndMethodDto getPayment(Long paymentId) {
        return PaymentAndMethodDto.from(
            paymentRepository.findById(paymentId)
                .orElseThrow(IllegalArgumentException::new)
        );
    }


}
