package com.battcn.framework.redis.sequence;

import com.battcn.framework.redis.cache.RedisCacheAutoConfiguration;
import com.battcn.framework.redis.constant.RedisConstant;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;


/**
 * <p>RedisSequenceAutoConfiguration</p>
 *
 * @author Levin
 * @since 2018/03/20
 */

@Configuration
@EnableConfigurationProperties(value = RedisSequenceProperties.class)
@AutoConfigureBefore(RedisCacheAutoConfiguration.class)
public class RedisSequenceAutoConfiguration {

    @Bean(name = RedisConstant.SEQUENCE_GENERATOR)
    public SequenceGenerator sequenceGenerator(JedisConnectionFactory jedisConnectionFactory, RedisSequenceProperties redisSequenceProperties) {
        jedisConnectionFactory.setDatabase(redisSequenceProperties.getDb());
        RedisTemplate<String, Serializable> redisCacheTemplate = new RedisTemplate<>();
        redisCacheTemplate.setKeySerializer(new StringRedisSerializer());
        redisCacheTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisCacheTemplate.setConnectionFactory(jedisConnectionFactory);
        SequenceGenerator sequenceGenerator = new SequenceGenerator();
        sequenceGenerator.setRedisCacheTemplate(redisCacheTemplate);
        sequenceGenerator.setRedisSequenceProperties(redisSequenceProperties);
        return sequenceGenerator;
    }
}
