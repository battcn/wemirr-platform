package com.wemirr.framework.redis.plus.anontation;

import org.redisson.api.RateType;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Levin
 * redisson限流器，注解配置项
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisLimit {

    /**
     * redis 锁key的前缀
     *
     * @return redis 锁key的前缀
     */
    String prefix() default "";

    /**
     * 放行数量，默认50个
     */
    long limit() default 50L;

    /**
     * 限流时间间隔数量,默认1秒
     */
    long timeout() default 1L;

    /**
     * 重试次数，默认0不重试
     */
    long retryTime() default 0L;

    /**
     * 时间单位（获取锁等待时间和持锁时间都用此单位）
     */
    TimeUnit unit() default TimeUnit.SECONDS;

    /**
     * 限流类型，默认总机限流
     */
    RateType type() default RateType.OVERALL;

    /**
     * 限流是否到参数级别
     */
    boolean useArgs() default false;
}