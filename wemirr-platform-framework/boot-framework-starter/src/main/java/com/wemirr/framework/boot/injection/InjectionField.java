package com.wemirr.framework.boot.injection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author battcn
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
public @interface InjectionField {

    /**
     * key
     *
     * @return key
     */
    String key() default "";

    String api() default "";

    Class<? extends Object> feign() default Object.class;

    Class<? extends Object> beanClass() default Object.class;

    String method() default "findByIds";

    int depth() default 3;
}
