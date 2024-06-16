package org.example.DeliveryService.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProcessDeliveryReq {
    private Long orderId;
    private String productName;
    private Long productCount;
    private String address;
}
