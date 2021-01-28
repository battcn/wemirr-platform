package com.wemirr.framework.security.client.annotation;


import java.lang.annotation.*;

/**
 * 跳过指定 resource-id 的认证操作
 * 加上该注解，接口地址将无法获得安全保护
 *
 * @author Levin
 * @since 2019-04-08
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface IgnoreAuthorize {


    /**
     * 是否加载到web ignore 中
     *
     * @return false, true
     */
    boolean web() default false;

}
