package com.battcn.bookstore.consumer.config;

import com.battcn.framework.exception.ErrorResponseEntity;
import com.battcn.framework.redis.exception.CacheException;
import com.battcn.framework.redis.exception.CacheLimitException;
import com.battcn.framework.redis.exception.CacheLockException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Levin
 * @since 2018/3/22 0022
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CacheException.class, CacheLockException.class, CacheLimitException.class})
    public final ErrorResponseEntity handlerRedisException(final Exception e, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        CacheException exception = (CacheException) e;
        String errorMsg = exception.getMessage();
        return new ErrorResponseEntity(response.getStatus(), errorMsg);
    }

}
