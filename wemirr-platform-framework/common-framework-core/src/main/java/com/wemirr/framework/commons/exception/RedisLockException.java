package com.wemirr.framework.commons.exception;

import com.wemirr.framework.commons.entity.enums.CommonError;
import com.wemirr.framework.commons.entity.enums.IntEnum;
import lombok.EqualsAndHashCode;
import lombok.Setter;

/**
 * 检查型异常
 *
 * @author Levin
 * @since 2019-03-13
 */
@Setter
@EqualsAndHashCode(callSuper = true)
public class RedisLockException extends RuntimeException {

    private int code;

    public RedisLockException(IntEnum exception) {
        super(exception.desc());
        this.setCode(exception.type());
    }

    public RedisLockException(IntEnum exception, String message) {
        super(message);
        this.setCode(exception.type());
    }

    public RedisLockException(String message) {
        super(message);
        this.setCode(CommonError.REQUEST_PARAM_ERROR.type());
    }

    public RedisLockException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedisLockException(Throwable cause) {
        super(cause);
    }

    public RedisLockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}