package com.wemirr.platform.ai.service;

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.ai.domain.entity.ConversationMessage;

/**
 * @author xiao1
 * @since 2025-10
 */
public interface ConversationMessageService extends SuperService<ConversationMessage> {
    /**
     * 异步保存用户消息
     */
    ConversationMessage saveUserMessage(
            Long conversationId,
            Long userId,
            Long tenantId,
            String rawContent,
            String promptContent,
            Integer promptTokens
    );

    /**
     * 异步保存 AI 回复消息
     */
    void saveAssistantMessageAsync(
            Long conversationId,
            Long userId,
            Long tenantId,
            String rawContent,
            String displayContent,
            String promptContent,
            String modelName,
            String modelProvider,
            Integer promptTokens,
            Integer completionTokens,
            Long responseLatencyMs,
            String thinkingContent,
            Long parentMessageId
    );

    /**
     * 更新消息状态（如重试、失败）
     */
    void updateMessageStatusAsync(Long messageId, Integer status);
}
