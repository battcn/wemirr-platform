package com.battcn.framework.redis.exception;

/**
 * Redis异常信息
 *
 * @author Levin
 */
public class RedisException extends RuntimeException {
    
    private static final long serialVersionUID = 4550515832057492430L;

    public RedisException() {
        super();
    }

    public RedisException(String message) {
        super(message);
    }

    public RedisException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedisException(Throwable cause) {
        super(cause);
    }
}