package com.wemirr.framework.log.diff.core.annotation;

import com.wemirr.framework.log.diff.support.DiffLogConfigureSelector;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;

import java.lang.annotation.*;

/**
 * @author mzt.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(DiffLogConfigureSelector.class)
public @interface EnableDiffLog {

    String serviceName();

    /**
     * ！不要删掉，为 null 就不代理了哦
     * true 都使用 CGLIB 代理
     * false 目标对象实现了接口 – 使用JDK动态代理机制(代理所有实现了的接口) 目标对象没有接口(只有实现类) – 使用CGLIB代理机制
     *
     * @return 不强制 cglib
     */
    boolean proxyTargetClass() default false;

    /**
     * Indicate how caching advice should be applied. The default is
     * {@link AdviceMode#PROXY}.
     *
     * @return 代理方式
     * @see AdviceMode
     */
    AdviceMode mode() default AdviceMode.PROXY;

    /**
     * 记录日志日志与业务日志是否同一个事务
     *
     * @return 默认独立
     */
    boolean joinTransaction() default false;

    /**
     * Indicate the ordering of the execution of the transaction advisor
     * when multiple advices are applied at a specific joinpoint.
     * <p>The default is {@link Ordered#LOWEST_PRECEDENCE}.
     *
     * @return 事务 advisor 的优先级
     */
    int order() default Ordered.LOWEST_PRECEDENCE;
}
