package com.wemirr.framework.security.configuration;

import com.wemirr.framework.security.configuration.client.ResourceAuthExceptionEntryPoint;
import com.wemirr.framework.security.service.RedisOAuth2AuthorizationServiceImpl;
import feign.Feign;
import feign.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Nullable;
import java.io.IOException;

/**
 * @author Levin
 */
public class OAuth2AutoConfiguration {

    @Bean
    @Primary
    @LoadBalanced
    @ConditionalOnClass(Feign.class)
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


    @Bean
    @Primary
    @ConditionalOnClass(Feign.class)
    public Logger.Level level() {
        return Logger.Level.FULL;
    }

    @Bean
    @ConditionalOnExpression("'${extend.oauth2.server.registered-client}'.equalsIgnoreCase('jdbc')")
    public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcRegisteredClientRepository(jdbcTemplate);
    }


    /**
     * 配置基于db的授权确认管理服务
     *
     * @param jdbcTemplate               db数据源信息
     * @param registeredClientRepository 客户端repository
     * @return JdbcOAuth2AuthorizationConsentService
     */
    @Bean
    @ConditionalOnExpression("'${extend.oauth2.server.consent}'.equalsIgnoreCase('jdbc')")
    public OAuth2AuthorizationConsentService authorizationConsentService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        // 基于db的授权确认管理服务
        // 基于本地内存的服务实现 InMemoryOAuth2AuthorizationConsentService
        // 基于分布式内存服务实现 RedisOAuth2AuthorizationConsentService - [暂未实现]
        return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
    }

    /**
     * 配置基于db的oauth2的授权管理服务
     *
     * @param jdbcTemplate               db数据源信息
     * @param registeredClientRepository 上边注入的客户端repository
     * @return JdbcOAuth2AuthorizationService
     */
    @Bean
    @ConditionalOnExpression("'${extend.oauth2.authorization-type}'.equalsIgnoreCase('jdbc')")
    public OAuth2AuthorizationService authorizationService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
    }

    /**
     * 配置基于db的oauth2的授权管理服务
     *
     * @param redisTemplate redis数据源信息
     * @return JdbcOAuth2AuthorizationService
     */
    @Bean
    @ConditionalOnExpression("'${extend.oauth2.authorization-type}'.equalsIgnoreCase('redis')")
    public OAuth2AuthorizationService oAuth2AuthorizationService(RedisTemplate<String, Object> redisTemplate) {
        return new RedisOAuth2AuthorizationServiceImpl(redisTemplate);
    }


    @Bean
    public AuthenticationEntryPoint resourceAuthExceptionEntryPoint() {
        return new ResourceAuthExceptionEntryPoint();
    }

}
