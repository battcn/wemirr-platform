package com.battcn.framework.redis.exception;

/**
 * Redis异常信息
 *
 * @author Levin
 */
public class CacheLockException extends RuntimeException {

    private static final long serialVersionUID = 4550515832057492430L;

    public CacheLockException() {
        super();
    }

    public CacheLockException(String message) {
        super(message);
    }

    public CacheLockException(String message, Throwable cause) {
        super(message, cause);
    }

    public CacheLockException(Throwable cause) {
        super(cause);
    }
}