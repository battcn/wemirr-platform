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

import com.alibaba.fastjson2.support.spring6.data.redis.GenericFastJsonRedisSerializer;
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
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
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
    @Order(value = -1)
    @ConditionalOnBean(RedisConnectionFactory.class)
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory, ObjectMapper objectMapper) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 设置连接工厂
        redisTemplate.setConnectionFactory(connectionFactory);
        // 字符串序列化器
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // 存入redis时序列化值的序列化器
        GenericFastJsonRedisSerializer valueSerializer = new GenericFastJsonRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setDefaultSerializer(valueSerializer);
        // 允许事务
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
    
}
