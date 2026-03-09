package com.wemirr.platform.ai.service.impl;

import com.wemirr.framework.ai.core.enums.AiProvider;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.platform.ai.core.enums.MessageRole;
import com.wemirr.platform.ai.domain.dto.req.AssistantMessageSaveReq;
import com.wemirr.platform.ai.domain.dto.req.UserMessageSaveReq;
import com.wemirr.platform.ai.domain.entity.ConversationTurn;
import com.wemirr.platform.ai.repository.ConversationMessageMapper;
import com.wemirr.platform.ai.service.ConversationMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 会话消息服务实现类
 *
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
    public ConversationTurn saveUserMessage(UserMessageSaveReq req) {
        ConversationTurn message = null;
        try {
            Integer sequenceNum = getNextSequence(req.getConversationId());
            message = ConversationTurn.builder()
                    .conversationId(req.getConversationId())
                    .userId(req.getUserId())
                    .tenantId(req.getTenantId())
                    .role(MessageRole.USER)
                    .userInput(req.getRawContent())
                    .displayContent(req.getRawContent())
                    .modelOutput(req.getRawContent())
                    .inputTokens(req.getPromptTokens())
                    .outputTokens(0)
                    .sequenceNum(sequenceNum)
                    .deleted(false)
                    .build();
            messageMapper.insert(message);
            log.debug("用户消息已保存，conversationId={}, messageId={}", req.getConversationId(), message.getId());
        } catch (Exception e) {
            log.error("异步保存用户消息失败", e);
        }
        return message;
    }

    @Transactional(propagation = Propagation.NESTED)
    @Override
    public void saveAssistantMessageAsync(AssistantMessageSaveReq req) {
        try {
            // 查询父消息（最后一条用户消息）
            if (req.getParentMessageId() == null) {
                log.warn("会话 {} 中未找到用户消息，AI回复无法建立 parent 关系", req.getConversationId());
            }

            Integer sequenceNum = getNextSequence(req.getConversationId());
            Integer promptTokens = req.getPromptTokens();
            Integer completionTokens = req.getCompletionTokens();

            ConversationTurn message = ConversationTurn.builder()
                    .conversationId(req.getConversationId())
                    .userId(req.getUserId())
                    .tenantId(req.getTenantId())
                    .role(MessageRole.ASSISTANT)
                    .modelOutput(req.getRawContent())
                    .displayContent(req.getDisplayContent())
                    .finalPrompt(req.getPromptContent())
                    .modelName(req.getModelName())
                    .modelProvider(AiProvider.of(req.getModelProvider()))
                    .inputTokens(promptTokens)
                    .outputTokens(completionTokens)
                    .inferenceLatencyMs(req.getResponseLatencyMs())
                    .thinkingContent(req.getThinkingContent())
                    .sequenceNum(sequenceNum)
                    .previousTurnId(req.getParentMessageId())
                    .deleted(false)
                    .build();

            messageMapper.insert(message);
            String rawContent = req.getRawContent();
            log.info("AI回复消息已保存，conversationId={}, messageId={}, content={}",
                    req.getConversationId(), message.getId(), rawContent != null ? rawContent.substring(0, Math.min(50, rawContent.length())) : "null");
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
