package com.wemirr.framework.commons.exception;

/**
 * 基础异常码接口
 *
 * @author Levin
 * @since 2020-09-27
 */
public interface BaseExceptionCode {

    /**
     * 异常编码
     *
     * @return code
     */
    int getCode();

    /**
     * 异常消息
     *
     * @return 异常信息
     */
    String getMessage();

}
