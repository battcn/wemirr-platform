package com.wemirr.framework.security.client.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;

/**
 * @author Levin
 */
@JsonSerialize(using = Auth2ExceptionSerializer.class)
public class InvalidException extends Auth2Exception {

    private static final long serialVersionUID = -4296699380286733162L;

    public InvalidException(String msg) {
        super(msg);
    }

//    @Override
    public String getOAuth2ErrorCode() {
        return "用户名或密码错误";
    }

//    @Override
    public int getHttpErrorCode() {
        return HttpStatus.UPGRADE_REQUIRED.value();
    }

//    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UPGRADE_REQUIRED;
    }

}
