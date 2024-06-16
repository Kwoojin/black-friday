package org.example.CatalogService.feign.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ProductTagsDto(
    Long productId,
    List<String> tags
) {

}
