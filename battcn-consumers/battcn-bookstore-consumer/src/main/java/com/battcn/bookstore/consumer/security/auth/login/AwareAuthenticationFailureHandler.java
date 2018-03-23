package com.battcn.bookstore.consumer.security.auth.login;

import com.battcn.bookstore.consumer.security.exceptions.AuthMethodNotSupportedException;
import com.battcn.bookstore.consumer.security.exceptions.ExpiredTokenException;
import com.battcn.framework.exception.ErrorResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 认证失败处理程序
 *
 * @author Levin
 * @since 2017-05-25
 */
@Component
public class AwareAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper;

    @Autowired
    public AwareAuthenticationFailureHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

        if (e instanceof BadCredentialsException) {
            objectMapper.writeValue(response.getWriter(), ErrorResponseEntity.of("Invalid username or password", HttpStatus.UNAUTHORIZED.value()));
        } else if (e instanceof ExpiredTokenException) {
            objectMapper.writeValue(response.getWriter(), ErrorResponseEntity.of("Token has expired", HttpStatus.UNAUTHORIZED.value()));
        } else if (e instanceof AuthMethodNotSupportedException) {
            objectMapper.writeValue(response.getWriter(), ErrorResponseEntity.of(e.getMessage(), HttpStatus.UNAUTHORIZED.value()));
        }
        objectMapper.writeValue(response.getWriter(), ErrorResponseEntity.of("Authentication failed", HttpStatus.UNAUTHORIZED.value()));
    }
}
