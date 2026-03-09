package com.wemirr.platform.ai.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.ai.domain.entity.McpServer;
import org.springframework.stereotype.Repository;

/**
 * MCP服务器配置 Mapper
 * 
 * @author battcn
 */
@Repository
public interface McpServerMapper extends SuperMapper<McpServer> {
}

