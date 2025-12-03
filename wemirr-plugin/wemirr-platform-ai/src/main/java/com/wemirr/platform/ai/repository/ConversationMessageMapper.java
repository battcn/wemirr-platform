package com.wemirr.platform.ai.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.ai.domain.entity.ConversationMessage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author xiao1
 * @since 2025-10
 */
@Repository
public interface ConversationMessageMapper extends SuperMapper<ConversationMessage> {

    /**
     * 查询会话中最后一条用户消息（用于设置 parent_message_id）
     */
    @Select("SELECT id FROM ai_conversation " +
            "WHERE conversation_id = #{conversationId} " +
            "  AND role = 'user' " +
            "ORDER BY sequence_num DESC LIMIT 1")
    Long findLastUserMessageId(@Param("conversationId") Long conversationId);

    /**
     * 查询会话中最后一条 assistant 消息
     */
    @Select("SELECT id FROM ai_conversation_message " +
            "WHERE conversation_id = #{conversationId} " +
            "  AND role = 'assistant' " +
            "ORDER BY sequence_num DESC LIMIT 1")
    Long findLastAssistantMessageId(@Param("conversationId") Long conversationId);

    /**
     * 查询会话中最大 sequence_num
     */
    @Select("SELECT MAX(sequence_num) FROM ai_conversation_message WHERE conversation_id = #{conversationId}")
    Integer selectMaxSequenceByConversationId(@Param("conversationId") Long conversationId);

    /**
     * 更新消息状态（如重试、失败）
     */
    @Update("UPDATE ai_conversation_message SET status = #{status}, updated_at = NOW() " +
            "WHERE id = #{messageId}")
    void updateStatusById(@Param("messageId") Long messageId, @Param("status") Integer status);
}
