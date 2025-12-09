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

package com.wemirr.framework.commons.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.google.common.collect.Maps;

import java.util.Locale;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 用于储存常见的数据到线程上下文中
 *
 * @author Levin
 */
public class ThreadLocalHolder {

    public static final String K_LOCAL = "local";

    private static final TransmittableThreadLocal<Map<String, Object>> THREAD_LOCAL = new TransmittableThreadLocal<>();

    public static Map<String, Object> getAll() {
        return THREAD_LOCAL.get();
    }

    public static Object get(String key) {
        Map<String, Object> map = getAll();
        if (map == null) {
            return null;
        }
        return map.get(key);
    }

    public static Object get(String key, Supplier<?> func) {
        Map<String, Object> map = getAll();
        if (map == null) {
            Object object = func.get();
            set(key, object);
            return object;
        }
        Object object = get(key);
        if (object == null) {
            Object node = func.get();
            set(key, node);
            return node;
        }
        return get(key);
    }

    public static Locale getLocal() {
        return (Locale) getAll().get(K_LOCAL);
    }

    public static void setLocal(Locale locale) {
        set(K_LOCAL, locale);
    }

    public static void set(String key, Object val) {
        Map<String, Object> map = getAll();
        if (map == null) {
            map = Maps.newHashMap();
            map.put(key, val);
            THREAD_LOCAL.set(map);
        } else {
            getAll().put(key, val);
        }
    }

    public static void clear() {
        THREAD_LOCAL.remove();
    }

    public static Long getLong(String key) {
        Object object = get(key);
        return object == null ? null : (Long) object;
    }

    /**
     * 获取值，并根据条件决定是否缓存到 ThreadLocal
     *
     * @param key            键
     * @param func           生成值的逻辑
     * @param condition 是否缓存的判断条件 (返回 true 则 set，返回 false 则不 set)
     * @return 结果值
     */
    public static Object get(String key, Supplier<?> func, Predicate<Object> condition) {
        // 1. 尝试从上下文中获取现有的值
        // 注意：利用现有的 get(key) 方法，它应该能处理 map 为 null 的情况返回 null
        Object existingValue = get(key);
        // 2. 如果已经缓存了值，直接返回
        if (existingValue != null) {
            return existingValue;
        }
        // 3. 如果没有缓存，执行业务逻辑获取新值
        Object result = func.get();
        // 4. 【核心修改】根据传入的条件判断是否需要 set 进去
        // 如果 condition 为 null，默认行为是必须缓存（或者你可以改为默认不缓存，看习惯）
        if (condition != null && condition.test(result)) {
            set(key, result);
        }
        // 5. 返回计算结果（无论是否缓存，都要返回当前次的结果）
        return result;
    }
}
