package com.wemirr.framework.database.properties;

import lombok.Getter;

/**
 * 多租户类型
 *
 * @author Levin
 * @since 2018/11/20
 */
@Getter
public enum MultiTenantType {
    /**
     * 非租户模式
     */
    NONE("非租户模式"),
    /**
     * 字段模式
     * 在sql中拼接 tenant_code 字段
     */
    COLUMN("字段模式"),
    /**
     * 独立数据源模式
     */
    DATASOURCE("独立数据源模式"),
    ;
    String description;


    MultiTenantType(String description) {
        this.description = description;
    }

    public boolean eq(String val) {
        return this.name().equalsIgnoreCase(val);
    }

    public boolean eq(MultiTenantType val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }
}
