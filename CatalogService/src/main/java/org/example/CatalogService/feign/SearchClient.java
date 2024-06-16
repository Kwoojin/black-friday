package org.example.CatalogService.feign;

import org.example.CatalogService.feign.dto.ProductTagsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Deprecated
//@FeignClient(name = "searchClient", url = "http://localhost:8084")
@FeignClient(name = "searchClient", url = "http://search-service:8080")
public interface SearchClient {

    @PostMapping(value = "/search/addTagCache")
    void addTagCache(@RequestBody ProductTagsDto dto);

    @PostMapping(value = "/search/removeTagCache")
    void removeTagCache(@RequestBody ProductTagsDto dto);

}
