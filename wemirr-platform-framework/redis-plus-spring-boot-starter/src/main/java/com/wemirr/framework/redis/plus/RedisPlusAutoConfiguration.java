/*
 * Copyright (c) 2023 WEMIRR-PLATFORM Authors. All Rights Reserved.
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wemirr.framework.redis.plus;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.wemirr.framework.commons.JacksonUtils;
import com.wemirr.framework.redis.plus.interceptor.RedisLimitInterceptor;
import com.wemirr.framework.redis.plus.interceptor.RedisLockInterceptor;
import com.wemirr.framework.redis.plus.limit.DistributedRateLimiter;
import com.wemirr.framework.redis.plus.lock.DistributedLock;
import com.wemirr.framework.redis.plus.lock.RedisLockHelper;
import com.wemirr.framework.redis.plus.sequence.RedisSequenceHelper;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis Plus 自动配置
 * <p>提供分布式锁、限流等功能的自动配置</p>
 *
 * @author Levin
 */
@Slf4j
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableConfigurationProperties(RedisPlusProperties.class)
@ConditionalOnProperty(prefix = "extend.redis", name = "enabled", havingValue = "true", matchIfMissing = true)
public class RedisPlusAutoConfiguration {

    private final RedissonClient redissonClient;

    public RedisPlusAutoConfiguration(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
        // 初始化分布式锁和限流API
        DistributedLock.initialize(redissonClient);
        DistributedRateLimiter.initialize(redissonClient);
        log.info("Initialized DistributedLock and DistributedRateLimiter APIs");
    }

    @Bean
    @Primary
    public RedisKeyGenerator redisKeyGenerator() {
        return new DefaultRedisKeyGenerator();
    }

    @Bean
    @Primary
    public RedisLockHelper redisLockHelper(RedissonClient redissonClient) {
        return new RedisLockHelper(redissonClient);
    }

    @Bean
    @Primary
    public RedisSequenceHelper redisSequenceHelper(StringRedisTemplate stringRedisTemplate) {
        return new RedisSequenceHelper(stringRedisTemplate);
    }

    @Bean
    @ConditionalOnProperty(prefix = "extend.redis.lock", name = "interceptor", havingValue = "true", matchIfMissing = true)
    @ConditionalOnSingleCandidate(RedissonClient.class)
    public RedisLockInterceptor redissonLockAspect(RedissonClient redissonClient, RedisKeyGenerator redisKeyGenerator) {
        return new RedisLockInterceptor(redissonClient, redisKeyGenerator);
    }

    @Bean
    @ConditionalOnProperty(prefix = "extend.redis.limit.interceptor", name = "enabled", havingValue = "true", matchIfMissing = true)
    @ConditionalOnSingleCandidate(RedissonClient.class)
    public RedisLimitInterceptor redisLimitInterceptor(RedissonClient redissonClient) {
        return new RedisLimitInterceptor(redissonClient);
    }

    /**
     * 默认情况下使用
     *
     * @param connectionFactory redis链接工厂
     * @return RedisTemplate
     */
    @Bean
    @Order(value = -1)
    @ConditionalOnBean(RedisConnectionFactory.class)
    @Primary
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory, ObjectMapper objectMapper) {
        return createRedisTemplate(connectionFactory);
    }

    /**
     * 统一创建RedisTemplate
     *
     * @param connectionFactory
     * @return
     */
    public static RedisTemplate<String, Object> createRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 设置连接工厂
        redisTemplate.setConnectionFactory(connectionFactory);
        // 字符串序列化器
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        ObjectMapper objectMapper = JacksonUtils.getObjectMapper();
        // 解决查询缓存转换异常的问题
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 启用对泛型和多态的支持，自定义规则以支持Kotlin等Final类
        objectMapper.setDefaultTyping(new ObjectMapper.DefaultTypeResolverBuilder(ObjectMapper.DefaultTyping.NON_FINAL, LaissezFaireSubTypeValidator.instance) {
            @Override
            public boolean useForType(com.fasterxml.jackson.databind.JavaType t) {
                return !com.fasterxml.jackson.core.TreeNode.class.isAssignableFrom(t.getRawClass());
            }
        }.init(JsonTypeInfo.Id.CLASS, null).inclusion(JsonTypeInfo.As.PROPERTY));
        // 存入redis时序列化值的序列化器
        GenericJackson2JsonRedisSerializer valueSerializer = new GenericJackson2JsonRedisSerializer(objectMapper);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setDefaultSerializer(valueSerializer);
        // 允许事务
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}
