package com.battcn.framework.exception.enums;


/**
 * 自定义错误码
 *
 * @author Levin
 * @since 2018-01-10
 */
public enum CustomResponseCode {

    /**
     * 通用错误
     */
    COMMON_ERROR_CODE(400, "未知错误"),

    /**
     * 该记录不存在
     */
    INVALID(1001, "该记录不存在"),

    /**
     * 未授权
     */
    UNAUTHORIZED(1051, "未授权");
    private Integer code;
    private String message;

    public Integer code() {
        return code;
    }

    public String message() {
        return message;
    }

    CustomResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}