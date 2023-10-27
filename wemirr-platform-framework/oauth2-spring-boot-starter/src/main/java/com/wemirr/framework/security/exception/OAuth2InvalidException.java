package com.wemirr.framework.security.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

/**
 * 验证码异常类
 * 校验验证码异常时抛出
 *
 * @author Levin
 */
@SuppressWarnings("ALL")
@Getter
public class OAuth2InvalidException extends AuthenticationException {

    private HttpStatus status;

    public OAuth2InvalidException(String msg) {
        super(msg);
    }

    public OAuth2InvalidException(HttpStatus status, String msg) {
        super(msg);
        this.status = status;
    }

    public OAuth2InvalidException(String msg, Throwable cause) {
        super(msg, cause);
    }
}