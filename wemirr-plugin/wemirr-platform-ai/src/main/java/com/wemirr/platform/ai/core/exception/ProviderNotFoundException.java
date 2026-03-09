package com.wemirr.platform.ai.core.exception;

import java.io.Serial;

/**
 * 模型提供商不存在异常（业务层）
 *
 * @author Levin
 * @since 2025/12/27
 */
public class ProviderNotFoundException extends AiServiceException {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final int NOT_FOUND = 404;

    public ProviderNotFoundException(String providerId) {
        super(NOT_FOUND, "模型提供商不存在: " + providerId);
    }

    public ProviderNotFoundException(String providerId, String modelName) {
        super(NOT_FOUND, String.format("未找到支持的模型提供商: provider=%s, model=%s", providerId, modelName));
    }
}
