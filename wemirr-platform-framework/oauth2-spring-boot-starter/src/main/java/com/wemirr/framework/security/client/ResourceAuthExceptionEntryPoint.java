package com.wemirr.framework.security.client;

import com.wemirr.framework.commons.entity.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 客户端异常处理
 * 1. 可以根据 AuthenticationException 不同细化异常处理
 *
 * @author Levin
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class ResourceAuthExceptionEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        final String uri = request.getRequestURI();
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        Result<String> result = Result.fail("错误");
        result.setSuccessful(false);
        if (authException != null) {
            String localizedMessage = authException.getLocalizedMessage();
            log.error("[访问受限] - [{}] - [{}]", localizedMessage, uri);
//            if (authException.getCause() instanceof InvalidTokenException) {
//                InvalidTokenException invalidTokenException = (InvalidTokenException) authException.getCause();
//                int httpErrorCode = invalidTokenException.getHttpErrorCode();
//                result.setCode(httpErrorCode);
//                result.setMessage("Token已失效");
//            } else if (authException instanceof InsufficientAuthenticationException) {
//                int httpErrorCode = HttpStatus.UNAUTHORIZED.value();
//                result.setCode(httpErrorCode);
//                result.setMessage("未授权的用户");
//            }
        } else {
            result.setCode(HttpStatus.UNAUTHORIZED.value());
            result.setMessage(HttpStatus.UNAUTHORIZED.name());
        }

        PrintWriter printWriter = response.getWriter();
        printWriter.append(result.toString());
    }
}
