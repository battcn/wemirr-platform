package com.wemirr.framework.security.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.security.entity.UserInfoDetails;
import com.wemirr.framework.security.exception.OAuth2InvalidException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.resource.BearerTokenError;
import org.springframework.security.oauth2.server.resource.BearerTokenErrorCodes;
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Levin
 */
@Slf4j
public final class SecurityUtils {
    private SecurityUtils() {
        // 禁止实例化工具类
        throw new UnsupportedOperationException("Utility classes cannot be instantiated.");
    }

    /**
     * 认证与鉴权失败回调
     *
     * @param request  当前请求
     * @param response 当前响应
     * @param e        具体的异常信息
     */
    public static void exceptionHandler(HttpServletRequest request, HttpServletResponse response, Throwable e) {
        Map<String, String> parameters = getErrorParameter(request, response, e);
        try {
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(JSON.toJSONString(Result.fail(response.getStatus(), parameters.remove("message"), parameters)));
            // 如果需要返回标准HTTP状态 就注释掉
            response.setStatus(HttpStatus.OK.value());
            response.getWriter().flush();
        } catch (IOException ex) {
            log.error("写回错误信息失败", e);
        }
    }

    /**
     * 获取异常信息map
     *
     * @param request  当前请求
     * @param response 当前响应
     * @param e        本次异常具体的异常实例
     * @return 异常信息map
     */
    private static Map<String, String> getErrorParameter(HttpServletRequest request, HttpServletResponse response, Throwable e) {
        e.printStackTrace();
        Map<String, String> parameters = new LinkedHashMap<>();
        if (e instanceof OAuth2InvalidException exception) {
            // 权限不足
            parameters.put("error", e.getMessage());
            parameters.put("error_description", exception.getLocalizedMessage());
            parameters.put("message", exception.getLocalizedMessage());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        } else if (e instanceof BadCredentialsException) {
            // 权限不足
            parameters.put("error", e.getMessage());
            parameters.put("error_description", "Failed to authenticate since password does not match stored value");
            parameters.put("message", "用户名或密码错误");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        } else if (request.getUserPrincipal() instanceof AbstractOAuth2TokenAuthenticationToken) {
            // 权限不足
            parameters.put("error", BearerTokenErrorCodes.INSUFFICIENT_SCOPE);
            parameters.put("error_description", "The request requires higher privileges than provided by the access token.");
            parameters.putIfAbsent("message", "权限不足,访问受限");
            response.setStatus(HttpStatus.FORBIDDEN.value());
        } else if (e instanceof OAuth2AuthenticationException authenticationException) {
            // jwt异常，e.g. jwt超过有效期、jwt无效等
            OAuth2Error error = authenticationException.getError();
            parameters.put("error", error.getErrorCode());
            parameters.put("error_uri", error.getUri());
            parameters.put("error_description", error.getDescription());
            if (error instanceof BearerTokenError bearerTokenError) {
                parameters.put("scope", bearerTokenError.getScope());
                response.setStatus(bearerTokenError.getHttpStatus().value());
            }
        }
        if (e instanceof InsufficientAuthenticationException) {
            // 没有携带jwt访问接口，没有客户端认证信息
            parameters.put("error", BearerTokenErrorCodes.INVALID_TOKEN);
            parameters.put("error_description", "Not authorized.");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
        parameters.putIfAbsent("message", e.getMessage());
        return parameters;
    }

    /**
     * 从认证信息中获取客户端token
     *
     * @param authentication 认证信息
     * @return 客户端认证信息，获取失败抛出异常
     */
    public static OAuth2ClientAuthenticationToken getAuthenticatedClientElseThrowInvalidClient(Authentication authentication) {
        OAuth2ClientAuthenticationToken clientPrincipal = null;
        if (OAuth2ClientAuthenticationToken.class.isAssignableFrom(authentication.getPrincipal().getClass())) {
            clientPrincipal = (OAuth2ClientAuthenticationToken) authentication.getPrincipal();
        }
        if (clientPrincipal != null && clientPrincipal.isAuthenticated()) {
            return clientPrincipal;
        }
        throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_CLIENT);
    }

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户详细信息(只适用于 authority 模块)
     *
     * @return 结果
     */
    public static UserInfoDetails getAuthInfo() {
        Authentication authentication = getAuthentication();
        if (authentication == null || anonymous()) {
            throw CheckedException.forbidden("认证信息不存在");
        }
        if (authentication.getPrincipal() instanceof UserInfoDetails) {
            return (UserInfoDetails) authentication.getPrincipal();
        }
        String detailsText = JSON.toJSONString(authentication.getDetails());
        final JSONObject detailJson = JSON.parseObject(detailsText);
        return detailJson.getObject(AUTH_DETAILS_PRINCIPAL, UserInfoDetails.class);
    }

    public static final String AUTH_DETAILS_PRINCIPAL = "principal";
    public static final String ANONYMOUS_USER = "anonymousUser";

    /**
     * 是否为匿名用户
     *
     * @return 是（true）|不是（false）
     */
    public static boolean anonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return true;
        }
        if (authentication.getPrincipal() == null || authentication instanceof UsernamePasswordAuthenticationToken) {
            return true;
        }
        return authentication.getPrincipal().equals(ANONYMOUS_USER);
    }

    /**
     * 提取 request 请求参数
     *
     * @param request request
     * @return additionalParameters
     */
    public static Map<String, Object> getAdditionalParameters(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        return request.getParameterMap().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,
                entry -> entry.getValue().length > 1 ? entry.getValue() : entry.getValue()[0]
        ));
    }
}
