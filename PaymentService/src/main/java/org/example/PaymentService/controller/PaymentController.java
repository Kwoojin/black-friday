package org.example.PaymentService.controller;

import lombok.RequiredArgsConstructor;
import org.example.PaymentService.controller.dto.ProcessPaymentReq;
import org.example.PaymentService.controller.dto.RegisterPaymentMethodReq;
import org.example.PaymentService.dto.PaymentAndMethodDto;
import org.example.PaymentService.dto.PaymentMethodDto;
import org.example.PaymentService.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/payment")
@RestController
public class PaymentController {

    private final PaymentService paymentService;

    /**
     * 결제 수단 등록 
     */
    @PostMapping("/methods")
    public PaymentMethodDto registerPaymentMethod(@RequestBody RegisterPaymentMethodReq req) {
        return paymentService.registerPaymentMethod(
            req.getUserId(),
            req.getPaymentMethodType(),
            req.getCreditCardNumber()
        );
    }

    /**
     * 결제 진행
     */
    @PostMapping("/process-payment")
    public PaymentAndMethodDto processPayment(@RequestBody ProcessPaymentReq req) {
        return paymentService.processPayment(
            req.getUserId(),
            req.getOrderId(),
            req.getAmountKRW(),
            req.getPaymentMethodId()
        );
    }

    /**
     * 결제 수단 중 우선 순위 있는 것 
     */
    @GetMapping("/users/{userId}/first-method")
    public PaymentMethodDto getPaymentMethod(@PathVariable Long userId) {
        return paymentService.getPaymentMethodByUser(userId);
    }

    /**
     * 결제 내역 조회
     */
    @GetMapping("/payments/{paymentId}")
    public PaymentAndMethodDto getPayment(@PathVariable Long paymentId) {
        return paymentService.getPayment(paymentId);
    }

}
