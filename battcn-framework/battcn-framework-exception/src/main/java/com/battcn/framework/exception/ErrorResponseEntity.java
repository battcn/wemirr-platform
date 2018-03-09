package com.battcn.framework.exception;

/**
 * 自定义返回异常信息。
 *
 * @author Levin
 * @since 2018-01-10
 */
public class ErrorResponseEntity {

    private Integer customCode;
    private String message;

    public ErrorResponseEntity(Integer customCode, String message) {
        super();
        this.customCode = customCode;
        this.message = message;
    }

    public Integer getCustomCode() {
        return customCode;
    }

    public void setCustomCode(Integer customCode) {
        this.customCode = customCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
