package com.wemirr.platform.ai.service;

import dev.langchain4j.mcp.client.McpClient;

/**
 * MCP连接管理器
 *
 * @author xJh
 * @since 2025/12/07
 */
public interface McpConnectionManager {

    /**
     * 获取或创建MCP客户端
     *
     * @param configId 配置ID
     * @return McpClient 实例
     */
    McpClient getClient(Long configId);

    /**
     * 关闭并移除客户端
     *
     * @param configId 配置ID
     */
    void closeClient(Long configId);
    
    /**
     * 刷新连接
     * 
     * @param configId 配置ID
     */
    void refreshClient(Long configId);
}

