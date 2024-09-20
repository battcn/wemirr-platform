package com.wemirr.framework.commons.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.google.common.collect.Maps;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

/**
 * 用于储存常见的数据到线程上下文中
 *
 * @author Levin
 */
public class ThreadLocalHolder {

    public static final String K_LOCAL = "local";

    private static final TransmittableThreadLocal<Map<String, Object>> THREAD_LOCAL = new TransmittableThreadLocal<>();


    public static Map<String, Object> get() {
        return THREAD_LOCAL.get();
    }

    public static Locale getLocal() {
        return (Locale) THREAD_LOCAL.get().get(K_LOCAL);
    }

    public static void setLocal(Locale locale) {
        set(K_LOCAL, locale);
    }

    private static void set(String key, Object val) {
        Optional.ofNullable(THREAD_LOCAL.get()).orElseGet(Maps::newHashMap).put(key, val);
    }

    public static void clear() {
        THREAD_LOCAL.remove();
    }
}
