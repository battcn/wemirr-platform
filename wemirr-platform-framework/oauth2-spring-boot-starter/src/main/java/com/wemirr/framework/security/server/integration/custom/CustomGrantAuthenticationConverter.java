package com.wemirr.framework.security.server.integration.custom;

import cn.hutool.core.util.StrUtil;
import com.wemirr.framework.security.exception.OAuth2InvalidException;
import com.wemirr.framework.security.utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientCredentialsAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2RefreshTokenAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 自定义 grant_type 校验转换器
 *
 * @author Levin
 */
@Slf4j
public class CustomGrantAuthenticationConverter implements AuthenticationConverter {

    public static final String GRANT_TYPE_CUSTOM = "custom";
    public static final String GRANT_TYPE_CUSTOM_PRE = "urn:ietf:params:oauth:grant-type:custom";

    @Override
    public Authentication convert(HttpServletRequest request) {
        final String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);
        final Map<String, Object> additionalParameters = SecurityUtils.getAdditionalParameters(request);
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthorizationGrantType authorizationGrantType = new AuthorizationGrantType(grantType);
        if (StrUtil.contains(grantType, GRANT_TYPE_CUSTOM) || StrUtil.equals(grantType, GRANT_TYPE_CUSTOM_PRE)) {
            authorizationGrantType = new AuthorizationGrantType(GRANT_TYPE_CUSTOM_PRE);
        }
        // 授权码模式登陆
        if (authorizationGrantType.equals(AuthorizationGrantType.AUTHORIZATION_CODE)) {
            log.info("授权码模式登陆 - {}", additionalParameters);
            final String code = request.getParameter(OAuth2ParameterNames.CODE);
            final String redirectUri = request.getParameter(OAuth2ParameterNames.REDIRECT_URI);
            return new OAuth2AuthorizationCodeAuthenticationToken(code, authentication, redirectUri, additionalParameters);
        }
        String scope = request.getParameter(OAuth2ParameterNames.SCOPE);
        if (StrUtil.isBlank(scope)) {
            throw new OAuth2InvalidException(HttpStatus.UNAUTHORIZED, "OAuth 2.0 Parameter: " + OAuth2ParameterNames.SCOPE);
        }
        Set<String> requestedScopes = new HashSet<>(Arrays.asList(StringUtils.delimitedListToStringArray(scope, " ")));
        if (authorizationGrantType.equals(AuthorizationGrantType.CLIENT_CREDENTIALS)) {
            return new OAuth2ClientCredentialsAuthenticationToken(authentication, requestedScopes, additionalParameters);
        }
        // 客户端模式
        if (authorizationGrantType.equals(AuthorizationGrantType.REFRESH_TOKEN)) {
            String refreshToken = request.getParameter(OAuth2ParameterNames.REFRESH_TOKEN);
            if (StrUtil.isBlank(refreshToken)) {
                throw new OAuth2InvalidException(HttpStatus.UNAUTHORIZED, "OAuth 2.0 Parameter: " + OAuth2ParameterNames.REFRESH_TOKEN);
            }
            return new OAuth2RefreshTokenAuthenticationToken(refreshToken, authentication, requestedScopes, additionalParameters);
        }
        String username = request.getParameter(OAuth2ParameterNames.USERNAME);
        if (StrUtil.isBlank(username)) {
            throw new OAuth2InvalidException(HttpStatus.UNAUTHORIZED, "OAuth 2.0 Parameter: " + OAuth2ParameterNames.USERNAME);
        }
        String password = request.getParameter(OAuth2ParameterNames.PASSWORD);
        if (StrUtil.isBlank(password)) {
            throw new OAuth2InvalidException(HttpStatus.UNAUTHORIZED, "OAuth 2.0 Parameter: " + OAuth2ParameterNames.PASSWORD);
        }
        // 自定义模式
        return new CustomGrantAuthenticationToken(request, authorizationGrantType, authentication, requestedScopes, additionalParameters);
    }

}