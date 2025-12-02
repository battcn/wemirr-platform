package com.wemirr.platform.ai.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.ai.domain.entity.Conversation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author xJh
 * @date 2025/10/11
 **/
@Mapper
@Repository
public interface ConversationMapper extends SuperMapper<Conversation> {
}
