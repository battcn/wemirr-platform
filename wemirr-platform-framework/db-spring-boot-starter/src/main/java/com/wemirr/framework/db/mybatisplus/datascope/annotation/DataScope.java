package com.wemirr.framework.db.mybatisplus.datascope.annotation;

import java.lang.annotation.*;

/**
 * 数据权限注解，注解在 Mapper类 或者 对应方法上 用于提供该 mapper 对应表，所需控制的实体信息
 *
 * @author Levin
 * @version 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {

    /**
     * 当前类或方法是否忽略数据权限
     *
     * @return boolean 默认返回 false
     */
    boolean ignore() default false;

    /**
     * 数据权限字段，支持多字段组合
     * 指定  @ScopeColumn(alias = "u", name = "department_id")
     */
    DataColumn[] columns() default {};

}
