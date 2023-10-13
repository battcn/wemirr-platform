package com.wemirr.framework.security.configuration.server.handler;

import com.wemirr.framework.security.configuration.SecurityExtProperties;
import com.wemirr.framework.security.constant.SecurityConstants;
import com.wemirr.framework.security.utils.SecurityUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 重定向至登录处理
 *
 * @author Levin
 */
@Slf4j
public class LoginTargetAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private final SecurityExtProperties properties;

    public LoginTargetAuthenticationEntryPoint(SecurityExtProperties properties) {
        super(properties.getServer().getLoginFormUrl());
        this.properties = properties;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        final SecurityExtProperties.Server server = properties.getServer();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        if (server.isCustom()) {
            log.debug("自定义模式异常 - {}", exception.getLocalizedMessage());
            SecurityUtils.exceptionHandler(request, response, exception);
            return;
        }
        // 兼容设备码前后端分离
        if (request.getRequestURI().equals(server.getDeviceVerificationUri())
                && request.getMethod().equals(HttpMethod.POST.name())
                && UrlUtils.isAbsoluteUrl(server.getDeviceActivatedUri())) {
            log.debug("兼容前后端分离设备码异常 - {}", exception.getLocalizedMessage());
            SecurityUtils.exceptionHandler(request, response, exception);
            return;
        }
//        // 获取登录表单的地址
        String loginForm = determineUrlToUseForThisRequest(request, response, exception);
        if (!UrlUtils.isAbsoluteUrl(loginForm)) {
            log.debug("非绝对路径调用,父类方法处理 - {}", exception.getLocalizedMessage());
            super.commence(request, response, exception);
            return;
        }
        StringBuffer requestUrl = request.getRequestURL();
        if (!ObjectUtils.isEmpty(request.getQueryString())) {
            requestUrl.append("?").append(request.getQueryString());
        }
        // 绝对路径在重定向前添加target参数
        String targetParameter = URLEncoder.encode(requestUrl.toString(), StandardCharsets.UTF_8);
        String targetUrl = loginForm + "?target=" + targetParameter + "&" + SecurityConstants.NONCE_HEADER_NAME + "=" + request.getSession(Boolean.FALSE).getId();
        log.debug("重定向至前后端分离的登录页面：{}", targetUrl);
        this.redirectStrategy.sendRedirect(request, response, targetUrl);
    }
}
