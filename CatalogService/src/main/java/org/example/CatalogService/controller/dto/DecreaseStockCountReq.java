package org.example.CatalogService.controller.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DecreaseStockCountReq {
    private Long decreaseCount;
}
