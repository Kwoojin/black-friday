package org.example.DeliveryService.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RegisterAddressReq {
    private Long userId;
    private String address;
    private String alias;
}
