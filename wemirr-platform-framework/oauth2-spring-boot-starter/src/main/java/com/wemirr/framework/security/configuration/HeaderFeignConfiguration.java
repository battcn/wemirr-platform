package com.wemirr.framework.security.configuration;

import feign.Feign;
import feign.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Nullable;
import java.io.IOException;

/**
 * fegin 配置增强
 *
 * @author L.cm
 */
@Slf4j
@Configuration
@ConditionalOnClass(Feign.class)
@Import(HeaderFeignClientInterceptor.class)
public class HeaderFeignConfiguration {

    @Bean
    @LoadBalanced
    @ConditionalOnExpression("${security.oauth2.resource.loadBalanced:true}")
    public RestTemplate lbRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(@Nullable ClientHttpResponse response) throws IOException {
                if (response != null && response.getStatusCode() != HttpStatus.BAD_REQUEST) {
                    super.handleError(response);
                }
            }
        });
        return restTemplate;
    }


    @Primary
    @Bean
    public Logger.Level level() {
        return Logger.Level.FULL;
    }
}
