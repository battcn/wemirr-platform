package com.wemirr.platform.ai.core.exception;

import com.wemirr.framework.ai.core.exception.AiException;

import java.io.Serial;

/**
 * AI 服务异常（业务层）
 * <p>
 * 继承自 ai-spring-boot-starter 的 AiException
 * </p>
 *
 * @author Levin
 * @since 2025/12/27
 */
public class AiServiceException extends AiException {

    @Serial
    private static final long serialVersionUID = 1L;

    public AiServiceException(String message) {
        super(message);
    }

    public AiServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public AiServiceException(int code, String message) {
        super(code, message);
    }
}
