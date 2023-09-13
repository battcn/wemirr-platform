package com.wemirr.framework.security.configuration.server;

import cn.hutool.extra.spring.SpringUtil;
import com.wemirr.framework.security.configuration.SecurityExtProperties;
import com.wemirr.framework.security.configuration.server.handler.*;
import com.wemirr.framework.security.configuration.server.support.IntegrationAuthenticator;
import com.wemirr.framework.security.configuration.server.support.custom.CustomGrantAuthenticationConverter;
import com.wemirr.framework.security.configuration.server.support.custom.CustomGrantAuthenticationProvider;
import com.wemirr.framework.security.configuration.server.support.device.DeviceClientAuthenticationConverter;
import com.wemirr.framework.security.configuration.server.support.device.DeviceClientAuthenticationProvider;
import com.wemirr.framework.security.constant.SecurityConstants;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.util.UrlUtils;

import java.util.Collection;

/**
 * @author Levin
 */
public class SecurityApply {

    private static final String CUSTOM_CONSENT_REDIRECT_URI = "/oauth2/consent/redirect";

    private static final String CUSTOM_DEVICE_REDIRECT_URI = "/activate/redirect";

    public static void applyFormLoginSecurity(HttpSecurity http, SecurityExtProperties properties) throws Exception {
        // 指定登录页面
        http.formLogin(formLogin -> {
            formLogin.loginPage("/login");
            if (UrlUtils.isAbsoluteUrl(properties.getLoginFormUrl())) {
                // 绝对路径代表是前后端分离，登录成功和失败改为写回json，不重定向了
                formLogin.successHandler(new LoginSuccessHandler());
                formLogin.failureHandler(new LoginFailureHandler());
            }
        });
    }

    public static DefaultSecurityFilterChain applyCustomSecurity(HttpSecurity http, Collection<IntegrationAuthenticator> integrationAuthenticators) throws Exception {
        CustomGrantAuthenticationProvider provider = new CustomGrantAuthenticationProvider();
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .authorizationEndpoint(authorizationEndpoint -> {
                    authorizationEndpoint.errorResponseHandler(new ConsentAuthenticationFailureHandler());
                    authorizationEndpoint.authorizationResponseHandler(new ConsentAuthorizationResponseHandler());
                })
                // 让认证服务器元数据中有自定义的认证方式
                .authorizationServerMetadataEndpoint(metadata -> metadata.authorizationServerMetadataCustomizer(customizer -> customizer.grantType(SecurityConstants.GRANT_TYPE_CUSTOM)))
                // 添加自定义grant_type——短信认证登录
                .tokenEndpoint(tokenEndpoint -> tokenEndpoint
                        .accessTokenRequestConverter(new CustomGrantAuthenticationConverter())
                        .accessTokenResponseHandler(new LoginSuccessHandler()).authenticationProvider(provider));
        DefaultSecurityFilterChain build = http.build();
        // 从框架中获取provider中所需的bean
        OAuth2TokenGenerator<?> tokenGenerator = http.getSharedObject(OAuth2TokenGenerator.class);
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        OAuth2AuthorizationService authorizationService = http.getSharedObject(OAuth2AuthorizationService.class);
        // 以上三个bean在build()方法之后调用是因为调用build方法时框架会尝试获取这些类，
        // 如果获取不到则初始化一个实例放入SharedObject中，所以要在build方法调用之后获取
        // 在通过set方法设置进provider中，但是如果在build方法之后调用 authenticationProvider(provider)
        // 框架会提示unsupported_grant_type，因为已经初始化完了，在添加就不会生效了
        provider.setTokenGenerator(tokenGenerator);
        provider.setAuthorizationService(authorizationService);
        provider.setAuthenticationManager(authenticationManager);
        provider.setIntegrationAuthenticators(integrationAuthenticators);
        return build;
    }


    public static void applyDeviceSecurity(HttpSecurity http) {
        // 新建设备码converter和provider
        final RegisteredClientRepository registeredClientRepository = SpringUtil.getBean(RegisteredClientRepository.class);
        final AuthorizationServerSettings authorizationServerSettings = SpringUtil.getBean(AuthorizationServerSettings.class);
        final DeviceClientAuthenticationConverter deviceClientAuthenticationConverter = new DeviceClientAuthenticationConverter(authorizationServerSettings.getDeviceAuthorizationEndpoint());
        final DeviceClientAuthenticationProvider deviceClientAuthenticationProvider = new DeviceClientAuthenticationProvider(registeredClientRepository);
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                // 开启OpenID Connect 1.0协议相关端点
                .oidc(Customizer.withDefaults())
                // 设置自定义用户确认授权页
                .authorizationEndpoint(authorizationEndpoint -> {
                    // 校验授权确认页面是否为完整路径；是否是前后端分离的页面
                    boolean absoluteUrl = UrlUtils.isAbsoluteUrl(SecurityConstants.CONSENT_PAGE_URI);
                    // 如果是分离页面则重定向，否则转发请求
                    authorizationEndpoint.consentPage(absoluteUrl ? CUSTOM_CONSENT_REDIRECT_URI : SecurityConstants.CONSENT_PAGE_URI);
                    if (absoluteUrl) {
                        // 适配前后端分离的授权确认页面，成功/失败响应json
                        authorizationEndpoint.errorResponseHandler(new ConsentAuthenticationFailureHandler());
                        authorizationEndpoint.authorizationResponseHandler(new ConsentAuthorizationResponseHandler());
                    }
                })
                // 设置设备码用户验证url(自定义用户验证页)
                .deviceAuthorizationEndpoint(deviceAuthorizationEndpoint -> deviceAuthorizationEndpoint.verificationUri(UrlUtils.isAbsoluteUrl(SecurityConstants.DEVICE_ACTIVATE_URI) ? CUSTOM_DEVICE_REDIRECT_URI : SecurityConstants.DEVICE_ACTIVATE_URI))
                // 设置验证设备码用户确认页面
                .deviceVerificationEndpoint(deviceVerificationEndpoint -> {
                    // 校验授权确认页面是否为完整路径；是否是前后端分离的页面
                    boolean absoluteUrl = UrlUtils.isAbsoluteUrl(SecurityConstants.CONSENT_PAGE_URI);
                    // 如果是分离页面则重定向，否则转发请求
                    deviceVerificationEndpoint.consentPage(absoluteUrl ? CUSTOM_CONSENT_REDIRECT_URI : SecurityConstants.CONSENT_PAGE_URI);
                    if (absoluteUrl) {
                        // 适配前后端分离的授权确认页面，失败响应json
                        deviceVerificationEndpoint.errorResponseHandler(new ConsentAuthenticationFailureHandler());
                    }
                    // 如果授权码验证页面或者授权确认页面是前后端分离的
                    if (UrlUtils.isAbsoluteUrl(SecurityConstants.DEVICE_ACTIVATE_URI) || absoluteUrl) {
                        // 添加响应json处理
                        deviceVerificationEndpoint.deviceVerificationResponseHandler(new DeviceAuthorizationResponseHandler());
                    }
                })
                // 客户端认证添加设备码的converter和provider
                .clientAuthentication(clientAuthentication -> clientAuthentication.authenticationConverter(deviceClientAuthenticationConverter).authenticationProvider(deviceClientAuthenticationProvider));

    }

}
