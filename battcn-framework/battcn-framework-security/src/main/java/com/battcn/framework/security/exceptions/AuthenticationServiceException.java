package com.battcn.framework.security.exceptions;

/**
 * 不支持的方法验证 / GET != POST
 *
 * @author Levin
 * @since 2017-05-25
 */
public class AuthenticationServiceException extends RuntimeException {

    private static final long serialVersionUID = 3705043083010304496L;

    public AuthenticationServiceException(String msg) {
        super(msg);
    }

}
