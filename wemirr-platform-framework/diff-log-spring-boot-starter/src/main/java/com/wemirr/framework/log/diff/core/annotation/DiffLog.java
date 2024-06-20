package com.wemirr.framework.log.diff.core.annotation;

import java.lang.annotation.*;

/**
 * @author muzhantong
 */
@Repeatable(DifLogs.class)
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface DiffLog {

    /**
     * 业务组
     *
     * @return 业务组
     */
    String group() default "";

    /**
     * @return 操作日志的类型，比如：订单类型、商品类型
     */
    String tag();

    /**
     * @return 日志绑定的业务标识
     */
    String businessKey();

    /**
     * @return 方法执行成功后的日志模版
     */
    String success();

    /**
     * @return 方法执行失败后的日志模版
     */
    String fail() default "";

    /**
     * @return 日志的额外信息
     */
    String extra() default "";

    /**
     * @return 是否记录日志
     */
    String condition() default "";

    /**
     * 记录成功日志的条件
     *
     * @return 表示成功的表达式，默认为空，代表不抛异常为成功
     */
    String successCondition() default "";
}
