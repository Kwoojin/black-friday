package org.example.OrderService.repository;

import org.example.OrderService.entity.ProductOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<ProductOrderEntity, Long> {

    List<ProductOrderEntity> findByUserId(Long userId);

}
