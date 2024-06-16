package org.example.SearchService.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ProductTagsDto {
    private Long productId;
    private List<String> tags;
}
