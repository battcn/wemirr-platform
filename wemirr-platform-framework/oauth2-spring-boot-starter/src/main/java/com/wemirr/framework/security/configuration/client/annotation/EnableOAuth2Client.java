package com.wemirr.framework.security.configuration.client.annotation;

import com.wemirr.framework.security.configuration.SecurityInnerServiceAspect;
import com.wemirr.framework.security.configuration.client.ClientResourceServerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.lang.annotation.*;

/**
 * 资源服务注解
 *
 * @author Levin
 * @since 2023-08-24
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Import({ClientResourceServerConfiguration.class, SecurityInnerServiceAspect.class})
public @interface EnableOAuth2Client {

}
