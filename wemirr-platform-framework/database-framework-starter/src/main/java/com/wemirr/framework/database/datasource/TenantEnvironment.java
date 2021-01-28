package com.wemirr.framework.database.datasource;


/**
 * 多租户上下文
 *
 * @author Levin
 */
public interface TenantEnvironment {

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


}
