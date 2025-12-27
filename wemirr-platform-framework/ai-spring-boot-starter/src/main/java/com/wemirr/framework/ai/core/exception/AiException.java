package com.wemirr.framework.ai.core.exception;

import com.wemirr.framework.commons.exception.CheckedException;

import java.io.Serial;

/**
 * AI 服务异常基类
 *
 * @author Levin
 * @since 2025/12/27
 */
public class AiException extends CheckedException {

    @Serial
    private static final long serialVersionUID = 1L;

    public AiException(String message) {
        super(message);
    }

    public AiException(String message, Throwable cause) {
        super(message, cause);
    }

    public AiException(int code, String message) {
        super(code, message);
    }
}
