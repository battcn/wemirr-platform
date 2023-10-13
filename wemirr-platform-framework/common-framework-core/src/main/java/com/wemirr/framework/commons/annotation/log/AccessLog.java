package com.wemirr.framework.commons.annotation.log;


import java.lang.annotation.*;

/**
 * 操作日志注解
 *
 * @author Levin
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessLog {

    /**
     * 描述
     *
     * @return {String}
     */
    String description();

    /**
     * 记录执行参数
     *
     * @return true | false
     */
    boolean request() default true;

    /**
     * 记录返回参数
     *
     * @return true | false
     */
    boolean response() default true;
}
