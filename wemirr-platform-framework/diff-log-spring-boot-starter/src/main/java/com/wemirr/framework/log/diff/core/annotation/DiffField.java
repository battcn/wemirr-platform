package com.wemirr.framework.log.diff.core.annotation;

import com.wemirr.framework.log.diff.core.DiffFieldStrategy;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Levin
 * 对比差异字段注解
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DiffField {

    /**
     * 名字 等同  @PropertyName("xxx")
     *
     * @return 标题
     * @see org.javers.core.metamodel.annotation.PropertyName
     */
    String name();

    /**
     * 函数
     *
     * @return 函数
     */
    String function() default "";


    /**
     * 策略（默认不处理）
     *
     * @return 策略
     */
    DiffFieldStrategy strategy() default DiffFieldStrategy.ALWAYS;


}
