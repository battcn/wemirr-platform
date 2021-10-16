package com.wemirr.framework.security.client;

import com.wemirr.framework.security.client.annotation.IgnoreAuthorize;
import com.wemirr.framework.security.client.properties.SecurityIgnoreProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;
import java.util.Map;

/**
 * @author Levin
 * @since 2019-03-30
 */
@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ClientIgnoreSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final SecurityIgnoreProperties securityIgnoreProperties;
    private final RequestMappingHandlerMapping requestMappingHandlerMapping;

    /**
     * 认证管理器（如果没有配置该 bean 那么则无法使用 password 类型的请求）
     *
     * @return AuthenticationManager
     * @throws Exception 异常
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> handlerMethodEntry : handlerMethods.entrySet()) {
            RequestMappingInfo key = handlerMethodEntry.getKey();
            HandlerMethod value = handlerMethodEntry.getValue();
            IgnoreAuthorize annotation = value.getMethodAnnotation(IgnoreAuthorize.class);
            log.info("[key] - [{}] - [value] - [{}]", key, value);
            if (annotation != null && annotation.web()) {
                key.getPatternsCondition().getPatterns().forEach(url -> web.ignoring().antMatchers(url));
            }
        }
        List<String> ignoreUrls = securityIgnoreProperties.getWebUrls();
        if (!CollectionUtils.isEmpty(ignoreUrls)) {
            String[] arr = ignoreUrls.toArray(new String[0]);
            web.ignoring().antMatchers(arr);
        }
    }
}
