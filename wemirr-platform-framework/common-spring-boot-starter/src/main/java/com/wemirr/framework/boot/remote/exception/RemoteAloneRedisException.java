package com.wemirr.framework.boot.remote.exception;

/**
 * @author YanCh
 * Created on: 2025-06-05 17:19
 **/
public class RemoteAloneRedisException extends RuntimeException {
    public RemoteAloneRedisException(String message) {
        super(message);
    }
}
