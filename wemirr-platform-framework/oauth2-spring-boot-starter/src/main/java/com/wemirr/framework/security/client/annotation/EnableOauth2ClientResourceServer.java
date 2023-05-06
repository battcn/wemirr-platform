package com.wemirr.framework.security.client.annotation;

import com.wemirr.framework.security.client.SecurityBeanDefinitionRegistrar;
import com.wemirr.framework.security.feign.HeaderFeignConfiguration;
import com.wemirr.framework.security.feign.SecurityInnerServiceAspect;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.lang.annotation.*;

/**
 * 资源服务注解
 *
 * @author Levin
 * @since 2019-04-03
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
//@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({SecurityBeanDefinitionRegistrar.class,
        HeaderFeignConfiguration.class,
        SecurityInnerServiceAspect.class})
public @interface EnableOauth2ClientResourceServer {

    /**
     * false 选用     UserInfoTokenServices
     * true  选用     RemoteTokenServices
     *
     * @return true | false
     */
    boolean preferTokenInfo() default false;

}
