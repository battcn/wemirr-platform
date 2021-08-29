package com.wemirr.platform.stp.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @author Levin
 */
@Configuration
@EnableConfigurationProperties(SaTokenExtProperties.class)
public class StpCloudOAuthConfig {

    // 相关参数配置
//    private String clientId = "1001";                                // 应用id
//    private String clientSecret = "aaaa-bbbb-cccc-dddd-eeee";        // 应用秘钥
//    private String serverUrl = "http://wemirr-platform-stp-server:7001";    // 服务端接口

    @Bean
    @LoadBalanced
    @ConditionalOnExpression("${sa-token.oauth2.enhance.loadBalanced:true}")
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
    public UserInfoTokenServices userInfoTokenServices(RestTemplate lbRestTemplate,
                                                       SaTokenExtProperties properties) {
        return new UserInfoTokenServices(lbRestTemplate, properties);
    }


}
