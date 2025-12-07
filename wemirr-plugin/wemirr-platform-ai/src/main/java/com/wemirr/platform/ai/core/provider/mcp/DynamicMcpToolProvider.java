package com.wemirr.platform.ai.core.provider.mcp;

import com.wemirr.platform.ai.service.McpConnectionManager;
import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.mcp.McpToolExecutor;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.service.tool.ToolExecutor;
import dev.langchain4j.service.tool.ToolProvider;
import dev.langchain4j.service.tool.ToolProviderRequest;
import dev.langchain4j.service.tool.ToolProviderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 动态MCP工具提供者
 *
 * @author xJh
 * @since 2025/12/07
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DynamicMcpToolProvider implements ToolProvider {

    private final McpConnectionManager mcpConnectionManager;
    
    // 存储当前智能体的 MCP 配置 ID 列表
    private ThreadLocal<List<Long>> agentMcpServerIds = new ThreadLocal<>();

    /**
     * 设置当前智能体的 MCP 服务器配置
     */
    public void setAgentMcpServerIds(List<Long> mcpServerIds) {
        this.agentMcpServerIds.set(mcpServerIds);
    }

    /**
     * 清除当前线程的 MCP 配置
     */
    public void clearAgentMcpServerIds() {
        this.agentMcpServerIds.remove();
    }

    @Override
    public ToolProviderResult provideTools(ToolProviderRequest request) {
        // 获取当前智能体配置的 MCP Server ID 列表
        List<Long> mcpServerIds = agentMcpServerIds.get();
        
        // 如果智能体没有配置 MCP，返回空工具
        if (mcpServerIds == null || mcpServerIds.isEmpty()) {
            log.debug("No MCP servers configured for current agent");
            return ToolProviderResult.builder().build();
        }
        
        ToolProviderResult.Builder builder = ToolProviderResult.builder();
        int totalTools = 0;
        
        // 只加载智能体配置的 MCP 服务器
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
                log.warn("Failed to provide tools for MCP server ID: {}", configId, e);
            }
        }
        
        log.info("Loaded {} MCP tools from {} servers", totalTools, mcpServerIds.size());
        return builder.build();
    }
}
