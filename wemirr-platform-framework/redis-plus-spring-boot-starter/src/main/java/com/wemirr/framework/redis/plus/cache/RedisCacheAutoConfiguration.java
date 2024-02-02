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

package com.wemirr.framework.redis.plus.cache;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson2.JSON;
import com.wemirr.framework.redis.plus.RedisPlusProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Levin
 * redis方法级缓存使用配置项
 * 全局开启时，才会加载该模块
 */
@Slf4j
@Configuration
@EnableCaching
@RequiredArgsConstructor
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableConfigurationProperties(RedisPlusProperties.class)
@ConditionalOnProperty(prefix = "extend.redis.cache", name = "enabled", havingValue = "true")
public class RedisCacheAutoConfiguration implements CachingConfigurer {
    
    private final RedisPlusProperties redisPlusProperties;
    
    /**
     * 自定义SpringCache缓存key
     */
    @Primary
    @Bean("keyGenerator")
    @ConditionalOnMissingBean(KeyGenerator.class)
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            // 保证key有序
            Map<String, Object> keyMap = new LinkedHashMap<>();
            // 放入target的名字
            keyMap.put("target", target.getClass().toGenericString());
            // 放入method的名字
            keyMap.put("method", method.getName());
            if (ArrayUtil.isNotEmpty(params)) {
                // 把所有参数放进去
                for (int i = 0; i < params.length; i++) {
                    keyMap.put("params-" + i, params[i]);
                }
            }
            final String jsonText = JSON.toJSONString(keyMap);
            // 使用MD5生成位移key
            return MD5.create().digestHex(jsonText);
        };
    }
    
    /**
     * 自定义缓存SimpleCacheManager
     */
    @Bean
    @Order(value = Ordered.HIGHEST_PRECEDENCE)
    @ConditionalOnBean({RedisTemplate.class, RedisConnectionFactory.class})
    public SimpleCacheManager simpleCacheManager(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory factory) {
        SimpleCacheManager simple = new SimpleCacheManager();
        final RedisPlusProperties.RedisCacheSwitch cache = redisPlusProperties.getCache();
        final List<RedisPlusProperties.RedisCacheItem> cacheItems = cache.getItems();
        if (CollectionUtil.isEmpty(cacheItems)) {
            return simple;
        }
        final long globalTimeOut = Optional.of(cache.getTimeout()).orElse(60 * 60 * 24L);
        // 祛除重复，过滤掉null
        List<RedisPlusProperties.RedisCacheItem> items = new ArrayList<>(new HashSet<>(cacheItems));
        Set<RedisCacheRepository> caches = items.stream().filter(Objects::nonNull)
                .filter(item -> StrUtil.isNotBlank(item.getName())).map(item -> RedisCacheRepository.builder()
                        .enabled(item.isEnabled()).name(item.getName())
                        // 配置默认超时时间
                        .timeout(Optional.of(item.getTimeout()).orElse(globalTimeOut))
                        .keyPrefix(cache.getPrefix())
                        .redisTemplate(redisTemplate).connectionFactory(factory).build())
                .collect(Collectors.toSet());
        simple.setCaches(caches);
        return simple;
    }
    
}
