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
        // ?????? IntegrationAuthenticationFilter ??? Servlet ?????????????????????Spring Security ?????????????????? FilterChain
        FilterRegistrationBean<IntegrationAuthenticationFilter> registration = new FilterRegistrationBean<>(integrationAuthenticationFilter);
        registration.setEnabled(false);
        return registration;
    }

    @Bean
    public DefaultTokenServices tokenService() {
        //  ??????TokenServices??????
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);
        // ??????????????????Token
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setReuseRefreshToken(true);
        tokenServices.setClientDetailsService(clientDetailsService);
        // ??????accessToken???refreshToken?????????????????????(??????clientDetails??????null????????????????????????clientDetails?????????null???clientDetails??????)
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
        // ?????????????????? ??????????????????
        security.allowFormAuthenticationForClients()
                .authenticationEntryPoint(authenticationEntryPoint)
                .tokenKeyAccess("isAuthenticated()")
                .checkTokenAccess("permitAll()");
        // ???????????????????????????
        security.addTokenEndpointAuthenticationFilter(integrationAuthenticationFilter);
    }

    /**
     * ???????????????
     */
    private static final String CLIENT_CREDENTIALS = "client_credentials";

    /**
     * token????????????????????????????????????
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
            additionalInfo.put("tenantCode", authInfo.getTenantCode());
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

