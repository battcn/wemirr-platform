package com.wemirr.framework.security.client.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;

/**
 * @author Levin
 * @since 2019-07-23
 */
@JsonSerialize(using = Auth2ExceptionSerializer.class)
public class MethodNotAllowedException extends Auth2Exception {

    public MethodNotAllowedException(String msg) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "method_not_allowed";
    }

    @Override
    public int getHttpErrorCode() {
        return HttpStatus.METHOD_NOT_ALLOWED.value();
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.METHOD_NOT_ALLOWED;
    }

}
