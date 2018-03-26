package com.battcn.framework.redis.exception;

/**
 * Redis异常信息
 *
 * @author Levin
 */
public class CacheLimitException extends RuntimeException {

    private static final long serialVersionUID = 4550515832057492430L;

    public CacheLimitException() {
        super();
    }

    public CacheLimitException(String message) {
        super(message);
    }

    public CacheLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public CacheLimitException(Throwable cause) {
        super(cause);
    }
}