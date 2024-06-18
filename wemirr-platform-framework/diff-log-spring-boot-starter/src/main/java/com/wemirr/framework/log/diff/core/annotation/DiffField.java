package com.wemirr.framework.log.diff.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Levin
 * 差异字段
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DiffField {

    /**
     * 标题
     *
     * @return 标题
     */
    String title();

    /**
     * 函数
     *
     * @return 函数
     */
    String function() default "";

}
