package com.wemirr.framework.commons.exception;

/**
 * @author YanCh
 * Create by 2025-03-27 18:04
 **/
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
