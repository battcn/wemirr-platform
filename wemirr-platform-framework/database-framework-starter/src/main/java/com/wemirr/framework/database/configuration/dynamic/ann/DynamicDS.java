package com.wemirr.framework.database.configuration.dynamic.ann;


import com.baomidou.dynamic.datasource.annotation.DS;

import java.lang.annotation.*;

/**
 * todo 从登陆上下问取感觉意义不大的样子
 * 自定义动态切换
 * 上下文 -> header -> session -> expression
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@DS("#custom.tenant_code")
public @interface DynamicDS {

}
