package org.example.OrderService.controller;


import lombok.RequiredArgsConstructor;
import org.example.OrderService.controller.dto.FinishOrderReq;
import org.example.OrderService.controller.dto.ProductOrderDetailRes;
import org.example.OrderService.controller.dto.StartOrderReq;
import org.example.OrderService.controller.dto.StartOrderRes;
import org.example.OrderService.dto.ProductOrderDto;
import org.example.OrderService.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    /**
     * 주문 요청 1
     */
    @PostMapping("/order/start-order")
    public StartOrderRes startOrder(@RequestBody StartOrderReq dto) {
        return StartOrderRes.from(
            orderService.startOrder(dto.getUserId(), dto.getProductId(), dto.getCount())
        );
    }

    /**
     * 주문 요청 2
     */
    @PostMapping("/order/finish-order")
    public ProductOrderDto finishOrder(@RequestBody FinishOrderReq dto) {
        return orderService.finishOrder(dto.getOrderId(), dto.getPaymentMethodId(), dto.getAddressId());
    }

    /**
     * 주문 목록
     */
    @GetMapping("/order/users/{userId}/orders")
    public List<ProductOrderDto> getUserOrders(@PathVariable Long userId) {
        return orderService.getUserOrders(userId);
    }

    /**
     * 주문 상세 정보
     */
    @GetMapping("/order/orders/{orderId}")
    public ProductOrderDetailRes getOrder(@PathVariable Long orderId) {
        return ProductOrderDetailRes.from(
            orderService.getOrderDetail(orderId)
        );
    }
}
