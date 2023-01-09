package com.wemirr.framework.security.client;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @author Levin
 * @since 2019-04-03
 */
@RequiredArgsConstructor
@Lazy
public class LoadBalancedRestTemplateAutoConfigurer {

    @Bean
    @LoadBalanced
    @ConditionalOnExpression("${security.oauth2.resource.loadBalanced:true}")
    public RestTemplate lbRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() != HttpStatus.BAD_REQUEST.value()) {
                    super.handleError(response);
                }
            }
        });
        return restTemplate;
    }

    @Bean
    @LoadBalanced
    @ConditionalOnExpression("${security.oauth2.resource.loadBalanced:true}")
    public OAuth2RestOperations restTemplate(OAuth2ProtectedResourceDetails resource) {
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resource);
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() != HttpStatus.BAD_REQUEST.value()) {
                    super.handleError(response);
                }
            }
        });
        return restTemplate;
    }


    @Bean
    @ConditionalOnExpression("${security.oauth2.resource.loadBalanced:false}")
    public UserInfoTokenServices userInfoTokenServices(ResourceServerProperties sso, OAuth2RestOperations restTemplate) {
        UserInfoTokenServices services = new UserInfoTokenServices(
                sso.getUserInfoUri(), sso.getClientId());
        services.setRestTemplate(restTemplate);
        services.setTokenType(sso.getTokenType());
        return services;
    }
}
