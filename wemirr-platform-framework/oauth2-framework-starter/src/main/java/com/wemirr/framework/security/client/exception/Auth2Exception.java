package com.wemirr.framework.security.client.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * 自定义OAuth2Exception
 *
 * @author Levin
 * @since 2019-07-23
 */
@Getter
@JsonSerialize(using = Auth2ExceptionSerializer.class)
public class Auth2Exception extends OAuth2Exception {

    private static final long serialVersionUID = -3092808061454515372L;
    private int code;
    private final String message;
    protected HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public Auth2Exception(String message) {
        super(message);
        this.message = message;
    }

    public Auth2Exception(String message, int code) {
        super(message);
        this.message = message;
        this.code = code;
    }
}
