package com.battcn.framework.redis.cache;

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
import org.springframework.data.redis.core.StringRedisTemplate;

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

    private final RedisCacheProperties redisCacheProperties;

    @Autowired
    public RedisCacheAutoConfiguration(RedisCacheProperties redisCacheProperties) {
        this.redisCacheProperties = redisCacheProperties;
    }

    @Bean(name = RedisConstant.CACHE_TEMPLATE_NAME)
    public RedisTemplate<String, String> lockRedisTemplate(final JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<String, String> temple = new StringRedisTemplate();
        jedisConnectionFactory.setDatabase(redisCacheProperties.getDb());
        temple.setConnectionFactory(jedisConnectionFactory);
        return temple;
    }

}
