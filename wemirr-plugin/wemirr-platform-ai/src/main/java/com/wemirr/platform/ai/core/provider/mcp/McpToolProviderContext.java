package com.wemirr.platform.ai.core.provider.mcp;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * MCP 工具提供者上下文
 * <p>
 * 封装 MCP 工具调用所需的上下文信息，避免使用 ThreadLocal 导致的异步问题
 *
 * @author xJh
 * @since 2025/12/28
 */
@Data
@Builder
public class McpToolProviderContext {

    /**
     * 智能体 ID
     */
    private Long agentId;

    /**
     * MCP 服务器配置 ID 列表
     */
    private List<Long> mcpServerIds;

    /**
     * 会话 ID（用于日志追踪）
     */
    private String sessionId;

    /**
     * 是否有效
     */
    public boolean isValid() {
        return mcpServerIds != null && !mcpServerIds.isEmpty();
    }
}
