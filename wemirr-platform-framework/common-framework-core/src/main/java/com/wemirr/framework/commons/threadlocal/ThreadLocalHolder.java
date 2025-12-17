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

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 线程上下文数据持有器（支持跨线程池传递）
 * <p>基于 {@link TransmittableThreadLocal} 实现，支持在线程池场景下自动传递上下文数据</p>
 *
 * <h3>功能特性</h3>
 * <ul>
 *   <li>支持跨线程池传递上下文（配合TTL Agent或TtlRunnable使用）</li>
 *   <li>内部使用ConcurrentHashMap保证线程安全</li>
 *   <li>提供类型安全的取值方法</li>
 *   <li>支持懒加载和条件缓存</li>
 *   <li>支持快照和恢复机制</li>
 * </ul>
 *
 * <h3>使用示例</h3>
 * <pre>{@code
 * // 设置上下文数据
 * ThreadLocalHolder.setTenantId(1L);
 * ThreadLocalHolder.setUserId(100L);
 * ThreadLocalHolder.set("customKey", "customValue");
 *
 * // 获取上下文数据
 * Long tenantId = ThreadLocalHolder.getTenantId();
 * Long userId = ThreadLocalHolder.getUserId();
 * String value = ThreadLocalHolder.getString("customKey");
 *
 * // 带缓存的懒加载（只计算一次）
 * Object expensive = ThreadLocalHolder.get("expensive", () -> computeExpensiveValue());
 *
 * // 条件缓存（仅当结果非null时缓存）
 * Object result = ThreadLocalHolder.get("key", () -> mayReturnNull(), Objects::nonNull);
 *
 * // 快照与恢复（用于手动传递上下文）
 * Map<String, Object> snapshot = ThreadLocalHolder.snapshot();
 * executor.submit(() -> {
 *     ThreadLocalHolder.restore(snapshot);
 *     // 可以获取到主线程的上下文
 * });
 *
 * // 请求结束时清理（通常在Filter/Interceptor的afterCompletion中调用）
 * ThreadLocalHolder.clear();
 * }</pre>
 *
 * @author Levin
 * @since 1.0.0
 * @see TransmittableThreadLocal
 */
public final class ThreadLocalHolder {

    // ==================== 常量定义 ====================

    /**
     * 语言环境Key
     */
    public static final String KEY_LOCALE = "locale";

    /**
     * 请求追踪ID Key
     */
    public static final String KEY_TRACE_ID = "traceId";

    /**
     * 租户ID Key
     */
    public static final String KEY_TENANT_ID = "tenantId";

    /**
     * 用户ID Key
     */
    public static final String KEY_USER_ID = "userId";

    /**
     * 用户信息Key
     */
    public static final String KEY_USER_INFO = "userInfo";

    /**
     * 线程上下文存储容器
     * <p>使用TTL支持线程池传递，内部使用ConcurrentHashMap保证线程安全</p>
     */
    private static final TransmittableThreadLocal<Map<String, Object>> CONTEXT =
            TransmittableThreadLocal.withInitial(ConcurrentHashMap::new);

    /**
     * 私有构造函数，防止实例化
     */
    private ThreadLocalHolder() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    // ==================== 基础操作 ====================

    /**
     * 获取所有上下文数据
     */
    public static Map<String, Object> getAll() {
        return CONTEXT.get();
    }

    /**
     * 获取指定key的值
     */
    public static Object get(String key) {
        Map<String, Object> map = getAll();
        return map != null ? map.get(key) : null;
    }

    /**
     * 类型安全的获取值
     */
    @SuppressWarnings("unchecked")
    public static <T> T getAs(String key, Class<T> type) {
        Object value = get(key);
        if (value == null) {
            return null;
        }
        if (type.isInstance(value)) {
            return (T) value;
        }
        throw new ClassCastException("Value for key '" + key + "' is not of type " + type.getName());
    }

    /**
     * 获取值，带默认值
     */
    @SuppressWarnings("unchecked")
    public static <T> T getOrDefault(String key, T defaultValue) {
        Object value = get(key);
        return value != null ? (T) value : defaultValue;
    }

    /**
     * 设置值
     */
    public static void set(String key, Object val) {
        if (key == null) {
            return;
        }
        Map<String, Object> map = getAll();
        if (val == null) {
            map.remove(key);
        } else {
            map.put(key, val);
        }
    }

    /**
     * 批量设置
     */
    public static void setAll(Map<String, Object> values) {
        if (values != null && !values.isEmpty()) {
            getAll().putAll(values);
        }
    }

    /**
     * 移除指定key
     */
    public static void remove(String key) {
        Map<String, Object> map = getAll();
        if (map != null) {
            map.remove(key);
        }
    }

    /**
     * 清除所有上下文数据
     */
    public static void clear() {
        CONTEXT.remove();
    }

    /**
     * 判断是否包含key
     */
    public static boolean contains(String key) {
        Map<String, Object> map = getAll();
        return map != null && map.containsKey(key);
    }

    // ==================== 懒加载获取 ====================

