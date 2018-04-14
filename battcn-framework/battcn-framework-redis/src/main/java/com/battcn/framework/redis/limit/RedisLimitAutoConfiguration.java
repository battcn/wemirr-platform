package com.battcn.framework.redis.limit;

import com.battcn.framework.redis.CacheKeyGenerator;
import com.battcn.framework.redis.cache.RedisCacheAutoConfiguration;
import com.battcn.framework.redis.constant.RedisConstant;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * @author Levin
 * @since 2018/3/22 0022
 */
@Configuration
@EnableConfigurationProperties(value = RedisLimitProperties.class)
@AutoConfigureBefore(RedisCacheAutoConfiguration.class)
@Import(CacheLimitInterceptor.class)
public class RedisLimitAutoConfiguration {


    @Bean(name = RedisConstant.LIMIT_TEMPLATE_NAME)
    public RedisTemplate<String, Serializable> limitRedisTemplate(RedisConnectionFactory redisConnectionFactory, RedisLimitProperties redisLimitProperties) {
        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        //redisConnectionFactory.getConnection().select(redisLimitProperties.getDb());
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

    @Bean(name = RedisConstant.LIMIT_KEY_GENERATOR)
    public CacheKeyGenerator limitKeyGenerator() {
        return new LimitKeyGenerator();
    }

}
