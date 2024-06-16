package org.example.OrderService.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class StartOrderReq {
    private Long userId;
    private Long productId;
    private Long count;
}
