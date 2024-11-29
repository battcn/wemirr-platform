package com.wemirr.framework.commons.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.google.common.collect.Maps;

import java.util.Locale;
import java.util.Map;
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
}
