package com.wemirr.framework.commons.security;


/**
 * 认证上下文接口
 *
 * @author Levin
 */
public interface AuthenticationContext {

    /**
     * 租户编码
     *
     * @return 租户编码
     */
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

    /**
     * 数据权限
     *
     * @return 数据权限范围
     */
    DataPermission dataPermission();
}
