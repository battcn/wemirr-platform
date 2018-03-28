package com.battcn.framework.redis.lock;

import com.battcn.framework.redis.CacheKeyGenerator;
import com.battcn.framework.redis.cache.RedisCacheAutoConfiguration;
import com.battcn.framework.redis.constant.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author Levin
 * @since 2018/3/22 0022
 */
@Configuration
@EnableConfigurationProperties(value = RedisLockProperties.class)
@AutoConfigureBefore(RedisCacheAutoConfiguration.class)
@Import(CacheLockInterceptor.class)
public class RedisLockAutoConfiguration {

    private final RedisLockProperties redisLockProperties;
    private final JedisConnectionFactory jedisConnectionFactory;

    @Autowired
    public RedisLockAutoConfiguration(JedisConnectionFactory jedisConnectionFactory, RedisLockProperties redisLockProperties) {
        this.redisLockProperties = redisLockProperties;
        this.jedisConnectionFactory = jedisConnectionFactory;
    }

    @Bean(name = RedisConstant.LOCK_TEMPLATE_NAME)
    public RedisTemplate<String, Object> lockRedisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        jedisConnectionFactory.setDatabase(redisLockProperties.getDb());
        template.setConnectionFactory(jedisConnectionFactory);
        return template;
    }

    @Bean(name = RedisConstant.LOCK_KEY_GENERATOR)
    public CacheKeyGenerator lockKeyGenerator() {
        return new LockKeyGenerator();
    }

}
