package org.example.OrderService.feign.dto;

import lombok.Builder;

@Builder
public record DecreaseStockCountDto(
    Long decreaseCount
) {
}
