package com.wemirr.framework.boot.config.redis.lock;

import com.wemirr.framework.boot.config.redis.properties.ExtendRedisProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import static com.wemirr.framework.boot.config.redis.lock.RedisLockAutoConfiguration.REDIS_LOCK;

/**
 * @author Levin
 */
@Configuration
@ConditionalOnClass(RedisAutoConfiguration.class)
@EnableConfigurationProperties(ExtendRedisProperties.class)
@ConditionalOnProperty(prefix = REDIS_LOCK, name = "enabled", havingValue = "true")
@Import(RedisLockInterceptor.class)
public class RedisLockAutoConfiguration {

    public static final String REDIS_LOCK = "extend.redis.lock";


    @Bean
    @Primary
    public RedisLockHelper redisLockHelper(RedisConnectionFactory redisConnectionFactory, StringRedisTemplate stringRedisTemplate) {
        return new RedisLockHelper(redisConnectionFactory, stringRedisTemplate);
    }


}
