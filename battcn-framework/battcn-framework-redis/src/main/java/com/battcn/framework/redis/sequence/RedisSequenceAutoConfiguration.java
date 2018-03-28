package com.battcn.framework.redis.sequence;

import com.battcn.framework.redis.cache.RedisCacheAutoConfiguration;
import com.battcn.framework.redis.constant.RedisConstant;
import org.springframework.beans.factory.annotation.Qualifier;
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


    @Bean(name = RedisConstant.SEQUENCE_TEMPLATE_NAME)
    public RedisTemplate<String, Serializable> lockRedisTemplate(JedisConnectionFactory jedisConnectionFactory, RedisSequenceProperties redisSequenceProperties) {
        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        jedisConnectionFactory.setDatabase(redisSequenceProperties.getDb());
        template.setConnectionFactory(jedisConnectionFactory);
        return template;
    }

    @Bean(name = RedisConstant.SEQUENCE_GENERATOR)
    public SequenceGenerator sequenceGenerator(@Qualifier(RedisConstant.SEQUENCE_TEMPLATE_NAME) RedisTemplate<String, Serializable> lockRedisTemplate, RedisSequenceProperties redisSequenceProperties) {
        SequenceGenerator sequenceGenerator = new SequenceGenerator();
        sequenceGenerator.setLockRedisTemplate(lockRedisTemplate);
        sequenceGenerator.setRedisSequenceProperties(redisSequenceProperties);
        return sequenceGenerator;
    }
}