    /**
     * 获取值，如果不存在则通过supplier生成并缓存
     */
    public static Object get(String key, Supplier<?> supplier) {
        Object existingValue = get(key);
        if (existingValue != null) {
            return existingValue;
        }
        Object newValue = supplier.get();
        if (newValue != null) {
            set(key, newValue);
        }
        return newValue;
    }

    /**
     * 类型安全的懒加载获取
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(String key, Supplier<T> supplier, Class<T> type) {
        Object existingValue = get(key);
        if (existingValue != null && type.isInstance(existingValue)) {
            return (T) existingValue;
        }
        T newValue = supplier.get();
        if (newValue != null) {
            set(key, newValue);
        }
        return newValue;
    }

    /**
     * 获取值，并根据条件决定是否缓存
     *
     * @param key       键
     * @param supplier  生成值的逻辑
     * @param condition 缓存条件（返回true则缓存）
     * @return 结果值
     */
    public static Object get(String key, Supplier<?> supplier, Predicate<Object> condition) {
        Object existingValue = get(key);
        if (existingValue != null) {
            return existingValue;
        }
        Object result = supplier.get();
        if (condition != null && condition.test(result)) {
            set(key, result);
        }
        return result;
    }

    // ==================== 类型化便捷方法 ====================

    /**
     * 获取Long类型值
     *
     * @param key 键
     * @return Long值，如果不存在或转换失败返回null
     */
    public static Long getLong(String key) {
        Object value = get(key);
        return switch (value) {
            case null -> null;
            case Long l -> l;
            case Number n -> n.longValue();
            default -> Long.parseLong(value.toString());
        };
    }

    /**
     * 获取Integer类型值
     *
     * @param key 键
     * @return Integer值，如果不存在或转换失败返回null
     */
    public static Integer getInteger(String key) {
        Object value = get(key);
        return switch (value) {
            case null -> null;
            case Integer i -> i;
            case Number n -> n.intValue();
            default -> Integer.parseInt(value.toString());
        };
    }

    /**
     * 获取String值
     */
    public static String getString(String key) {
        Object value = get(key);
        return value != null ? value.toString() : null;
    }

    /**
     * 获取Boolean类型值
     *
     * @param key 键
     * @return Boolean值，如果不存在返回null
     */
    public static Boolean getBoolean(String key) {
        Object value = get(key);
        return switch (value) {
            case null -> null;
            case Boolean b -> b;
            default -> Boolean.parseBoolean(value.toString());
        };
    }

    // ==================== 常用数据便捷方法 ====================

    /**
     * 获取语言环境
     *
     * @return 语言环境，默认返回系统默认Locale
     */
    public static Locale getLocale() {
        Object value = get(KEY_LOCALE);
        return value instanceof Locale locale ? locale : Locale.getDefault();
    }

    /**
     * 设置语言环境
     *
     * @param locale 语言环境
     */
    public static void setLocale(Locale locale) {
        set(KEY_LOCALE, locale);
    }

    /**
     * 获取请求追踪ID
     *
     * @return 追踪ID
     */
    public static String getTraceId() {
        return getString(KEY_TRACE_ID);
    }

    /**
     * 设置请求追踪ID
     *
     * @param traceId 追踪ID
     */
    public static void setTraceId(String traceId) {
        set(KEY_TRACE_ID, traceId);
    }

    /**
     * 获取当前租户ID
     *
     * @return 租户ID，如果未设置返回null
     */
    public static Long getTenantId() {
        return getLong(KEY_TENANT_ID);
    }

    /**
     * 设置当前租户ID
     *
     * @param tenantId 租户ID
     */
    public static void setTenantId(Long tenantId) {
        set(KEY_TENANT_ID, tenantId);
    }

    /**
     * 获取当前用户ID
     *
     * @return 用户ID，如果未设置返回null
     */
    public static Long getUserId() {
        return getLong(KEY_USER_ID);
    }

    /**
     * 设置当前用户ID
     *
     * @param userId 用户ID
     */
    public static void setUserId(Long userId) {
        set(KEY_USER_ID, userId);
    }

    // ==================== 快照与恢复 ====================

    /**
     * 创建当前上下文的快照（用于传递给子线程）
     */
    public static Map<String, Object> snapshot() {
        Map<String, Object> current = getAll();
        return current != null ? new HashMap<>(current) : new HashMap<>();
    }

    /**
     * 从快照恢复上下文
     */
    public static void restore(Map<String, Object> snapshot) {
        clear();
        if (snapshot != null && !snapshot.isEmpty()) {
            CONTEXT.set(new ConcurrentHashMap<>(snapshot));
        }
    }

    /**
     * 在指定上下文中执行任务
     */
    public static void runWith(Map<String, Object> context, Runnable runnable) {
        Map<String, Object> previous = snapshot();
        try {
            restore(context);
            runnable.run();
        } finally {
            restore(previous);
        }
    }

    /**
     * 在指定上下文中执行任务并返回结果
     */
    public static <T> T callWith(Map<String, Object> context, Supplier<T> supplier) {
        Map<String, Object> previous = snapshot();
        try {
            restore(context);
            return supplier.get();
        } finally {
            restore(previous);
        }
    }
}
