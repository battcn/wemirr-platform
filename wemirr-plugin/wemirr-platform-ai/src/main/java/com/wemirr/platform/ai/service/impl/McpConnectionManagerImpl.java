package com.wemirr.platform.ai.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wemirr.platform.ai.domain.entity.McpServerConfig;
import com.wemirr.platform.ai.repository.McpServerConfigMapper;
import com.wemirr.platform.ai.service.McpConnectionManager;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.mcp.client.transport.McpTransport;
import dev.langchain4j.mcp.client.transport.http.StreamableHttpMcpTransport;
import dev.langchain4j.mcp.client.transport.stdio.StdioMcpTransport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * MCP连接管理器实现
 *
 * @author xJh
 * @since 2025/12/07
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class McpConnectionManagerImpl implements McpConnectionManager {

    private final McpServerConfigMapper mcpServerConfigMapper;
    private final ObjectMapper objectMapper;
    
    // 缓存客户端实例: configId -> McpClient
    private final Map<Long, McpClient> clientCache = new ConcurrentHashMap<>();

    @Override
    public McpClient getClient(Long configId) {
        if (clientCache.containsKey(configId)) {
            return clientCache.get(configId);
        }
        
        McpServerConfig config = mcpServerConfigMapper.selectById(configId);
        if (config == null) {
            throw new IllegalArgumentException("MCP config not found: " + configId);
        }
        
        if (Boolean.FALSE.equals(config.getStatus())) {
            throw new IllegalStateException("MCP server is disabled: " + config.getName());
        }

        McpClient client = createClient(config);
        clientCache.put(configId, client);
        return client;
    }

    @Override
    public void closeClient(Long configId) {
        McpClient client = clientCache.remove(configId);
        if (client != null) {
            try {
                client.close();
            } catch (Exception e) {
                log.error("Error closing MCP client: {}", configId, e);
            }
        }
    }

    @Override
    public void refreshClient(Long configId) {
        closeClient(configId);
        // 下次 getClient 时会自动重新创建
    }

    private McpClient createClient(McpServerConfig config) {
        McpTransport transport;
        
        if ("STDIO".equalsIgnoreCase(config.getType())) {
            Map<String, String> env = parseEnv(config.getEnv());
            List<String> args = parseArgs(config.getArgs());
            
            List<String> fullCommand = new ArrayList<>();
            fullCommand.add(config.getCommand());
            if (args != null && !args.isEmpty()) {
                fullCommand.addAll(args);
            }
            
            StdioMcpTransport.Builder builder = StdioMcpTransport.builder()
                    .command(fullCommand)
                    .logEvents(true);
            
            if (env != null && !env.isEmpty()) {
                builder.environment(env);
            }
            
            transport = builder.build();
        } else if ("SSE".equalsIgnoreCase(config.getType()) || "HTTP".equalsIgnoreCase(config.getType())) {
            transport = StreamableHttpMcpTransport.builder()
                    .url(config.getUrl())
                    .logRequests(true)
                    .logResponses(true)
                    .build();
        } else {
            throw new IllegalArgumentException("Unsupported MCP transport type: " + config.getType());
        }

        return new DefaultMcpClient.Builder()
                .key("wemirr-platform-ai-" + config.getId())
                .transport(transport)
                .toolExecutionTimeout(Duration.ofSeconds(60))
                .build();
    }

    private Map<String, String> parseEnv(String json) {
        if (json == null || json.isEmpty()) {
            return Collections.emptyMap();
        }
        try {
            return objectMapper.readValue(json, new TypeReference<Map<String, String>>() {});
        } catch (Exception e) {
            log.error("Failed to parse env JSON", e);
            return Collections.emptyMap();
        }
    }

    private List<String> parseArgs(String json) {
        if (json == null || json.isEmpty()) {
            return Collections.emptyList();
        }
        try {
            return objectMapper.readValue(json, new TypeReference<List<String>>() {});
        } catch (Exception e) {
            log.error("Failed to parse args JSON", e);
            return Collections.emptyList();
        }
    }
}

