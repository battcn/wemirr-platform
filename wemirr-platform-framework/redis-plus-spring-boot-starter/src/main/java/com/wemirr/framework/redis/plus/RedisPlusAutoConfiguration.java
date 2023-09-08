package com.wemirr.framework.redis.plus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wemirr.framework.redis.plus.interceptor.RedisLimitInterceptor;
import com.wemirr.framework.redis.plus.interceptor.RedisLockInterceptor;
import com.wemirr.framework.redis.plus.sequence.RedisSequenceHelper;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redisson限流器自动配置项
 *
 * @author Levin
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableConfigurationProperties(RedisPlusProperties.class)
@ConditionalOnProperty(prefix = "extend.redis", name = "enabled", havingValue = "true", matchIfMissing = true)
public class RedisPlusAutoConfiguration {

    @Bean
    @Primary
    public RedisKeyGenerator redisKeyGenerator() {
        return new DefaultRedisKeyGenerator();
    }

    @Bean
    @Primary
    public RedisSequenceHelper redisSequenceHelper(StringRedisTemplate stringRedisTemplate) {
        return new RedisSequenceHelper(stringRedisTemplate);
    }

    @Bean
    @ConditionalOnProperty(prefix = "extend.redis.limit", name = "enabled", havingValue = "true", matchIfMissing = true)
    @ConditionalOnSingleCandidate(RedissonClient.class)
    public RedisLimitHelper redisLimitHelper(RedissonClient redissonClient) {
        return new RedisLimitHelper(redissonClient);
    }

    @Bean
    @ConditionalOnProperty(prefix = "extend.redis.lock", name = "interceptor", havingValue = "true", matchIfMissing = true)
    @ConditionalOnSingleCandidate(RedissonClient.class)
    public RedisLockInterceptor redissonLockAspect(RedissonClient redissonClient, RedisKeyGenerator redisKeyGenerator) {
        return new RedisLockInterceptor(redissonClient, redisKeyGenerator);
    }

    @Bean
    @ConditionalOnBean(RedisLimitHelper.class)
    @ConditionalOnProperty(prefix = "extend.redis.limit.interceptor", name = "enabled", havingValue = "true", matchIfMissing = true)
    public RedisLimitInterceptor redisLimitInterceptor(RedisLimitHelper redisLimitHelper) {
        return new RedisLimitInterceptor(redisLimitHelper);
    }

    /**
     * 默认情况下使用
     *
     * @param connectionFactory redis链接工厂
     * @return RedisTemplate
     */
    @Bean
    @ConditionalOnBean(RedisConnectionFactory.class)
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory, ObjectMapper objectMapper) {
        // 字符串序列化器
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // 存入redis时序列化值的序列化器
//        FastJsonRedisSerializer<Object> valueSerializer = new FastJsonRedisSerializer<>(Object.class);
        // 存入redis时序列化值的序列化器
        Jackson2JsonRedisSerializer<Object> valueSerializer = new Jackson2JsonRedisSerializer<>(objectMapper, Object.class);
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 设置hash格式数据值的序列化器
        redisTemplate.setHashValueSerializer(valueSerializer);
        // 默认的Key序列化器为：JdkSerializationRedisSerializer
        redisTemplate.setKeySerializer(stringRedisSerializer);
        // 设置字符串序列化器
        redisTemplate.setStringSerializer(stringRedisSerializer);
        // 设置hash结构的key的序列化器
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        // 设置连接工厂
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }


}
