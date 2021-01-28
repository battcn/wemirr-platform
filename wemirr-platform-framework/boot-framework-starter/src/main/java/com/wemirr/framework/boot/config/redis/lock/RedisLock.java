package com.wemirr.framework.boot.config.redis.lock;


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
    int expire() default 5;

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
}