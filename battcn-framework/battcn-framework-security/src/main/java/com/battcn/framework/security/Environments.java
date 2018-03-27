package com.battcn.framework.security;

import java.sql.Timestamp;

/**
 * ThreadLocal 包装类
 *
 * @author Levin
 */
public class Environments {

    private final static ThreadLocal<Authentication> AUTHENTICATED_THREAD_LOCAL = new InheritableThreadLocal<>();
    private final static ThreadLocal<Timestamp> NOW = new ThreadLocal<>();

    public static void set(Authentication member) {
        AUTHENTICATED_THREAD_LOCAL.set(member);
    }

    public static void clear() {
        AUTHENTICATED_THREAD_LOCAL.remove();
        NOW.remove();
    }

    public static Authentication get() {
        return AUTHENTICATED_THREAD_LOCAL.get();
    }

    public static void setNow() {
        NOW.set(new Timestamp(System.currentTimeMillis()));
    }

    public static Timestamp now() {
        if (NOW.get() == null) {
            setNow();
        }
        return NOW.get();
    }

    public static String getAuthId() {
        if (AUTHENTICATED_THREAD_LOCAL.get() != null) {
            return AUTHENTICATED_THREAD_LOCAL.get().getAuthId();
        }
        return null;
    }
}
