package com.wemirr.framework.redis.plus;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * key生成器
 *
 * @author Levin
 * @since 2021/09/10
 */
public interface RedisKeyGenerator {

    /**
     * 获取AOP参数,生成指定缓存Key
     *
     * @param prefix    key前缀
     * @param delimiter 分隔符
     * @return 缓存KEY
     */
    default String generate(String prefix, String delimiter) {
        throw new RuntimeException("请自行实现该接口方法");
    }

    /**
     * 获取AOP参数,生成指定缓存Key
     *
     * @param prefix    key前缀
     * @param delimiter 分隔符
     * @param pjp       PJP
     * @return 缓存KEY
     */
    default String generate(String prefix, String delimiter, ProceedingJoinPoint pjp) {
        return prefix;
    }
}