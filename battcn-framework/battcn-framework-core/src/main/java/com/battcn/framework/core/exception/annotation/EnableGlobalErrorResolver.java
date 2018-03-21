package com.battcn.framework.core.exception.annotation;

import com.battcn.framework.core.exception.ErrorResolverAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启全局异常处理
 *
 * @author Levin
 * @since 2018-01-15
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ErrorResolverAutoConfiguration.class})
public @interface EnableGlobalErrorResolver {


}
