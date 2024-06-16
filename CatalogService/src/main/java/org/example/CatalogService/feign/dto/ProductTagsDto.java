package org.example.CatalogService.feign.dto;

import lombok.Builder;

import java.util.List;

@Deprecated
@Builder
public record ProductTagsDto(
    Long productId,
    List<String> tags
) {

}
