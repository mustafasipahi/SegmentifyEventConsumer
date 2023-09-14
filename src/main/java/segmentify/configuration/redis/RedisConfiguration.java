package segmentify.configuration.redis;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import segmentify.configuration.properties.EventRedisProperties;
import segmentify.configuration.properties.RedisProperties;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static segmentify.constants.RedisCacheConstant.EVENT_CACHE_PREFIX;

@Configuration
@EnableCaching
@AllArgsConstructor
public class RedisConfiguration {

    private final RedisProperties redisProperties;
    private final EventRedisProperties eventRedisProperties;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisConf = new RedisStandaloneConfiguration();
        redisConf.setHostName(redisProperties.getHost());
        redisConf.setPort(redisProperties.getPort());
        return new LettuceConnectionFactory(redisConf);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        return template;
    }

    @Bean
    public RedisCacheManager cacheManager() {
        return RedisCacheManager.builder(redisConnectionFactory())
            .withInitialCacheConfigurations(constructInitialCacheConfigurations())
            .transactionAware()
            .build();
    }

    private Map<String, RedisCacheConfiguration> constructInitialCacheConfigurations() {
        final Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();

        final RedisCacheConfiguration eventCache = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(eventRedisProperties.getEventCacheHour()))
            .disableCachingNullValues();

        redisCacheConfigurationMap.put(EVENT_CACHE_PREFIX, eventCache);

        return redisCacheConfigurationMap;
    }
}
