package com.wemirr.framework.security.configuration.server.handler;

import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.security.configuration.SecurityExtProperties;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 校验设备码成功响应类
 *
 * @author Levin
 */
@RequiredArgsConstructor
public class DeviceAuthorizationResponseHandler implements AuthenticationSuccessHandler {

    private final SecurityExtProperties.Server server;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // 写回json数据
        Result<Object> result = Result.success(server.getDeviceActivatedUri());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(result.toString());
        response.getWriter().flush();
    }
}
