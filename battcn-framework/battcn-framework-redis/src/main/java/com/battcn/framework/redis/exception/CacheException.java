package com.battcn.framework.redis.exception;

/**
 * Redis异常信息
 *
 * @author Levin
 */
public class CacheException extends RuntimeException {

    private static final long serialVersionUID = 4550515832057492430L;

    public CacheException() {
        super();
    }

    public CacheException(String message) {
        super(message);
    }

    public CacheException(Throwable cause, String message) {
        super(message, cause);
    }

    public CacheException(Throwable cause) {
        super(cause);
    }
}