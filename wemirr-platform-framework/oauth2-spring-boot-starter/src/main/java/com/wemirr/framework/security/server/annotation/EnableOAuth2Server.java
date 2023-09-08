package com.wemirr.framework.security.server.annotation;

import com.wemirr.framework.security.server.AuthorizationServerConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 授权服务注解
 *
 * @author Levin
 * @since 2023-08-24
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({AuthorizationServerConfiguration.class})
public @interface EnableOAuth2Server {

}
