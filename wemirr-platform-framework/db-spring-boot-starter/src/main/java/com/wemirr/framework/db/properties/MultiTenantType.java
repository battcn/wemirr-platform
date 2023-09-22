package com.wemirr.framework.db.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 多租户类型
 *
 * @author Levin
 * @since 2018/11/20
 */
@Getter
@AllArgsConstructor
public enum MultiTenantType {
    /**
     * 非租户模式
     */
    NONE("非租户模式"),
    /**
     * 字段模式 适合数据量不大（核心数据一年不足1000w 用它足够了）
     * 在sql中拼接 tenant_code 字段
     */
    COLUMN("字段模式"),

    /**
     * 暂时没想到这种模式有啥好处
     * schema 模式
     * sql 表名添加 schema 库名
     */
    @Deprecated
    SCHEMA("schema模式"),

    /**
     * 数据量大客户多可以考虑独立数据源
     * <p>
     * 独立数据源模式
     */
    DATASOURCE("独立数据源模式"),
    ;
    private final String description;

}
