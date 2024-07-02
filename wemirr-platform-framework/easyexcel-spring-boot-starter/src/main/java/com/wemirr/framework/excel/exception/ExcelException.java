package com.wemirr.framework.excel.exception;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Levin
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ExcelException extends RuntimeException {


    private int code;

    public ExcelException(String message) {
        super(message);
    }

    public ExcelException(int code, String message) {
        super(message);
        this.setCode(code);
    }


    public ExcelException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExcelException(Throwable cause) {
        super(cause);
    }

    public ExcelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
