package com.wemirr.platform.ai.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.ai.domain.entity.ChatAgent;
import org.springframework.stereotype.Repository;

/**
 * @author xJh
 * @since 2025/11/4
 **/
@Repository
public interface ChatAgentMapper extends SuperMapper<ChatAgent> {
}
