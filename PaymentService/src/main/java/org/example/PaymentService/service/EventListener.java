package org.example.PaymentService.service;

import blackfriday.protobuf.EdaMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.PaymentService.dto.PaymentAndMethodDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class EventListener {

    private final PaymentService paymentService;

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    @KafkaListener(topics = "payment_request")
    public void consumePaymentRequest(byte[] message) throws Exception {
        EdaMessage.PaymentRequestV1 object = EdaMessage.PaymentRequestV1.parseFrom(message);
        log.info("[payment_request] consumed: {}", object);

        PaymentAndMethodDto payment = paymentService.processPayment(
            object.getUserId(),
            object.getOrderId(),
            object.getAmountKRW(),
            object.getPaymentMethodId()
        );

        // ExternalPaymentAdapter를 사용한 외부 연동도 EDA로 수행될 수 있음.
        // 하지만 여기서는 즉각 처리가 되었다고 가정하고 처리.

        EdaMessage.PaymentResultV1 paymentResultMessage = EdaMessage.PaymentResultV1.newBuilder()
            .setOrderId(payment.payment().orderId())
            .setPaymentId(payment.payment().paymentId())
            .setPaymentStatus(payment.payment().paymentStatus().toString())
            .build();

        kafkaTemplate.send("payment_result", paymentResultMessage.toByteArray());
    }
}
