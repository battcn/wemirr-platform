package com.wemirr.framework.db.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 多租户策略
 *
 * @author Levin
 * @since 2019/11/20
 */
@Getter
@AllArgsConstructor
public enum MultiTenantStrategy {
    /**
     * 本地服务
     */
    LOCAL("本地服务"),
    /**
     * Feign远程调用
     */
    FEIGN("Feign远程调用"),
    ;
    /**
     * 描述信息
     */
    private final String description;
}
