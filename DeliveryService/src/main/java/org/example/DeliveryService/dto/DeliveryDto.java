package org.example.DeliveryService.dto;

import lombok.Builder;
import org.example.DeliveryService.entity.DeliveryEntity;
import org.example.DeliveryService.enums.DeliveryStatus;

@Builder
public record DeliveryDto(
    Long deliveryId,
    Long orderId,
    String productName,
    Long productCount,
    String address,
    Long referenceCode,
    DeliveryStatus status
) {

    public static DeliveryDto from(DeliveryEntity entity) {
        return DeliveryDto.builder()
            .deliveryId(entity.getDeliveryId())
            .orderId(entity.getOrderId())
            .productName(entity.getProductName())
            .productCount(entity.getProductCount())
            .address(entity.getAddress())
            .referenceCode(entity.getReferenceCode())
            .status(entity.getStatus())
            .build();

    }

}
