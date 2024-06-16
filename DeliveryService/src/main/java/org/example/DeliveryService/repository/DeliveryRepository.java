package org.example.DeliveryService.repository;

import org.example.DeliveryService.entity.DeliveryEntity;
import org.example.DeliveryService.enums.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<DeliveryEntity, Long> {

    List<DeliveryEntity> findAllByOrderId(Long orderId);

    List<DeliveryEntity> findAllByStatus(DeliveryStatus status);

}
