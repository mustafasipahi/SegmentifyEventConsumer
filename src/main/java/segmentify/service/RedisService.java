package segmentify.service;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import segmentify.configuration.properties.EventRedisProperties;
import segmentify.request.PageViewEventRequest;
import segmentify.request.ProductViewEventRequest;

import java.util.concurrent.TimeUnit;

import static segmentify.constants.RedisCacheConstant.*;

@Service
@AllArgsConstructor
public class RedisService {

    private final EventRedisProperties eventRedisProperties;
    private final RedisTemplate<String, Object> redisTemplate;

    @Async
    public void saveProductViewEvent(ProductViewEventRequest request) {
        redisTemplate.opsForValue().set(
                PRODUCT_VIEW_EVENT_CACHE_PREFIX + request.getApiKey(),
                request.getProductViewEvent(),
                eventRedisProperties.getEventCacheHour(),
                TimeUnit.HOURS
        );
    }

    @Async
    public void savePageViewEvent(PageViewEventRequest request) {
        redisTemplate.opsForValue().set(
                PAGE_VIEW_EVENT_CACHE_PREFIX + request.getApiKey(),
                request.getPageViewEvent(),
                eventRedisProperties.getEventCacheHour(),
                TimeUnit.HOURS
        );
    }
}
