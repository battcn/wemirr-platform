package com.wemirr.framework.security.configuration.client;


import com.wemirr.framework.commons.entity.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author Levin
 */
@Slf4j
@RequiredArgsConstructor
public class ResourceAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    @SneakyThrows
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        log.error("访问受限 - {}", exception.getLocalizedMessage());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter printWriter = response.getWriter();
        if (exception instanceof InvalidBearerTokenException e) {
            printWriter.append(Result.fail(HttpStatus.UNAUTHORIZED.value(), "访问受限,无效TOKEN").toString());
        } else {
            printWriter.append(Result.fail(HttpStatus.UNAUTHORIZED.value(), exception.getMessage()).toString());
        }
    }
}