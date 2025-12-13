package com.wemirr.platform.ai.service.impl;

import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.platform.ai.domain.entity.ConversationMessage;
import com.wemirr.platform.ai.repository.ConversationMessageMapper;
import com.wemirr.platform.ai.service.ConversationMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiao1
 * @since 2025-10
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ConversationMessageServiceImpl extends SuperServiceImpl<ConversationMessageMapper, ConversationMessage> implements ConversationMessageService {

    private final ConversationMessageMapper messageMapper;

    @Transactional(propagation = Propagation.NESTED)
    @Override
    public ConversationMessage saveUserMessage(
            Long conversationId,
            Long userId,
            Long tenantId,
            String rawContent,
            String promptContent,
            Integer promptTokens
    ) {
        ConversationMessage message = null;
        try {
            Integer sequenceNum = getNextSequence(conversationId);
            message = ConversationMessage.builder()
                    .conversationId(conversationId)
                    .userId(userId)
                    .tenantId(tenantId)
                    .role("user")
                    .rawContent(rawContent)
                    .promptContent(promptContent)
                    // 用户输入无需处理
                    .displayContent(rawContent)
                    .promptTokens(promptTokens)
                    .completionTokens(0)
                    .totalTokens(promptTokens)
                    .sequenceNum(sequenceNum)
                    // 用户消息无父消息
                    .parentMessageId(null)
                    .deleted(false)
                    .build();
            messageMapper.insert(message);
            log.debug("用户消息已保存，conversationId={}, messageId={}", conversationId, message.getId());
        } catch (Exception e) {
            log.error("异步保存用户消息失败", e);
        }
        return message;
    }

    @Transactional(propagation = Propagation.NESTED)
    @Override
    public void saveAssistantMessageAsync(
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
    ) {
        try {
            // 查询父消息（最后一条用户消息）
            if (parentMessageId == null) {
                log.warn("会话 {} 中未找到用户消息，AI回复无法建立 parent 关系", conversationId);
            }

            Integer sequenceNum = getNextSequence(conversationId);
            Integer totalTokens = (promptTokens != null ? promptTokens : 0) +
                    (completionTokens != null ? completionTokens : 0);

            ConversationMessage message = ConversationMessage.builder()
                    .conversationId(conversationId)
                    .userId(userId)
                    .tenantId(tenantId)
                    .role("assistant")
                    .rawContent(rawContent)
                    .displayContent(displayContent)
                    .promptContent(promptContent)
                    .modelName(modelName)
                    .modelProvider(modelProvider)
                    .promptTokens(promptTokens)
                    .completionTokens(completionTokens)
                    .totalTokens(totalTokens)
                    .responseLatencyMs(responseLatencyMs)
                    .thinkingContent(thinkingContent)
                    .sequenceNum(sequenceNum)
                    .parentMessageId(parentMessageId)
                    .deleted(false)
                    .build();

            messageMapper.insert(message);
            log.info("AI回复消息已保存，conversationId={}, messageId={}", conversationId, message.getId());
        } catch (Exception e) {
            log.error("异步保存AI回复消息失败", e);
        }
    }

    @Transactional(propagation = Propagation.NESTED)
    @Override
    public void updateMessageStatusAsync(Long messageId, Integer status) {
        try {
            messageMapper.updateStatusById(messageId, status);
            log.debug("消息状态已更新，messageId={}, status={}", messageId, status);
        } catch (Exception e) {
            log.error("更新消息状态失败", e);
        }
    }

    private Integer getNextSequence(Long conversationId) {
        Integer maxSeq = messageMapper.selectMaxSequenceByConversationId(conversationId);
        return maxSeq == null ? 1 : maxSeq + 1;
    }
}
