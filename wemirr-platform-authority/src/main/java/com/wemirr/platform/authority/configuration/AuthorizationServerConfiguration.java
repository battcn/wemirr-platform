package com.wemirr.platform.authority.configuration;

import com.google.common.collect.Maps;
import com.wemirr.framework.security.client.entity.UserInfoDetails;
import com.wemirr.framework.security.client.exception.CustomWebResponseExceptionTranslator;
import com.wemirr.platform.authority.configuration.integration.IntegrationAuthenticationFilter;
import com.wemirr.platform.authority.configuration.properties.OAuth2Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.util.Map;

/**
 * @author Levin
 */
@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
@EnableConfigurationProperties(OAuth2Properties.class)
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private final OAuth2Properties properties;
    private final TokenStore tokenStore;
    private final CustomWebResponseExceptionTranslator customWebResponseExceptionTranslator;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final ClientDetailsService clientDetailsService;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final IntegrationAuthenticationFilter integrationAuthenticationFilter;


    @Bean
    public FilterRegistrationBean<IntegrationAuthenticationFilter> registration(IntegrationAuthenticationFilter integrationAuthenticationFilter) {
        // 防止 IntegrationAuthenticationFilter 被 Servlet 容器注册，因为Spring Security 也维护了一个 FilterChain
        FilterRegistrationBean<IntegrationAuthenticationFilter> registration = new FilterRegistrationBean<>(integrationAuthenticationFilter);
        registration.setEnabled(false);
        return registration;
    }

    @Bean
    public DefaultTokenServices tokenService() {
        //  配置TokenServices参数
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);
        // 是否支持刷新Token
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setReuseRefreshToken(true);
        tokenServices.setClientDetailsService(clientDetailsService);
        // 设置accessToken和refreshToken的默认超时时间(如果clientDetails的为null就取默认的，如果clientDetails的不为null取clientDetails中的)
        tokenServices.setAccessTokenValiditySeconds((int) properties.getAccessTokenValidityTimeToLive().getSeconds());
        tokenServices.setRefreshTokenValiditySeconds((int) properties.getRefreshTokenValidityTimeToLive().getSeconds());
        return tokenServices;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {

        endpoints.tokenStore(tokenStore)
                .tokenEnhancer(tokenEnhancer())
                .userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.POST, HttpMethod.GET)
                .exceptionTranslator(customWebResponseExceptionTranslator)
                .reuseRefreshTokens(true);

//        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
//                .exceptionTranslator(customWebResponseExceptionTranslator)
//                .tokenStore(tokenStore).tokenEnhancer(tokenEnhancer())
//                .reuseRefreshTokens(true)
//                .tokenServices(tokenService())
//                .userDetailsService(userDetailsService)
//                .authenticationManager(authenticationManager)
        // .setClientDetailsService(clientDetailsService)
        ;
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        // 允许表单认证 设置异常处理
        security.allowFormAuthenticationForClients()
                .authenticationEntryPoint(authenticationEntryPoint)
                .tokenKeyAccess("isAuthenticated()")
                .checkTokenAccess("permitAll()");
        // 设置登录校验过滤器
        security.addTokenEndpointAuthenticationFilter(integrationAuthenticationFilter);
    }

    /**
     * 客户端模式
     */
    private static final String CLIENT_CREDENTIALS = "client_credentials";

    /**
     * token增强，客户端模式不增强。
     *
     * @return TokenEnhancer
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            if (CLIENT_CREDENTIALS.equals(authentication.getOAuth2Request().getGrantType())) {
                return accessToken;
            }
            final Map<String, Object> additionalInfo = Maps.newLinkedHashMap();
            UserInfoDetails authInfo = (UserInfoDetails) authentication.getUserAuthentication().getPrincipal();
            additionalInfo.put("userId", authInfo.getUserId());
            additionalInfo.put("username", authInfo.getUsername());
            additionalInfo.put("nickName", authInfo.getNickName());
            additionalInfo.put("tenantId", authInfo.getTenantId());
            additionalInfo.put("avatar", authInfo.getAvatar());
            additionalInfo.put("sex", authInfo.getSex());
            additionalInfo.put("email", authInfo.getEmail());
            additionalInfo.put("permissions", authInfo.getPermissions());
            additionalInfo.put("mobile", authInfo.getMobile());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }
}

