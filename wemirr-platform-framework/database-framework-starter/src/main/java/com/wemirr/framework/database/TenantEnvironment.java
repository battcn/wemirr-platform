package com.wemirr.framework.database;


/**
 * 多租户上下文
 *
 * @author Levin
 */
public interface TenantEnvironment {

    default String tenantCode() {
        return null;
    }

    /**
     * 租户ID
     *
     * @return id
     */
    Long tenantId();

    /**
     * 用户ID
     *
     * @return id
     */
    Long userId();

    /**
     * 用户ID
     *
     * @return id
     */
    String realName();

    /**
     * 匿名用户
     *
     * @return 是否为匿名
     */
    boolean anonymous();


}
