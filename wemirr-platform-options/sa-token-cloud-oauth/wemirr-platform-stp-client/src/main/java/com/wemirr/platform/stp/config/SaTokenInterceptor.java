package com.wemirr.platform.stp.config;

import cn.dev33.satoken.id.SaIdUtil;
import cn.dev33.satoken.util.SaResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Levin
 */
@RequiredArgsConstructor
public class SaTokenInterceptor implements HandlerInterceptor {

    private final UserInfoTokenServices userInfoTokenServices;

    /**
     * 每次请求之前触发的方法
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 获取处理method
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        final SaResult userInfo = userInfoTokenServices.getUserInfo();
        SaIdUtil.checkCurrentRequestToken();
        // 通过验证
        return true;
    }
}
