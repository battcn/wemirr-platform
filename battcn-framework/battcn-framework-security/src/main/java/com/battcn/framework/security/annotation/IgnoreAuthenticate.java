package com.battcn.framework.security.annotation;

import java.lang.annotation.*;

/**
 * @author Levin
 * @since 2018-01-20
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface IgnoreAuthenticate {

    /**
     * 忽略登录校验的情况下，是否还需要获取登录信息。
     * <br>用于登录/未登录均可访问，但处理逻辑不同的接口,例如：商品详情接口
     *
     * @return 是否需要登录
     * @author fly
     */
    boolean needLoginInfo() default false;
}
