package com.wemirr.platform.ai.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * AI 模块错误码
 * <p>
 * 统一定义 AI 模块的错误码，便于前端处理和日志追踪
 *
 * @author xJh
 * @since 2025/12/28
 */
@Getter
@AllArgsConstructor
public enum AiErrorCode {

    // ==================== 通用错误 (1000-1099) ====================
    UNKNOWN_ERROR(1000, "未知错误"),
    INVALID_PARAMETER(1001, "参数无效"),
    OPERATION_FAILED(1002, "操作失败"),

    // ==================== 模型相关错误 (1100-1199) ====================
    MODEL_NOT_FOUND(1100, "模型不存在"),
    MODEL_CONFIG_INVALID(1101, "模型配置无效"),
    MODEL_PROVIDER_NOT_FOUND(1102, "模型提供者不存在"),
    MODEL_CALL_FAILED(1103, "模型调用失败"),
    MODEL_RATE_LIMITED(1104, "模型调用频率受限"),

    // ==================== 知识库相关错误 (1200-1299) ====================
    KNOWLEDGE_BASE_NOT_FOUND(1200, "知识库不存在"),
    KNOWLEDGE_ITEM_NOT_FOUND(1201, "知识条目不存在"),
    KNOWLEDGE_CHUNK_NOT_FOUND(1202, "知识分片不存在"),
    VECTORIZATION_FAILED(1203, "向量化失败"),
    RETRIEVAL_FAILED(1204, "检索失败"),

    // ==================== 智能体相关错误 (1300-1399) ====================
    AGENT_NOT_FOUND(1300, "智能体不存在"),
    AGENT_CONFIG_INVALID(1301, "智能体配置无效"),
    TOOL_NOT_FOUND(1302, "工具不存在"),
    MCP_CONNECTION_FAILED(1303, "MCP 连接失败"),
    MCP_TOOL_EXECUTION_FAILED(1304, "MCP 工具执行失败"),

    // ==================== 对话相关错误 (1400-1499) ====================
    CONVERSATION_NOT_FOUND(1400, "对话不存在"),
    MESSAGE_SEND_FAILED(1401, "消息发送失败"),
    STREAM_INTERRUPTED(1402, "流式响应中断"),

    // ==================== 图谱相关错误 (1500-1599) ====================
    GRAPH_SERVICE_UNAVAILABLE(1500, "图谱服务不可用"),
    GRAPH_EXTRACTION_FAILED(1501, "图谱提取失败"),
    GRAPH_QUERY_FAILED(1502, "图谱查询失败");

    /**
     * 错误码
     */
    private final int code;

    /**
     * 错误描述
     */
    private final String message;

    /**
     * 获取完整错误码（带前缀）
     */
    public String getFullCode() {
        return "AI_" + code;
    }
}
