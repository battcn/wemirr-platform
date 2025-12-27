package com.wemirr.framework.ai.core.constant;

/**
 * AI 服务常量
 *
 * @author Levin
 * @since 2025/12/27
 */
public final class AiConstants {

    private AiConstants() {}

    // ==================== 聊天记忆配置 ====================
    public static final int DEFAULT_MAX_MESSAGES = 10;

    // ==================== RAG 检索配置 ====================
    public static final int DEFAULT_RAG_MAX_RESULTS = 5;
    public static final double DEFAULT_RAG_MIN_SCORE = 0.5;
    public static final int DEFAULT_RERANK_MAX_RESULTS = 5;
    public static final double DEFAULT_RERANK_MIN_SCORE = 0.5;
    public static final int DEFAULT_GRAPH_MAX_RESULTS = 10;
    public static final int DEFAULT_VECTOR_MAX_RESULTS = 8;
    public static final int DEFAULT_WEB_SEARCH_MAX_RESULTS = 5;

    // ==================== 错误消息模板 ====================
    public static final String ERROR_MODEL_NOT_FOUND = "模型配置不存在: %s";
    public static final String ERROR_MODEL_TYPE_MISMATCH = "模型类型不匹配，期望: %s, 实际: %s";
    public static final String ERROR_NO_PROVIDER_AVAILABLE = "没有可用的模型提供商";
    public static final String ERROR_DUPLICATE_PROVIDER = "重复注册模型提供商: %s";
    public static final String ERROR_API_KEY_NOT_CONFIGURED = "API Key 未配置";
    public static final String ERROR_NO_RETRIEVER_ENABLED = "未启用任何检索器";
    public static final String ERROR_EMBEDDING_MODEL_NOT_CONFIGURED = "向量模型未配置";

    // ==================== 系统提示词模板 ====================
    public static final String SYSTEM_PROMPT_KNOWLEDGE_BASE = """
            你是一个专业的企业级知识库问答助手。
            
            【核心指令】
            1. 请严格根据检索到的上下文信息（Context）来回答用户的问题。
            2. 严禁使用你自己的预训练知识来回答问题。
            3. 如果检索到的上下文为空，或者上下文中不包含回答问题所需的信息，请直接回复："抱歉，当前的知识库中没有关于该问题的记录。"
            4. 不要写代码、不要讲故事、不要回答闲聊话题，除非这些内容在知识库中明确存在。
            """;

    // ==================== SSE 事件名称 ====================
    public static final String SSE_EVENT_START = "start";
    public static final String SSE_EVENT_THINKING = "thinking";
    public static final String SSE_EVENT_PARTIAL = "partial";
    public static final String SSE_EVENT_COMPLETE = "complete";
    public static final String SSE_EVENT_ERROR = "error";
    public static final String SSE_EVENT_PING = "ping";
}
