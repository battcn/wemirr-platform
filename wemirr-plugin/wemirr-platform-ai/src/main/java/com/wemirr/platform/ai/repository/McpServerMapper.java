package com.wemirr.platform.ai.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.ai.domain.entity.McpServerEntity;
import org.springframework.stereotype.Repository;

/**
 * @author battcn
 */
@Repository
public interface McpServerMapper extends SuperMapper<McpServerEntity> {
}

