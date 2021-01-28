package com.wemirr.framework.database.mybatis.auth;

import java.lang.annotation.*;

/**
 * 数据权限（只适合用在部分controller.method 中）
 *
 * @author Levin
 * @since 2020-04-08
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Scope {

    /**
     * 限制范围为个人时的字段名称
     */

    String selfScopeName() default "created_by";

    /**
     * 权限范围
     */
    DataScopeType scopeType() default DataScopeType.SELF;


}
