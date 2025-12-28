package com.wemirr.platform.ai.core.provider.mcp;

import com.wemirr.platform.ai.service.McpConnectionManager;
import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.mcp.McpToolExecutor;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.service.tool.ToolExecutor;
import dev.langchain4j.service.tool.ToolProvider;
import dev.langchain4j.service.tool.ToolProviderRequest;
import dev.langchain4j.service.tool.ToolProviderResult;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 上下文感知的 MCP 工具提供者
 * <p>
 * 通过构造函数注入上下文，避免 ThreadLocal 在异步场景下的问题。
 * 每个智能体会话创建独立的实例。
 *
 * @author xJh
 * @since 2025/12/28
 */
@Slf4j
public class ContextAwareMcpToolProvider implements ToolProvider {

    private final McpConnectionManager mcpConnectionManager;
    private final McpToolProviderContext context;

    /**
     * 构造函数
     *
     * @param mcpConnectionManager MCP 连接管理器
     * @param context              MCP 工具上下文
     */
    public ContextAwareMcpToolProvider(McpConnectionManager mcpConnectionManager, McpToolProviderContext context) {
        this.mcpConnectionManager = mcpConnectionManager;
        this.context = context;
    }

    @Override
    public ToolProviderResult provideTools(ToolProviderRequest request) {
        // 检查上下文是否有效
        if (context == null || !context.isValid()) {
            log.debug("No MCP servers configured for current context");
            return ToolProviderResult.builder().build();
        }

        List<Long> mcpServerIds = context.getMcpServerIds();
        ToolProviderResult.Builder builder = ToolProviderResult.builder();
        int totalTools = 0;

        // 加载配置的 MCP 服务器工具
        for (Long configId : mcpServerIds) {
            try {
                McpClient client = mcpConnectionManager.getClient(configId);
                List<ToolSpecification> tools = client.listTools();

                if (tools != null) {
                    for (ToolSpecification originalSpec : tools) {
                        String originalToolName = originalSpec.name();

                        // 为避免工具名称冲突，添加前缀
                        String uniqueToolName = "mcp_" + configId + "_" + originalToolName;

                        // 创建新的 ToolSpecification，使用唯一名称
                        ToolSpecification newSpec = originalSpec.toBuilder()
                                .name(uniqueToolName)
                                .build();

                        // 使用官方的 McpToolExecutor，传入原始工具名称
                        ToolExecutor toolExecutor = new McpToolExecutor(client, originalToolName);

                        // 添加工具和执行器
                        builder.add(newSpec, toolExecutor);
                        totalTools++;
                    }
                }
            } catch (Exception e) {
                log.warn("Failed to provide tools for MCP server ID: {}, session: {}",
                        configId, context.getSessionId(), e);
            }
        }

        log.info("Loaded {} MCP tools from {} servers for agent: {}, session: {}",
                totalTools, mcpServerIds.size(), context.getAgentId(), context.getSessionId());
        return builder.build();
    }
}
