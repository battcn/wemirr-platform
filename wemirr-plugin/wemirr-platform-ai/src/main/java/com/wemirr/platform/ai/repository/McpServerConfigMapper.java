package com.wemirr.platform.ai.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.ai.domain.entity.McpServerConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface McpServerConfigMapper extends SuperMapper<McpServerConfig> {
}

