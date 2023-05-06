package com.wemirr.framework.security.client;

import cn.hutool.core.collection.CollUtil;
import com.wemirr.framework.security.client.properties.SecurityIgnoreProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * spring security 5 SecurityConfigurerAdapter 的替代品
 *
 * @author Levin
 * @since 2023-05-06
 */
@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ClientIgnoreSecurityConfiguration {

    private final SecurityIgnoreProperties securityIgnoreProperties;
    private final RequestMappingHandlerMapping requestMappingHandlerMapping;
    private static final List<String> DEFAULT_IGNORE_URLS = List.of("/actuator/**", "/error", "/v3/api-docs");

    @Bean
    public SecurityFilterChain initIgnoreFilterChain(HttpSecurity http) throws Exception {
        if (CollUtil.isNotEmpty(securityIgnoreProperties.getResourceUrls())) {
            securityIgnoreProperties.getResourceUrls().addAll(DEFAULT_IGNORE_URLS);
        }
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        final AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry = http.authorizeHttpRequests();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> handlerMethodEntry : handlerMethods.entrySet()) {
            RequestMappingInfo key = handlerMethodEntry.getKey();
            if (key == null || key.getPatternsCondition() == null) {
                continue;
            }
            HandlerMethod value = handlerMethodEntry.getValue();
//            IgnoreAuthorize annotation = value.getMethodAnnotation(IgnoreAuthorize.class);
            log.info("[key] - [{}] - [value] - [{}]", key, value);
            key.getPatternsCondition().getPatterns().forEach(url -> registry.requestMatchers(url).permitAll());
        }
        List<String> ignoreUrls = securityIgnoreProperties.getWebUrls();
        if (!CollectionUtils.isEmpty(ignoreUrls)) {
            String[] arr = ignoreUrls.toArray(new String[0]);
            registry.requestMatchers(arr).permitAll();
        }
        return registry.and().build();
    }
}
