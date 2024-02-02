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

package com.wemirr.framework.security.configuration.server.store;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Redis操作类
 *
 * @param <V> value的类型
 * @author Levin
 */
@RequiredArgsConstructor
public class RedisTokenStore<V> {
    
    private final RedisTemplate<String, V> redisTemplate;
    
    public void delete(List<String> keys) {
        if (ObjectUtils.isEmpty(keys)) {
            return;
        }
        redisTemplate.delete(keys);
    }
    
    /**
     * 根据key删除缓存
     *
     * @param keys 要删除的key，可变参数列表
     */
    public void delete(String... keys) {
        if (ObjectUtils.isEmpty(keys)) {
            return;
        }
        redisTemplate.delete(Arrays.asList(keys));
    }
    
    /**
     * 存入值
     *
     * @param key   缓存中的key
     * @param value 存入的value
     */
    public void set(String key, V value) {
        valueOperations().set(key, value);
    }
    
    /**
     * 根据key取值
     *
     * @param key 缓存中的key
     * @return 返回键值对应缓存
     */
    public V get(String key) {
        return valueOperations().get(key);
    }
    
    /**
     * 设置键值并设置过期时间
     *
     * @param key     键
     * @param value   值
     * @param timeout 过期时间
     * @param unit    过期时间的单位
     */
    public void set(String key, V value, long timeout, TimeUnit unit) {
        valueOperations().set(key, value, timeout, unit);
    }
    
    /**
     * 设置键值并设置过期时间（单位秒）
     *
     * @param key     键
     * @param value   值
     * @param timeout 过期时间,单位：秒
     */
    public void set(String key, V value, long timeout) {
        this.set(key, value, timeout, TimeUnit.SECONDS);
    }
    
    /**
     * value操作集
     *
     * @return ValueOperations
     */
    private ValueOperations<String, V> valueOperations() {
        redisTemplate.setValueSerializer(RedisSerializer.java());
        return redisTemplate.opsForValue();
    }
    
    public void setValueSerializer(RedisSerializer<Object> java) {
        redisTemplate.setValueSerializer(java);
    }
}
