package org.example.PaymentService.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.PaymentService.enums.PaymentMethodType;

@Setter
@Getter
@NoArgsConstructor
public class RegisterPaymentMethodReq {
    private Long userId;
    private PaymentMethodType paymentMethodType;
    private String creditCardNumber;
}
