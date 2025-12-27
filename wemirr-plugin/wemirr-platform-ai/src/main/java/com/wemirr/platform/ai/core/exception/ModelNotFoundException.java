package com.wemirr.platform.ai.core.exception;

import com.wemirr.framework.ai.core.constant.AiConstants;

import java.io.Serial;

/**
 * 模型配置不存在异常（业务层）
 *
 * @author Levin
 * @since 2025/12/27
 */
public class ModelNotFoundException extends AiServiceException {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final int NOT_FOUND = 404;

    public ModelNotFoundException(Long modelId) {
        super(NOT_FOUND, String.format(AiConstants.ERROR_MODEL_NOT_FOUND, modelId));
    }

    public ModelNotFoundException(String modelIdentifier) {
        super(NOT_FOUND, String.format(AiConstants.ERROR_MODEL_NOT_FOUND, modelIdentifier));
    }

    public ModelNotFoundException(Long modelId, Throwable cause) {
        super(String.format(AiConstants.ERROR_MODEL_NOT_FOUND, modelId), cause);
    }
}
