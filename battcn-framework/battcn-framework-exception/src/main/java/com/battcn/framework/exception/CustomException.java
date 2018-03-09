package com.battcn.framework.exception;


import com.battcn.framework.exception.enums.CustomResponseCode;

import java.text.MessageFormat;

/**
 * 自定义异常
 *
 * @author Levin
 * @since 2018/03/08
 */
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = -8994969925213820584L;

    private static final int BAD_REQUEST = 400;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int RESOURCE_REPLICATION = 409;

    /**
     * 规范的HTTP状态响应码,如400/403/503等
     */
    private transient int code;
    /**
     * 自定义返回码
     */
    private int customCode;

    public static CustomException badRequest(String message, Object... arguments) {
        return new CustomException(BAD_REQUEST, MessageFormat.format(message, arguments));
    }

    public static CustomException badRequest(String message) {
        return new CustomException(BAD_REQUEST, message);
    }

    public static CustomException badRequest(CustomResponseCode responseCode) {
        return new CustomException(BAD_REQUEST, responseCode.code(), responseCode.message());
    }

    public static CustomException badRequest(Integer customCode, String message) {
        return new CustomException(BAD_REQUEST, customCode, message);
    }

    public static CustomException notFound(String message, Object... arguments) {
        return new CustomException(NOT_FOUND, MessageFormat.format(message, arguments));
    }

    public static CustomException notFound(String message) {
        return new CustomException(NOT_FOUND, message);
    }

    public static CustomException notFound(CustomResponseCode responseCode) {
        return new CustomException(NOT_FOUND, responseCode.code(), responseCode.message());
    }

    public static CustomException notFound(Integer customCode, String message) {
        return new CustomException(NOT_FOUND, customCode, message);
    }

    public static CustomException resourceReplication(String message) {
        return new CustomException(RESOURCE_REPLICATION, message);
    }
    public static CustomException forbidden(String message) {
        return new CustomException(FORBIDDEN, message);
    }

    public static CustomException forbidden() {
        return new CustomException(FORBIDDEN, "登录过期,请重新登录");
    }

    public CustomException(String message) {
        super(message);
        this.setCustomCode(BAD_REQUEST);
        this.setCode(BAD_REQUEST);
    }

    public CustomException(String message, Throwable e) {
        super(message, e);
        this.setCustomCode(BAD_REQUEST);
        this.setCode(BAD_REQUEST);
    }

    public CustomException(int code, String message) {
        super(message);
        this.setCustomCode(code);
        this.setCode(code);
    }

    public CustomException(int code, int customcode, String message) {
        super(message);
        this.setCustomCode(customcode);
        this.setCode(code);
    }

    public CustomException(int code, String message, Throwable t) {
        super(message, t);
        this.code = code;
    }

    public ErrorResponseEntity toErrorResponseEntity() {
        return new ErrorResponseEntity(this.customCode, this.getMessage());
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCustomCode() {
        return customCode;
    }

    public void setCustomCode(int customcode) {
        this.customCode = customcode;
    }

}

