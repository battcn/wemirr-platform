package com.wemirr.framework.db.mybatisplus.intercept.data;

import java.lang.annotation.*;

/**
 * 数据权限（只适合用在部分controller.method 中）
 * 如果想禁用数据权限那么请直接指定
 * 目前来说注解的权重是最高的
 *
 * @author Levin
 * @since 2020-04-08
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataScope {

    /**
     * 数据权限字段，支持多字段组合
     * 指定  @ScopeColumn(alias = "u", name = "department_id")
     */
    DataColumn[] columns() default {};

    /**
     * 权限范围
     */
    DataScopeType type() default DataScopeType.ALL;

    /**
     * 忽略权限处理逻辑 true 是 false 否
     *
     * @return 是否忽略
     */
    boolean ignore() default false;

}
