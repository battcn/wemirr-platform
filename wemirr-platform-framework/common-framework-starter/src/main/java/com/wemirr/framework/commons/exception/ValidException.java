package com.wemirr.framework.commons.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 验证型异常
 *
 * @author Levin
 * @since 2019-03-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ValidException extends RuntimeException {

    private int code;

    public ValidException(ExceptionCode exception) {
        super(exception.getMessage());
        this.setCode(exception.getCode());
    }

    public ValidException(ExceptionCode exception, String message) {
        super(message);
        this.setCode(exception.getCode());
    }

    public ValidException(String message) {
        super(message);
    }

    public ValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidException(Throwable cause) {
        super(cause);
    }

    public ValidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
