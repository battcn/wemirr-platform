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
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

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
    private final RedisConnectionFactory redisConnectionFactory;

    @Autowired
    public RedisLockAutoConfiguration(RedisConnectionFactory redisConnectionFactory, RedisLockProperties redisLockProperties) {
        this.redisLockProperties = redisLockProperties;
        this.redisConnectionFactory = redisConnectionFactory;
    }

    @Bean(name = RedisConstant.LOCK_TEMPLATE_NAME)
    public RedisTemplate<String, Object> lockRedisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        //redisConnectionFactory.getConnection().select(redisLockProperties.getDb());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean(name = RedisConstant.LOCK_KEY_GENERATOR)
    public CacheKeyGenerator lockKeyGenerator() {
        return new LockKeyGenerator();
    }

}
