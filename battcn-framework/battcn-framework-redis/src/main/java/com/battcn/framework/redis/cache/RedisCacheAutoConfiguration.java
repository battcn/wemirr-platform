package com.battcn.framework.redis.cache;

import com.battcn.framework.redis.cache.impl.CacheServiceImpl;
import com.battcn.framework.redis.constant.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * @author Levin
 * @since 2018/3/22 0022
 */
@Configuration
@EnableConfigurationProperties(value = RedisCacheProperties.class)
@AutoConfigureAfter(RedisAutoConfiguration.class)
@ConditionalOnProperty(
        prefix = "spring.redis.battcn.cache",
        name = "enabled",
        havingValue = "true"
)
public class RedisCacheAutoConfiguration {

    private final JedisConnectionFactory jedisConnectionFactory;
    private final RedisCacheProperties redisCacheProperties;

    @Autowired
    public RedisCacheAutoConfiguration(JedisConnectionFactory jedisConnectionFactory, RedisCacheProperties redisCacheProperties) {
        this.redisCacheProperties = redisCacheProperties;
        this.jedisConnectionFactory = jedisConnectionFactory;
    }

    @Bean(name = RedisConstant.CACHE_TEMPLATE_NAME)
    public RedisTemplate<String, Serializable> redisCacheTemplate(final JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        jedisConnectionFactory.setDatabase(redisCacheProperties.getDb());
        template.setConnectionFactory(jedisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

    @Bean(name = RedisConstant.CACHE_SERVICE)
    public CacheService cacheService() {
        return new CacheServiceImpl(redisCacheTemplate(jedisConnectionFactory));
    }

}
