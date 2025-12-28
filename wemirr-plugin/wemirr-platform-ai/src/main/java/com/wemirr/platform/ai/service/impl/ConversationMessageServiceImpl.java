package com.wemirr.platform.ai.service.impl;

import com.wemirr.framework.ai.core.enums.AiProvider;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.platform.ai.core.enums.MessageRole;
import com.wemirr.platform.ai.domain.entity.ConversationTurn;
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
public class ConversationMessageServiceImpl extends SuperServiceImpl<ConversationMessageMapper, ConversationTurn> implements ConversationMessageService {

    private final ConversationMessageMapper messageMapper;

    @Transactional(propagation = Propagation.NESTED)
    @Override
    public ConversationTurn saveUserMessage(Long conversationId, Long userId,
                                            Long tenantId, String rawContent, String promptContent, Integer promptTokens) {
        ConversationTurn message = null;
        try {
            Integer sequenceNum = getNextSequence(conversationId);
            message = ConversationTurn.builder()
                    .conversationId(conversationId)
                    .userId(userId)
                    .tenantId(tenantId)
                    .role(MessageRole.USER)
                    .userInput(rawContent)
                    .displayContent(rawContent)
                    .modelOutput(rawContent)
                    .inputTokens(promptTokens)
                    .outputTokens(0)
//                    .totalTokens(promptTokens)
                    .sequenceNum(sequenceNum)
                    // 用户消息无父消息
//                    .parentMessageId(null)
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

            ConversationTurn message = ConversationTurn.builder()
                    .conversationId(conversationId)
                    .userId(userId)
                    .tenantId(tenantId)
                    .role(MessageRole.ASSISTANT)
//                    .originalContent(rawContent)
//                    .renderedContent(displayContent)
//                    .processedContent(promptContent)
                    .modelName(modelName)
                    .modelProvider(AiProvider.of(modelProvider))
//                    .promptTokens(promptTokens)
//                    .completionTokens(completionTokens)
//                    .totalTokens(totalTokens)
//                    .responseLatencyMs(responseLatencyMs)
                    .thinkingContent(thinkingContent)
                    .sequenceNum(sequenceNum)
//                    .parentMessageId(parentMessageId)
                    .deleted(false)
                    .build();

            messageMapper.insert(message);
            log.info("AI回复消息已保存，conversationId={}, messageId={}", conversationId, message.getId());
        } catch (Exception e) {
            log.error("异步保存AI回复消息失败", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
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
