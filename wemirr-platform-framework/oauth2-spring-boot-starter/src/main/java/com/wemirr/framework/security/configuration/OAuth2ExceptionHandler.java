package com.wemirr.framework.security.configuration;


import cn.dev33.satoken.context.SaHolder;
import com.wemirr.framework.commons.entity.Result;
import lombok.extern.slf4j.Slf4j;
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
    @ExceptionHandler
    public ResponseEntity<Result<?>> handlerException(Exception e) {
        log.error("http request uri => {},message => {}", SaHolder.getRequest().getUrl(), e.getLocalizedMessage());
        return ResponseEntity.ok(Result.fail(e.getMessage()));
    }

}