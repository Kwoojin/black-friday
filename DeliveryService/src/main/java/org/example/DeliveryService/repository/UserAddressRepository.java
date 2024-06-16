package org.example.DeliveryService.repository;

import org.example.DeliveryService.entity.UserAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAddressRepository extends JpaRepository<UserAddressEntity, Long> {

    List<UserAddressEntity> findByUserId(Long userId);

}
