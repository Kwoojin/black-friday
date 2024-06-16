package org.example.SearchService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchService {

    private final StringRedisTemplate stringRedisTemplate;

    public void addTagCache(Long productId, List<String> tags) {
        SetOperations<String, String> ops = stringRedisTemplate.opsForSet();

        tags.forEach(tag -> ops.add(tag, productId.toString()));
    }

    public void removeTagCache(Long productId, List<String> tags) {
        SetOperations<String, String> ops = stringRedisTemplate.opsForSet();

        tags.forEach(tag -> ops.remove(tag, productId.toString()));
    }

    public List<Long> getProductIdsByTag(String tag) {
        SetOperations<String, String> ops = stringRedisTemplate.opsForSet();

        var values = ops.members(tag);
        if(values != null) {
            return values.stream().map(Long::parseLong).toList();
        }

        return Collections.emptyList();
    }
}
