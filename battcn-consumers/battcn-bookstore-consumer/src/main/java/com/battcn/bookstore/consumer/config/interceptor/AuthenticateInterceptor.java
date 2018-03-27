package com.battcn.bookstore.consumer.config.interceptor;

import com.alibaba.dubbo.config.annotation.Reference;
import com.battcn.framework.security.Environments;
import com.battcn.framework.security.annotation.IgnoreAuthenticate;
import com.battcn.member.facade.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;


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

    private static final String AUTHORIZATION = "Authorization";
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
        String token = request.getHeader(AUTHORIZATION);
        log.debug("[拦截到的Token] - [{}]", token);
        if (ignoreLogin != null) {
            if (!ignoreLogin.needLoginInfo() || StringUtils.isEmpty(token)) {
                return true;
            } else {
                return true;
            }
        }
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
