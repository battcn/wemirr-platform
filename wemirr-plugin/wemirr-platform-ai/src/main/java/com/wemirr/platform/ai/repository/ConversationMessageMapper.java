package com.wemirr.platform.ai.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.ai.domain.entity.ConversationTurn;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author xiao1
 * @since 2025-10
 */
@Repository
public interface ConversationMessageMapper extends SuperMapper<ConversationTurn> {

    /**
     * 查询会话中最大 sequence_num
     */
    @Select("SELECT MAX(sequence_num) FROM ai_conversation_turn WHERE conversation_id = #{conversationId}")
    Integer selectMaxSequenceByConversationId(@Param("conversationId") Long conversationId);

    /**
     * 更新消息状态（如重试、失败）
     */
    @Update("UPDATE ai_conversation_turn SET status = #{status}, updated_at = NOW() " +
            "WHERE id = #{messageId}")
    void updateStatusById(@Param("messageId") Long messageId, @Param("status") Integer status);
}
