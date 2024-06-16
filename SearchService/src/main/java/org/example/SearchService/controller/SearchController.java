package org.example.SearchService.controller;

import lombok.RequiredArgsConstructor;
import org.example.SearchService.controller.dto.ProductTagsDto;
import org.example.SearchService.service.SearchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/search")
@RestController
public class SearchController {

    private final SearchService searchService;

//    @PostMapping("/addTagCache")
//    public void addTagCache(@RequestBody ProductTagsDto dto) {
//        searchService.addTagCache(dto.getProductId(), dto.getTags());
//    }

//    @PostMapping("/removeTagCache")
//    public void removeTagCache(@RequestBody ProductTagsDto dto) {
//        searchService.removeTagCache(dto.getProductId(), dto.getTags());
//    }

    @GetMapping("/tags/{tag}/productIds")
    public List<Long> getTagProductIds(@PathVariable String tag) {
        return searchService.getProductIdsByTag(tag);
    }
}
