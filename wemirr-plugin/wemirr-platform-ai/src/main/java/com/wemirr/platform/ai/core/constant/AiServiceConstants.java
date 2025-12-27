package com.wemirr.platform.ai.core.constant;

import com.wemirr.framework.ai.core.constant.AiConstants;

/**
 * AI 服务业务层常量
 * <p>
 * 继承自 framework 的 AiConstants，并扩展业务层特有的常量
 *
 * @author Levin
 * @since 2025/12/27
 */
public final class AiServiceConstants {

    private AiServiceConstants() {}

    // ==================== 继承自 Framework ====================
    public static final int DEFAULT_MAX_MESSAGES = AiConstants.DEFAULT_MAX_MESSAGES;
    public static final int DEFAULT_RAG_MAX_RESULTS = AiConstants.DEFAULT_RAG_MAX_RESULTS;
    public static final double DEFAULT_RAG_MIN_SCORE = AiConstants.DEFAULT_RAG_MIN_SCORE;

    // ==================== 业务层错误消息 ====================
    public static final String ERROR_UNSUPPORTED_CHAT_TYPE = "不支持的对话类型: %s";
    public static final String ERROR_AGENT_NOT_FOUND = "智能体不存在: %s";
    public static final String ERROR_KNOWLEDGE_BASE_NOT_FOUND = "知识库不存在: %s";
    public static final String ERROR_CONVERSATION_NOT_FOUND = "会话不存在: %s";
}
