package com.battcn.framework.redis.limit;


/**
 * <p>redis 限流类型</p>
 *
 * @author Levin
 * @since 2018年2月02日 下午4:58:52
 */

public enum LimitType {

    /**
     * 自定义key
     *
     * @see LimitKeyGenerator
     */
    CUSTOMER,
    /**
     * 默认生成
     */
    DEFAULT,
    /**
     * 根据请求者IP
     */
    IP;
}
