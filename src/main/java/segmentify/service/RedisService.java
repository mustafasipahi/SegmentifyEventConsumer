package segmentify.service;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import segmentify.configuration.properties.EventRedisProperties;
import segmentify.request.EventRequest;

import java.util.concurrent.TimeUnit;

import static segmentify.constants.RedisCacheConstant.EVENT_CACHE_PREFIX;

@Service
@AllArgsConstructor
public class RedisService {

    private final EventRedisProperties eventRedisProperties;
    private final RedisTemplate<String, Object> redisTemplate;

    @Async
    public void save(EventRequest eventRequest) {
        redisTemplate.opsForValue().set(
                EVENT_CACHE_PREFIX + eventRequest.getApiKey(),
                eventRequest.getEventList(),
                eventRedisProperties.getEventCacheHour(),
                TimeUnit.HOURS
        );
    }
}
