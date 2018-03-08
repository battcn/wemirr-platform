package com.battcn.framework.exception;


/**
 * 自定义异常
 *
 * @author Levin
 * @since 2018/03/08
 */
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 649333084513029673L;

    public CustomException() {
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    public CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

