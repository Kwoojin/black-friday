package org.example.OrderService.service;


import lombok.RequiredArgsConstructor;
import org.example.OrderService.dto.ProductOrderAndDeliveryAndPaymentDto;
import org.example.OrderService.dto.ProductOrderAndDeliveryAndPaymentMethodDto;
import org.example.OrderService.dto.ProductOrderDto;
import org.example.OrderService.entity.ProductOrderEntity;
import org.example.OrderService.enums.OrderStatus;
import org.example.OrderService.feign.CatalogClient;
import org.example.OrderService.feign.DeliveryClient;
import org.example.OrderService.feign.PaymentClient;
import org.example.OrderService.feign.dto.DecreaseStockCountDto;
import org.example.OrderService.feign.dto.ProcessDeliveryDto;
import org.example.OrderService.feign.dto.ProcessPaymentDto;
import org.example.OrderService.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final PaymentClient paymentClient;

    private final DeliveryClient deliveryClient;

    private final CatalogClient catalogClient;

    @Transactional
    public ProductOrderAndDeliveryAndPaymentMethodDto startOrder(Long userId, Long productId, Long count) {
        // 1. 상품 정보 조회
        Map<String, Object> product = catalogClient.getProduct(productId);

        // 2. 결제 수단 정보 조회
        Map<String, Object> paymentMethod = paymentClient.getPaymentMethod(userId);

        // 3. 배송지 정보 조회
        Map<String, Object> address = deliveryClient.getUserAddress(userId);

        // 4. 주문 정보 생성
        ProductOrderEntity order = ProductOrderEntity.builder()
            .userId(userId)
            .productId(productId)
            .count(count)
            .orderStatus(OrderStatus.INITIATED)
            .paymentId(null)
            .deliveryId(null)
            .build();
        orderRepository.save(order);

        return ProductOrderAndDeliveryAndPaymentMethodDto.builder()
            .productOrder(ProductOrderDto.from(order))
            .paymentMethod(paymentMethod)
            .delivery(address)
            .build();
    }

    @Transactional
    public ProductOrderDto finishOrder(Long orderId, Long paymentMethodId, Long addressId) {
        ProductOrderEntity order = orderRepository.findById(orderId).orElseThrow();

        // 1. 상품 정보 조회
        Map<String, Object> product = catalogClient.getProduct(order.getProductId());

        // 2. 결제
        ProcessPaymentDto processPaymentDto = ProcessPaymentDto.builder()
                .orderId(order.getProductOrderId())
                .userId(order.getUserId())
                .amountKRW(Long.parseLong(product.get("price").toString()) * order.getCount())
                .paymentMethodId(paymentMethodId)
                .build();
        Map<String, Object> payment = paymentClient.processPayment(processPaymentDto);

        // 3. 배송 요청
        Map<String, Object> address = deliveryClient.getAddress(addressId);

        ProcessDeliveryDto processDeliveryDto = ProcessDeliveryDto.builder()
            .orderId(order.getProductOrderId())
            .productName(product.get("name").toString())
            .productCount(order.getCount())
            .address(address.get("address").toString())
            .build();
        Map<String, Object> delivery = deliveryClient.processDelivery(processDeliveryDto);

        // 4. 상품 재고 감소
        DecreaseStockCountDto decreaseStockCountDto = new DecreaseStockCountDto(order.getCount());

        catalogClient.decreaseStockCount(order.getProductId(), decreaseStockCountDto);

        // 5. 주문 정보 업데이트
        order.update(
            OrderStatus.DELIVERY_REQUESTED,
            Long.parseLong(payment.get("id").toString()),
            Long.parseLong(delivery.get("id").toString())
        );

        return ProductOrderDto.from(orderRepository.save(order));
    }

    public List<ProductOrderDto> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId).stream()
            .map(ProductOrderDto::from)
            .toList();
    }

    public ProductOrderAndDeliveryAndPaymentDto getOrderDetail(Long orderId) {
        ProductOrderEntity order = orderRepository.findById(orderId).orElseThrow();

        Map<String, Object> paymentRes = paymentClient.getPayment(order.getPaymentId());
        Map<String, Object> deliveryRes = deliveryClient.getDelivery(order.getDeliveryId());

        return ProductOrderAndDeliveryAndPaymentDto.builder()
            .productOrder(ProductOrderDto.from(order))
            .payment(paymentRes)
            .delivery(deliveryRes)
            .build();
    }
}
