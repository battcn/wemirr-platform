package com.wemirr.framework.security.client.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;

/**
 * @author Levin
 * @since 2019-07-23
 */
@JsonSerialize(using = Auth2ExceptionSerializer.class)
public class UnauthorizedException extends Auth2Exception {

    public UnauthorizedException(String msg) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "unauthorized";
    }

    @Override
    public int getHttpErrorCode() {
        return HttpStatus.UNAUTHORIZED.value();
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
