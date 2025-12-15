package com.wemirr.framework.log.diff.core.annotation;

import java.lang.annotation.*;

/**
 * 差异日志注解容器，支持在同一方法上声明多个 @DiffLog 注解
 *
 * @author wulang
 * @see DiffLog
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface DiffLogs {

    /**
     * 获取所有差异日志注解
     *
     * @return DiffLog 注解数组
     */
    DiffLog[] value();
}
