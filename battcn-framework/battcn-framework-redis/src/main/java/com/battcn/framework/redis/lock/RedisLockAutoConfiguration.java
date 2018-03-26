package com.battcn.framework.redis.lock;

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
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author Levin
 * @since 2018/3/22 0022
 */
@Configuration
@EnableConfigurationProperties(value = RedisLockProperties.class)
@ConditionalOnProperty(
        prefix = "spring.redis.battcn.lock",
        name = "enabled",
        havingValue = "true"
)
@AutoConfigureAfter(RedisAutoConfiguration.class)
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
    public RedisTemplate<String, String> lockRedisTemplate() {
        RedisTemplate<String, String> temple = new StringRedisTemplate();
        jedisConnectionFactory.setDatabase(redisLockProperties.getDb());
        temple.setConnectionFactory(jedisConnectionFactory);
        return temple;
    }

    @Bean(name = RedisConstant.LOCK_KEY_GENERATOR)
    public CacheKeyGenerator lockKeyGenerator() {
        return new LockKeyGenerator();
    }

}
