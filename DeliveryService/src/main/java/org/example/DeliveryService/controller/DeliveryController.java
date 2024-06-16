package org.example.DeliveryService.controller;

import lombok.RequiredArgsConstructor;
import org.example.DeliveryService.controller.dto.RegisterAddressReq;
import org.example.DeliveryService.dto.DeliveryDto;
import org.example.DeliveryService.dto.UserAddressDto;
import org.example.DeliveryService.service.DeliveryService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping("/delivery/addresses")
    public UserAddressDto registerAddress(@RequestBody RegisterAddressReq dto) {
        return deliveryService.addUserAddress(
            dto.getUserId(),
            dto.getAddress(),
            dto.getAlias()
        );
    }

//    @PostMapping("/delivery/process-delivery")
//    public DeliveryDto processDelivery(@RequestBody ProcessDeliveryReq dto) {
//        return deliveryService.processDelivery(
//            dto.getOrderId(),
//            dto.getProductName(),
//            dto.getProductCount(),
//            dto.getAddress()
//        );
//    }

    @GetMapping("/delivery/deliveries/{deliveryId}")
    public DeliveryDto getDelivery(@PathVariable Long deliveryId) {
        return deliveryService.getDelivery(deliveryId);
    }

    @GetMapping("/delivery/address/{addressId}")
    public UserAddressDto getAddress(@PathVariable Long addressId) {
        return deliveryService.getAddress(addressId);
    }

    @GetMapping("/delivery/users/{userId}/first-address")
    public UserAddressDto getUserAddress(@PathVariable Long userId) {
        return deliveryService.getUserAddress(userId);
    }
}
