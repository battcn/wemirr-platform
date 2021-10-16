package com.wemirr.framework.database.properties;

import lombok.Getter;

/**
 * 多租户策略
 *
 * @author Levin
 * @since 2019/11/20
 */
@Getter
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
    String description;


    MultiTenantStrategy(String description) {
        this.description = description;
    }

    public boolean eq(String val) {
        return this.name().equalsIgnoreCase(val);
    }

    public boolean eq(MultiTenantStrategy val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }
}
