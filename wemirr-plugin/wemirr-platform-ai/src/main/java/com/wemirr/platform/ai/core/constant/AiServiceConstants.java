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

    private AiServiceConstants() {
    }

    // ==================== 继承自 Framework ====================

    public static final int DEFAULT_MAX_MESSAGES = AiConstants.DEFAULT_MAX_MESSAGES;
    public static final int DEFAULT_RAG_MAX_RESULTS = AiConstants.DEFAULT_RAG_MAX_RESULTS;
    public static final double DEFAULT_RAG_MIN_SCORE = AiConstants.DEFAULT_RAG_MIN_SCORE;

    // ==================== 图谱检索相关常量 ====================

    /**
     * 默认向量相似度阈值
     */
    public static final double DEFAULT_VECTOR_SCORE_THRESHOLD = 0.7;

    /**
     * 混合检索默认向量相似度阈值
     */
    public static final double HYBRID_VECTOR_SCORE_THRESHOLD = 0.6;

    /**
     * 默认向量检索结果数
     */
    public static final int DEFAULT_VECTOR_SEARCH_LIMIT = 5;

    /**
     * 默认实体匹配结果数
     */
    public static final int DEFAULT_ENTITY_MATCH_LIMIT = 5;

    /**
     * 默认子图扩展深度
     */
    public static final int DEFAULT_HOP_DEPTH = 1;

    /**
     * 默认最大三元组数
     */
    public static final int DEFAULT_MAX_TRIPLES = 30;

    /**
     * 默认图谱检索最大结果数
     */
    public static final int DEFAULT_GRAPH_MAX_RESULTS = 10;

    /**
     * 默认重排序最大结果数
     */
    public static final int DEFAULT_RERANK_MAX_RESULTS = 5;

    /**
     * 默认重排序最小分数
     */
    public static final double DEFAULT_RERANK_MIN_SCORE = 0.5;

    // ==================== 向量化相关常量 ====================

    /**
     * 默认向量维度
     */
    public static final int DEFAULT_VECTOR_DIMENSION = 768;

    /**
     * 日志截断长度
     */
    public static final int LOG_TRUNCATE_LENGTH = 100;

    /**
     * 问题日志截断长度
     */
    public static final int QUESTION_LOG_TRUNCATE_LENGTH = 50;

    // ==================== MCP 相关常量 ====================

    /**
     * MCP 工具执行超时时间（秒）
     */
    public static final int MCP_TOOL_EXECUTION_TIMEOUT_SECONDS = 60;

    // ==================== 业务层错误消息 ====================

    public static final String ERROR_UNSUPPORTED_CHAT_TYPE = "不支持的对话类型: %s";
    public static final String ERROR_AGENT_NOT_FOUND = "智能体不存在: %s";
    public static final String ERROR_KNOWLEDGE_BASE_NOT_FOUND = "知识库不存在: %s";
    public static final String ERROR_CONVERSATION_NOT_FOUND = "会话不存在: %s";
    public static final String ERROR_MODEL_NOT_FOUND = "模型配置不存在: %s";
    public static final String ERROR_MCP_CONFIG_NOT_FOUND = "MCP配置不存在: %s";
    public static final String ERROR_MCP_SERVER_DISABLED = "MCP服务器已禁用: %s";
    public static final String ERROR_GRAPH_SERVICE_UNAVAILABLE = "图谱服务不可用";
    public static final String ERROR_EMBEDDING_MODEL_NOT_CONFIGURED = "知识库未配置向量模型";
}
