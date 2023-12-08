package com.wemirr.framework.db.dynamic.annotation;


import com.baomidou.dynamic.datasource.annotation.DS;

import java.lang.annotation.*;

/**
 * 如果租户类型为 字段隔离 请注释
 * spring.datasource.dynamic.aop.enabled=false
 * 自定义动态切换
 * 上下文 -> header -> session -> expression
 *
 * @author Levin
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@DS("#custom.Tenant-Code")
public @interface TenantDS {

}
