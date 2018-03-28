package com.battcn.framework.redis.limit;

import com.battcn.framework.redis.CacheKeyGenerator;
import com.battcn.framework.redis.constant.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
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
@EnableConfigurationProperties(value = RedisLimitProperties.class)
@ConditionalOnProperty(
        prefix = "spring.redis.battcn.limit",
        name = "enabled",
        havingValue = "true"
)
@AutoConfigureAfter(RedisAutoConfiguration.class)
@Import(CacheLimitInterceptor.class)
public class RedisLimitAutoConfiguration {

    private final RedisLimitProperties redisLimitProperties;
    private final JedisConnectionFactory jedisConnectionFactory;

    @Autowired
    public RedisLimitAutoConfiguration(JedisConnectionFactory jedisConnectionFactory, RedisLimitProperties redisLimitProperties) {
        this.redisLimitProperties = redisLimitProperties;
        this.jedisConnectionFactory = jedisConnectionFactory;
    }

    @Bean(name = RedisConstant.LIMIT_TEMPLATE_NAME)
    public RedisTemplate<String, Serializable> limitRedisTemplate() {
        RedisTemplate<String, Serializable> temple = new RedisTemplate<>();
        temple.setKeySerializer(new StringRedisSerializer());
        temple.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        jedisConnectionFactory.setDatabase(redisLimitProperties.getDb());
        temple.setConnectionFactory(jedisConnectionFactory);
        return temple;
    }

    @Bean(name = RedisConstant.LIMIT_KEY_GENERATOR)
    public CacheKeyGenerator limitKeyGenerator() {
        return new LimitKeyGenerator();
    }

}
