package com.wemirr.platform.ai.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.ai.domain.entity.ChatMsgStore;
import org.springframework.stereotype.Repository;

/**
 * @author xJh
 * @since 2025/10/11
 **/
@Repository
public interface ChatMsgStoreMapper extends SuperMapper<ChatMsgStore> {
}
