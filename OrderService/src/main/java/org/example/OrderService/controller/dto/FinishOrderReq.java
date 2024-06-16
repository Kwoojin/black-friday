package org.example.OrderService.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class FinishOrderReq {
    private Long orderId;
    private Long paymentMethodId;
    private Long addressId;
}
