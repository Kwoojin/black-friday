package org.example.DeliveryService.dto;

import lombok.Builder;
import org.example.DeliveryService.entity.UserAddressEntity;

@Builder
public record UserAddressDto(
    Long userAddressId,
    Long userId,
    String address,
    String alias
) {

    public static UserAddressDto from(UserAddressEntity entity) {
        return UserAddressDto.builder()
            .userAddressId(entity.getUserAddressId())
            .userId(entity.getUserId())
            .address(entity.getAddress())
            .alias(entity.getAlias())
            .build();
    }
}
