package com.battcn.bookstore.consumer.config.interceptor;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.battcn.framework.exception.ErrorResponseEntity;
import com.battcn.framework.security.Authentication;
import com.battcn.framework.security.Environments;
import com.battcn.framework.security.SecurityTokenProperties;
import com.battcn.framework.security.annotation.IgnoreAuthenticate;
import com.battcn.framework.security.extractor.TokenExtractor;
import com.battcn.framework.security.model.token.RawAccessToken;
import com.battcn.member.facade.MemberService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

import static com.battcn.framework.security.AuthConstant.AUTHORIZATION;


/**
 * @author Levin
 */
@Slf4j
@Configuration
public class AuthenticateInterceptor implements HandlerInterceptor {


    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880", timeout = 10000)
    private MemberService memberService;

    private final TokenExtractor tokenExtractor;
    private final SecurityTokenProperties securityTokenProperties;

    @Autowired
    public AuthenticateInterceptor(SecurityTokenProperties securityTokenProperties, TokenExtractor tokenExtractor) {
        this.securityTokenProperties = securityTokenProperties;
        this.tokenExtractor = tokenExtractor;
    }

    private static final List<String> ANY_MATCH = Arrays.asList("html", "error", "static", "docs", "resources", "images", "staticsrc", "configuration", "actuator");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Environments.setNow();
        String reqUrl = request.getRequestURI().replace(request.getContextPath(), "");
        if (ANY_MATCH.stream().anyMatch(reqUrl::contains) || !(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        IgnoreAuthenticate ignoreLogin = handlerMethod.getMethodAnnotation(IgnoreAuthenticate.class);
        String tokenPayload = request.getHeader(AUTHORIZATION);
        log.debug("[拦截到的Token] - [{}]", tokenPayload);
        if (ignoreLogin != null) {
            if (!ignoreLogin.needLoginInfo() || StringUtils.isEmpty(tokenPayload)) {
                return true;
            }
        }
        if (StringUtils.isEmpty(tokenPayload)) {
            ErrorResponseEntity responseEntity = new ErrorResponseEntity(HttpStatus.FORBIDDEN.value(), "Token expired");
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().write(JSON.toJSONString(responseEntity));
            return false;
        }
        // 解析Token,将Token中的上下文存储到 Environments 中
        RawAccessToken rawAccessToken = new RawAccessToken(tokenExtractor.extract(tokenPayload));
        Jws<Claims> jwsClaims = rawAccessToken.parseClaims(securityTokenProperties.getSigningKey());
        Environments.set(Authentication.create(jwsClaims));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        Environments.clear();
    }

}
