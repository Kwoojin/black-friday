package org.example.CatalogService.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class RegisterProductReq {

    private Long sellerId;
    private String name;
    private String description;
    private Long price;
    private Long stockCount;
    private List<String> tags;
}
