package com.wemirr.platform.ai.core.exception;

import java.io.Serial;

/**
 * 知识库不存在异常
 *
 * @author Levin
 * @since 2025/12/27
 */
public class KnowledgeBaseNotFoundException extends AiServiceException {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final int NOT_FOUND = 404;

    public KnowledgeBaseNotFoundException(Long kbId) {
        super(NOT_FOUND, "知识库不存在: " + kbId);
    }

    public KnowledgeBaseNotFoundException(String identifier) {
        super(NOT_FOUND, "知识库不存在: " + identifier);
    }
}
