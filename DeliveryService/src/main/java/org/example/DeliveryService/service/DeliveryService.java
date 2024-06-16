package org.example.DeliveryService.service;

import lombok.RequiredArgsConstructor;
import org.example.DeliveryService.dg.DeliveryAdapter;
import org.example.DeliveryService.dto.DeliveryDto;
import org.example.DeliveryService.dto.UserAddressDto;
import org.example.DeliveryService.entity.DeliveryEntity;
import org.example.DeliveryService.entity.UserAddressEntity;
import org.example.DeliveryService.enums.DeliveryStatus;
import org.example.DeliveryService.repository.DeliveryRepository;
import org.example.DeliveryService.repository.UserAddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class DeliveryService {

    private final UserAddressRepository userAddressRepository;

    private final DeliveryRepository deliveryRepository;

    private final DeliveryAdapter deliveryAdapter;

    @Transactional
    public UserAddressDto addUserAddress(
        final Long userId,
        final String address,
        final String alias
    ) {
        UserAddressEntity savedUserAddress = userAddressRepository.save(
            UserAddressEntity.builder()
                .userId(userId)
                .address(address)
                .alias(alias)
                .build()
        );

        return UserAddressDto.from(savedUserAddress);
    }

    @Transactional
    public DeliveryDto processDelivery(
        final Long orderId,
        final String productName,
        final Long productCount,
        final String address
    ) {
        final Long refCode = deliveryAdapter.processDelivery(productName, productCount, address);
        DeliveryEntity savedDelivery = deliveryRepository.save(
            DeliveryEntity.builder()
                .orderId(orderId)
                .productName(productName)
                .productCount(productCount)
                .address(address)
                .referenceCode(refCode)
                .status(DeliveryStatus.REQUESTED)
                .build()
        );

        return DeliveryDto.from(savedDelivery);
    }

    public DeliveryDto getDelivery(final Long deliveryId) {
        return DeliveryDto.from(
            deliveryRepository.findById(deliveryId).orElseThrow(IllegalStateException::new)
        );
    }

    public UserAddressDto getAddress(final Long addressId) {
        return UserAddressDto.from(
            userAddressRepository.findById(addressId).orElseThrow(IllegalStateException::new)
        );
    }

    public UserAddressDto getUserAddress(final Long userId) {
        return UserAddressDto.from(
            userAddressRepository.findByUserId(userId)
                .stream()
                .findFirst()
                .orElseThrow(IllegalStateException::new)
        );
    }
}
