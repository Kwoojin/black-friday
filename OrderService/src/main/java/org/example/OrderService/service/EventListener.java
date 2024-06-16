package org.example.OrderService.service;

import blackfriday.protobuf.EdaMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.OrderService.entity.ProductOrderEntity;
import org.example.OrderService.enums.OrderStatus;
import org.example.OrderService.feign.CatalogClient;
import org.example.OrderService.feign.dto.DecreaseStockCountDto;
import org.example.OrderService.repository.OrderRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Component
public class EventListener {

    private final OrderRepository orderRepository;

    private final CatalogClient catalogClient;

    private final KafkaTemplate<String, byte[]> kafkaTemplate;


    @KafkaListener(topics = "payment_result")
    public void consumePaymentResult(byte[] message) throws Exception {
        var object = EdaMessage.PaymentResultV1.parseFrom(message);
        log.info("[payment_result] consumed: {}", object);

        // 결제 정보 업데이트
        ProductOrderEntity order = orderRepository.findById(object.getOrderId()).orElseThrow();
        order.paymentResult(OrderStatus.DELIVERY_REQUESTED, object.getPaymentId());
        orderRepository.save(order);

        Map<String, Object> product = catalogClient.getProduct(order.getProductId());
        EdaMessage.DeliveryRequestV1 deliveryRequest = EdaMessage.DeliveryRequestV1.newBuilder()
            .setOrderId(order.getProductOrderId())
            .setProductName(product.get("name").toString())
            .setProductCount(order.getCount())
            .setAddress(order.getDeliveryAddress())
            .build();

        kafkaTemplate.send("delivery_request", deliveryRequest.toByteArray());
    }

    @KafkaListener(topics = "delivery_status_update")
    public void consumeDeliveryStatusUpdate(byte[] message) throws Exception {
        EdaMessage.DeliveryStatusUpdateV1 object = EdaMessage.DeliveryStatusUpdateV1.parseFrom(message);
        log.info("[delivery_status_update] consumed: {}", object);

        if(object.getDeliveryStatus().equals("REQUESTED")) {
            ProductOrderEntity order = orderRepository.findById(object.getOrderId()).orElseThrow();

            // deliveryId 저장
            order.deliveryStatusUpdate(object.getDeliveryId());
            orderRepository.save(order);

            // 상품 재고 감소
            DecreaseStockCountDto decreaseStockCountDto = new DecreaseStockCountDto(order.getCount());
            catalogClient.decreaseStockCount(order.getProductId(), decreaseStockCountDto);
        }
    }
}
