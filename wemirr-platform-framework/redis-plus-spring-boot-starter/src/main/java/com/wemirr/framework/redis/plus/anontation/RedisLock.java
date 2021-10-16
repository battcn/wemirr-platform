package com.wemirr.framework.redis.plus.anontation;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Levin
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RedisLock {

    /**
     * redis 锁key的前缀
     *
     * @return redis 锁key的前缀
     */
    String prefix() default "";

    /**
     * 过期秒数,默认为5秒
     *
     * @return 轮询锁的时间
     */
    int expire() default 30;

    /**
     * 等待时间
     *
     * @return long
     */
    long waitTime() default 0L;

    /**
     * 超时时间单位
     *
     * @return 秒
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * <p>Key的分隔符（默认 :）</p>
     * <p>生成的Key：N:SO1008:500</p>
     *
     * @return String
     */
    String delimiter() default ":";

    /**
     * 提示消息
     *
     * @return String
     */
    String message() default "请求过快,稍后再试";

    /**
     * 是否自动释放锁
     *
     * @return true | false
     */
    boolean unlock() default true;

    /**
     * 锁类型
     */
    LockType lockType() default LockType.REENTRANT_LOCK;


    /**
     * @author Levin
     */
    @Getter
    @AllArgsConstructor
    enum LockType {

        /**
         * 可重入锁
         */
        REENTRANT_LOCK(0, "可重入锁"),

        /**
         * 公平锁
         */
        FAIR_LOCK(1, "公平锁"),

        /**
         * 读锁
         */
        READ_LOCK(2, "读锁"),

        /**
         * 写锁
         */
        WRITE_LOCK(3, "写锁"),

        /**
         * 红锁
         */
        RED_LOCK(4, "红锁"),

        /**
         * 联锁
         */
        MULTI_LOCK(5, "联锁");

        private final int code;

        private final String name;
    }

}