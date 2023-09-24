package com.wemirr.framework.security.configuration.client.annotation;


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


}
