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
     */
    CUSTOMER,
    /**
     * 根据当前登录用户给方法加锁，key生成规则{MethodName}:{token}
     */
    TOKEN,
    /**
     * 根据请求者IP
     */
    IP;
}
