package com.wemirr.framework.security.configuration;


import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.SaTokenException;
import com.wemirr.framework.commons.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author click33
 */
@Slf4j
@RestControllerAdvice
public class OAuth2ExceptionHandler {

    @ExceptionHandler(NotLoginException.class)
    public ResponseEntity<Result<?>> handlerException(NotLoginException e) {
        log.error("http request uri => {},message => {}", SaHolder.getRequest().getUrl(), e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Result.fail(HttpStatus.UNAUTHORIZED.value(), e.getMessage()));
    }

    @ExceptionHandler(SaTokenException.class)
    public ResponseEntity<Result<?>> handlerException(SaTokenException e) {
        log.error("http request uri => {},message => {}", SaHolder.getRequest().getUrl(), e.getLocalizedMessage());
        return ResponseEntity.ok(Result.fail(HttpStatus.FORBIDDEN.value(), e.getMessage()));
    }
}