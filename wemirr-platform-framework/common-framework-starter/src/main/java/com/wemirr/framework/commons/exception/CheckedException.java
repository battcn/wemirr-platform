package com.wemirr.framework.commons.exception;


import com.wemirr.framework.commons.entity.enums.IntEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.text.MessageFormat;

/**
 * 检查型异常
 *
 * @author Levin
 * @since 2019-03-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CheckedException extends RuntimeException {

    private static final long serialVersionUID = -5552347741420562795L;

    private static final int BAD_REQUEST = 400;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;

    private static final int USER_NOT_FOUND = 428;

    /**
     * 待审核
     */
    private static final int WAIT_CHECK = 429;
    /**
     * 审核拒绝
     */
    private static final int REJECT_CHECK = 430;

    /**
     * 通过code获取微信openid异常
     */
    private static final int GET_OPENID_BY_CODE_ERROR = 436;

    /**
     * 规范的HTTP状态响应码,如400/403/503等
     */
    private transient int code;


    /**
     * badRequest
     *
     * @param message 错误消息
     * @return 异常
     */
    public static CheckedException badRequest(String message) {
        return new CheckedException(BAD_REQUEST, message);
    }

    /**
     * badRequest 支持参数占位符（badRequest(订单 {0} 不存在,AP1001) 会被编译成 订单 AP1001 不存在）
     *
     * @param message 错误消息
     * @return 异常
     */
    public static CheckedException badRequest(String message, Object... arguments) {
        return new CheckedException(BAD_REQUEST, MessageFormat.format(message, arguments));
    }

    /**
     * badRequest
     *
     * @param intEnum 实现了 IntEnum 接口的任何类，意味着是可插拔的
     * @return 异常
     */
    public static CheckedException badRequest(IntEnum intEnum) {
        return new CheckedException(intEnum.type(), intEnum.desc());
    }

    public static CheckedException badRequest(IntEnum intEnum, String arg) {
        return new CheckedException(intEnum.type(), String.format(intEnum.desc(), arg));
    }

    public static CheckedException notFound(String message, Object... arguments) {
        return new CheckedException(NOT_FOUND, MessageFormat.format(message, arguments));
    }

    public static CheckedException notFound(IntEnum intEnum, String arg) {
        return new CheckedException(intEnum.type(), String.format(intEnum.desc(), arg));
    }

    public static CheckedException notFound(String message) {
        return new CheckedException(NOT_FOUND, message);
    }

    public static CheckedException notFound(IntEnum intEnum) {
        return new CheckedException(intEnum.type(), intEnum.desc());
    }

    public static CheckedException waitCheck(String message) {
        return new CheckedException(WAIT_CHECK, message);
    }

    public static CheckedException rejectCheck(String message) {
        return new CheckedException(REJECT_CHECK, message);
    }

    public static CheckedException getOpenidByCodeError(String message) {
        return new CheckedException(GET_OPENID_BY_CODE_ERROR, message);
    }

    public static CheckedException userNotFound(String message) {
        return new CheckedException(USER_NOT_FOUND, message);
    }

    public static CheckedException forbidden() {
        return forbidden("登录过期,请重新登录");
    }

    public static CheckedException forbidden(String message) {
        return new CheckedException(FORBIDDEN, "登录过期,请重新登录");
    }

    public CheckedException(String message) {
        super(message);
        this.setCode(BAD_REQUEST);
    }

    public CheckedException(String message, Throwable e) {
        super(message, e);
        this.setCode(BAD_REQUEST);
    }

    public CheckedException(int code, String message) {
        super(message);
        this.setCode(code);
    }

    public CheckedException(int code, String message, Throwable t) {
        super(message, t);
        this.code = code;
    }
}