/*
 * Copyright (c) 2023 WEMIRR-PLATFORM Authors. All Rights Reserved.
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wemirr.framework.security.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.google.common.collect.Lists;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.security.configuration.client.annotation.IgnoreAuthorize;
import com.wemirr.framework.security.domain.UserInfoDetails;
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
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.resource.BearerTokenError;
import org.springframework.security.oauth2.server.resource.BearerTokenErrorCodes;
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PathPatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPattern;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
        log.error("security error - {}", e.getLocalizedMessage());
        Map<String, String> parameters = new LinkedHashMap<>();
        if (e instanceof OAuth2InvalidException exception) {
            // 权限不足
            parameters.put("error", e.getMessage());
            parameters.put("error_description", exception.getLocalizedMessage());
            parameters.put("message", exception.getLocalizedMessage());
            response.setStatus(exception.getStatus().value());
        } else if (e instanceof BadCredentialsException exception) {
            // 权限不足
            parameters.put("error", exception.getMessage());
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
            parameters.put("message", "Token 已失效");
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
        if (authentication instanceof JwtAuthenticationToken token) {
            if (token.getPrincipal()instanceof Jwt jwt) {
                final Object userinfo = jwt.getClaim("userinfo");
                return BeanUtil.toBean(userinfo, UserInfoDetails.class);
            } else {
                final String tokenValue = token.getToken().getTokenValue();
                final OAuth2AuthorizationService oAuth2AuthorizationService = SpringUtil.getBean(OAuth2AuthorizationService.class);
                final OAuth2Authorization oAuth2Authorization = oAuth2AuthorizationService.findByToken(tokenValue, OAuth2TokenType.ACCESS_TOKEN);
                final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        (UsernamePasswordAuthenticationToken) Objects.requireNonNull(oAuth2Authorization).getAttributes().get(Principal.class.getName());
                return (UserInfoDetails) usernamePasswordAuthenticationToken.getPrincipal();
            }
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
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken || authentication instanceof OAuth2ClientAuthenticationToken) {
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
                entry -> entry.getValue().length > 1 ? entry.getValue() : entry.getValue()[0]));
    }
    
    public static List<String> loadIgnoreAuthorizeUrl(RequestMappingHandlerMapping requestMappingHandlerMapping) {
        final List<String> urls = Lists.newArrayList();
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> handlerMethodEntry : handlerMethods.entrySet()) {
            HandlerMethod value = handlerMethodEntry.getValue();
            RequestMappingInfo key = handlerMethodEntry.getKey();
            IgnoreAuthorize annotation = value.getMethodAnnotation(IgnoreAuthorize.class);
            if (log.isDebugEnabled()) {
                log.debug("[key] - [{}] - [value] - [{}]", key, value);
            }
            if (annotation == null) {
                continue;
            }
            final PatternsRequestCondition patternsCondition = key.getPatternsCondition();
            if (Objects.nonNull(patternsCondition)) {
                urls.addAll(patternsCondition.getPatterns());
            }
            final PathPatternsRequestCondition pathPatternsCondition = key.getPathPatternsCondition();
            if (Objects.nonNull(pathPatternsCondition)) {
                final List<String> list = pathPatternsCondition.getPatterns().stream().map(PathPattern::getPatternString).toList();
                urls.addAll(list);
            }
        }
        return urls;
    }
}
