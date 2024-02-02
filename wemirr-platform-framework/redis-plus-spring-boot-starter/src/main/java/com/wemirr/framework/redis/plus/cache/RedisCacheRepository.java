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

import cn.hutool.core.util.StrUtil;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.lang.Nullable;

import java.io.*;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * @author Levin
 * 自定义配置redis缓存
 * 自定义生成key策略，目前分开了不同的缓存name区：
 * 如当前cacheName的名字是defalut：那么就增删改查时，都会生成的key带上前缀:projectName+"_fn_"+cacheName+"_"],类似于ehcache功能
 */
@Data
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("unchecked")
public class RedisCacheRepository implements Cache {
    
    /**
     * 某项缓存是否开启,默认开启
     */
    @Builder.Default
    private boolean enabled = true;
    /**
     * 缓存固定名称
     */
    private String name;
    
    /**
     * 缓存存活时间,默认24小时
     */
    @Builder.Default
    private long timeout = 60 * 60 * 24;
    
    /**
     * 单项最终缓存前缀
     */
    private String keyPrefix;
    
    private RedisTemplate<String, Object> redisTemplate;
    
    private RedisConnectionFactory connectionFactory;
    
    @Override
    @NonNull
    public String getName() {
        return this.name;
    }
    
    @Override
    @NonNull
    public Object getNativeCache() {
        final Cache cache = RedisCacheManager.create(connectionFactory).getCache(name);
        return Optional.ofNullable(cache).orElseThrow(() -> new IllegalArgumentException("cache must not be null!"));
    }
    
    /**
     * 获取缓存数据
     *
     * @param key key
     * @return null
     */
    @Override
    public ValueWrapper get(@NonNull Object key) {
        if (!enabled) {
            return null;
        }
        final String ukPrefix = getUkPrefix(key.toString());
        Object object = redisTemplate.execute((RedisCallback<Object>) connection -> {
            byte[] ukByteKey = ukPrefix.getBytes();
            byte[] value = connection.stringCommands().get(ukByteKey);
            return value == null ? null : toObject(value);
        });
        return (object != null ? new SimpleValueWrapper(object) : null);
    }
    
    /**
     * 方法结果存入缓存
     *
     * @param key   key
     * @param value value
     */
    @Override
    public void put(@NonNull Object key, Object value) {
        if (!enabled) {
            return;
        }
        final String ukPrefix = getUkPrefix(key.toString());
        final long liveTime = timeout;
        redisTemplate.execute((RedisCallback<Long>) connection -> {
            byte[] cacheKey = ukPrefix.getBytes();
            byte[] valueByte = toByteArray(value);
            connection.stringCommands().set(cacheKey, valueByte);
            if (liveTime > 0) {
                connection.keyCommands().expire(cacheKey, liveTime);
            }
            return 1L;
        });
    }
    
    /**
     * 清除
     */
    @Override
    public void evict(@Nullable Object key) {
        if (enabled && key != null) {
            redisTemplate.delete(getUkPrefix(key.toString()));
        }
    }
    
    /**
     * 清除的时候，只会清除缓存名称为name前缀的缓存
     */
    @Override
    public void clear() {
        if (!enabled) {
            log.warn("not enabled , stop clear......");
            return;
        }
        final Set<String> keys = redisTemplate.keys(getUkPrefix("*"));
        if (keys == null || keys.isEmpty()) {
            log.warn("keys is null,return......");
            return;
        }
        redisTemplate.delete(keys);
    }
    
    /**
     * 从缓存获取参数
     *
     * @param key  key
     * @param type type
     * @param <T>  T
     * @return <T>
     */
    @Override
    public <T> T get(@NonNull Object key, Class<T> type) {
        if (!enabled) {
            return null;
        }
        Object object = null;
        try {
            ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
            object = valueOperations.get(getUkPrefix(key.toString()));
        } catch (Exception e) {
            log.error("redis cache get object error key:{},type:{},error:{}", key, type, e.getLocalizedMessage());
        }
        return isEmpty(object) ? null : (T) object;
    }
    
    /**
     * 从缓存获取参数
     *
     * @param key         key
     * @param valueLoader valueLoader
     * @param <T>         T
     * @return <T>
     */
    @Override
    public <T> T get(@NonNull Object key, @NonNull Callable<T> valueLoader) {
        if (!enabled) {
            return null;
        }
        Object object = null;
        try {
            object = get(key, valueLoader.call().getClass());
        } catch (Exception e) {
            log.error("redis cache get object error key:{},valueLoader:{},error:{}", key, valueLoader, e.getLocalizedMessage());
        }
        return (T) object;
    }
    
    /**
     * 自动将指定值在缓存中指定的键是否已经设置
     */
    @Override
    public ValueWrapper putIfAbsent(@NonNull Object key, Object value) {
        if (!enabled) {
            return null;
        }
        ValueWrapper vw = get(key);
        if (isEmpty(vw)) {
            put(key, value);
            return null;
        } else {
            return vw;
        }
    }
    
    /**
     * 保证生成的key唯一前缀
     */
    private String getUkPrefix(String key) {
        return key.startsWith(keyPrefix + "_fn_" + name + "_") ? key : (keyPrefix + "_fn_" + name + "_" + key);
    }
    
    /**
     * 判断对象是否为空
     */
    private boolean isEmpty(Object obj) {
        return obj == null || StrUtil.isBlank(obj.toString());
    }
    
    /**
     * 对象转换字节流
     *
     * @param obj obj
     * @return bytes
     */
    private byte[] toByteArray(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            log.error("redis cache convent object to byteArray error object:{},error：", obj, ex);
        }
        return bytes;
    }
    
    /**
     * 字节流转换对象
     *
     * @param bytes bytes
     * @return obj
     */
    private Object toObject(byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException | ClassNotFoundException ex) {
            log.error("redis cache convent byteArray to object error bytes:{},error：", bytes, ex);
        }
        return obj;
    }
    
}