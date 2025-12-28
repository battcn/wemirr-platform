package com.wemirr.platform.ai.core.provider.mcp;

import com.wemirr.platform.ai.service.McpConnectionManager;
import dev.langchain4j.service.tool.ToolProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * MCP 工具提供者工厂
 * <p>
 * 负责创建上下文感知的 MCP 工具提供者实例
 *
 * @author xJh
 * @since 2025/12/28
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class McpToolProviderFactory {

    private final McpConnectionManager mcpConnectionManager;

    /**
     * 为智能体创建 MCP 工具提供者
     *
     * @param agentId      智能体 ID
     * @param mcpServerIds MCP 服务器配置 ID 列表
     * @param sessionId    会话 ID
     * @return MCP 工具提供者
     */
    public ToolProvider create(Long agentId, List<Long> mcpServerIds, String sessionId) {
        if (mcpServerIds == null || mcpServerIds.isEmpty()) {
            log.debug("No MCP servers configured for agent: {}", agentId);
            return null;
        }

        McpToolProviderContext context = McpToolProviderContext.builder()
                .agentId(agentId)
                .mcpServerIds(mcpServerIds)
                .sessionId(sessionId)
                .build();

        log.debug("Creating MCP tool provider for agent: {}, servers: {}", agentId, mcpServerIds);
        return new ContextAwareMcpToolProvider(mcpConnectionManager, context);
    }

    /**
     * 为智能体创建 MCP 工具提供者（无会话 ID）
     *
     * @param agentId      智能体 ID
     * @param mcpServerIds MCP 服务器配置 ID 列表
     * @return MCP 工具提供者
     */
    public ToolProvider create(Long agentId, List<Long> mcpServerIds) {
        return create(agentId, mcpServerIds, null);
    }
}
