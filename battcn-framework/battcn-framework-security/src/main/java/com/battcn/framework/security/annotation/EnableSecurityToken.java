package com.battcn.framework.security.annotation;

import com.battcn.framework.security.SecurityTokenAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启JWT Token
 *
 * @author Levin
 * @since 2018-01-15
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({SecurityTokenAutoConfiguration.class})
public @interface EnableSecurityToken {


}
