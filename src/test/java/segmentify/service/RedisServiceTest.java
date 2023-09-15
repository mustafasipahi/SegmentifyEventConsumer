package segmentify.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import segmentify.configuration.properties.EventRedisProperties;
import segmentify.request.PageViewEventRequest;
import segmentify.request.ProductViewEventRequest;

import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static segmentify.constants.RedisCacheConstant.PAGE_VIEW_EVENT_CACHE_PREFIX;
import static segmentify.constants.RedisCacheConstant.PRODUCT_VIEW_EVENT_CACHE_PREFIX;

@ExtendWith(MockitoExtension.class)
class RedisServiceTest {

    @InjectMocks
    private RedisService redisService;
    @Mock
    private EventRedisProperties eventRedisProperties;
    @Mock
    private RedisTemplate<String, Object> redisTemplate;
    @Mock
    private ValueOperations<String, Object> opsForValue;

    private static final long CACHE_HOUR = 1;

    @BeforeEach
    void setUp() {
        when(redisTemplate.opsForValue()).thenReturn(opsForValue);
        when(eventRedisProperties.getEventCacheHour()).thenReturn(CACHE_HOUR);
    }

    @Test
    void shouldSaveProductViewEvent() {
        final ProductViewEventRequest productViewEventRequest = ProductViewEventRequest.builder()
                .apiKey(RandomStringUtils.randomNumeric(3))
                .build();
        redisService.saveProductViewEvent(productViewEventRequest);
        verify(opsForValue).set(
                PRODUCT_VIEW_EVENT_CACHE_PREFIX + productViewEventRequest.getApiKey(),
                productViewEventRequest.getProductViewEvent(),
                CACHE_HOUR,
                TimeUnit.HOURS
        );
    }

    @Test
    void shouldSavePageViewEvent() {
        final PageViewEventRequest pageViewEventRequest = PageViewEventRequest.builder()
                .apiKey(RandomStringUtils.randomNumeric(3))
                .build();

        redisService.savePageViewEvent(pageViewEventRequest);
        verify(opsForValue).set(
                PAGE_VIEW_EVENT_CACHE_PREFIX + pageViewEventRequest.getApiKey(),
                pageViewEventRequest.getPageViewEvent(),
                eventRedisProperties.getEventCacheHour(),
                TimeUnit.HOURS
        );
    }
}