package com.wemirr.platform.ai.service.impl;

import com.wemirr.framework.ai.core.enums.ModelType;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.ai.core.assistant.interfaces.ChatAssistant;
import com.wemirr.platform.ai.core.assistant.service.AssistantService;
import com.wemirr.platform.ai.core.assistant.service.RagAssistantParams;
import com.wemirr.platform.ai.core.constant.AiServiceConstants;
import com.wemirr.platform.ai.core.enums.ConversationType;
import com.wemirr.platform.ai.core.helper.ModelConfigRetriever;
import com.wemirr.platform.ai.core.sse.SseChatHelper;
import com.wemirr.platform.ai.domain.dto.req.AskReq;
import com.wemirr.platform.ai.domain.entity.*;
import com.wemirr.platform.ai.service.*;
import dev.langchain4j.service.TokenStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.Map;

/**
 * 对话服务实现
 * <p>
 * 处理不同类型的对话请求：普通文本对话、知识库对话（RAG）、智能体对话
 *
 * @author xJh
 * @since 2025/10/11
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final AuthenticationContext authenticationContext;
    private final SseChatHelper sseChatHelper;
    private final ModelConfigRetriever modelConfigRetriever;
    private final AssistantService assistantService;
    private final ConversationMessageService conversationMessageService;
    private final KnowledgeBaseService knowledgeBaseService;
    private final ChatAgentService chatAgentService;
    private final ConversationService conversationService;

    @Override
    @Transactional
    public SseEmitter chatStream(AskReq askReq) {
        log.info("开始处理对话请求: chatType={}, userId={}",
                askReq.getChatType(), authenticationContext.userId());

        SseEmitter emitter = sseChatHelper.createEmitter(String.valueOf(authenticationContext.userId()));

        switch (askReq.getChatType()) {
            case NORMAL_TEXT -> handleTextChat(askReq, emitter);
            case KNOWLEDGE_BASE -> handleKnowledgeChat(askReq, emitter);
            case GENERAL_AGENT, PLATFORM_AGENT -> handleAgentChat(askReq, emitter);
            default ->
                    throw CheckedException.badRequest(String.format(AiServiceConstants.ERROR_UNSUPPORTED_CHAT_TYPE, askReq.getChatType()));
        }

        return emitter;
    }

    /**
     * 处理普通文本对话
     */
    private void handleTextChat(AskReq askReq, SseEmitter sseEmitter) {
        log.debug("处理普通文本对话: conversationId={}, modelId={}",
                askReq.getConversationId(), askReq.getModelId());

        Long userId = authenticationContext.userId();
        Long tenantId = authenticationContext.tenantId();
        Long conversationId = askReq.getConversationId();
        String userPrompt = askReq.getPrompt();

        // 保存用户消息
        ConversationMessage conversationMessage = conversationMessageService.saveUserMessage(
                conversationId, userId, tenantId, userPrompt, userPrompt, 0
        );

        // 获取模型配置
        ModelEntity modelEntity = modelConfigRetriever.getRequiredModel(Long.valueOf(askReq.getModelId()));
        modelEntity.setReturnThinking(askReq.getReturnThinking());
        modelEntity.setEnableWebSearch(askReq.getEnableWebSearch());

        // 创建助手并执行对话
        ChatAssistant assistant = assistantService.createMemoryAssistant(modelEntity);
        TokenStream tokenStream = assistant.chatStream(conversationId, userPrompt);

        // 处理流式响应
        sseChatHelper.chatStreamToSse(askReq, sseEmitter, tokenStream, result ->
                saveAssistantMessage(conversationId, userId, tenantId, modelEntity, conversationMessage.getId(), result)
        );
    }

    /**
     * 处理知识库对话（RAG）
     */
    private void handleKnowledgeChat(AskReq askReq, SseEmitter sseEmitter) {
        log.debug("处理知识库对话: kbId={}", askReq.getKbId());

        Long userId = authenticationContext.userId();
        Long tenantId = authenticationContext.tenantId();
        String userPrompt = askReq.getPrompt();

        // 获取或创建会话
        KnowledgeBase knowledgeBase = knowledgeBaseService.getById(askReq.getKbId());
        Conversation conversation = getOrCreateConversation(userId, askReq.getKbId(), ConversationType.KNOWLEDGE_BASE);
        Long conversationId = conversation.getId();

        // 保存用户消息
        ConversationMessage conversationMessage = conversationMessageService.saveUserMessage(
                conversationId, userId, tenantId, userPrompt, userPrompt, 0
        );

        try {
            // 获取模型配置
            ModelEntity textModelEntity = modelConfigRetriever.getModelByIdAndType(
                    knowledgeBase.getChatModelId(), ModelType.TEXT);
            textModelEntity.setEnableWebSearch(askReq.getEnableWebSearch());
            textModelEntity.setReturnThinking(askReq.getReturnThinking());

            ModelEntity embeddingModelEntity = modelConfigRetriever.getModelByIdAndType(
                    knowledgeBase.getEmbedModelId(), ModelType.EMBEDDING);

            // 获取重排序模型配置（可选）
            ModelEntity rerankModelEntity = modelConfigRetriever.getModel(knowledgeBase.getRerankModelId())
                    .orElse(null);

            // 构建RAG参数并创建助手
            RagAssistantParams params = RagAssistantParams.builder()
                    .kbId(askReq.getKbId())
                    .textModelEntity(textModelEntity)
                    .embeddingModelEntity(embeddingModelEntity)
                    .rerankModelEntity(rerankModelEntity)
                    .enableGraphRetrieval(knowledgeBase.getEnableGraph())
                    .build();

            ChatAssistant assistant = assistantService.createMemoryRagAssistant(params);
            TokenStream tokenStream = assistant.chatStream(conversationId, userPrompt);

            // 处理流式响应
            sseChatHelper.chatStreamToSse(askReq, sseEmitter, tokenStream, result ->
                    saveAssistantMessage(conversationId, userId, tenantId, textModelEntity, conversationMessage.getId(), result)
            );

        } catch (Exception e) {
            log.error("知识库对话失败: kbId={}, query={}", askReq.getKbId(), userPrompt, e);
            handleChatError(sseEmitter, "知识库对话失败: " + e.getMessage());
        }
    }

    /**
     * 处理智能体对话
     */
    private void handleAgentChat(AskReq askReq, SseEmitter sseEmitter) {
        log.debug("处理智能体对话: agentId={}", askReq.getAgentId());

        Long userId = authenticationContext.userId();
        Long tenantId = authenticationContext.tenantId();
        String userPrompt = askReq.getPrompt();

        // 获取或创建会话
        Conversation conversation = getOrCreateConversation(userId, askReq.getAgentId(), ConversationType.GENERAL_AGENT);
        Long conversationId = conversation.getId();

        // 保存用户消息
        ConversationMessage conversationMessage = conversationMessageService.saveUserMessage(
                conversationId, userId, tenantId, userPrompt, userPrompt, 0
        );

        // 获取智能体配置
        ChatAgent chatAgent = chatAgentService.getById(askReq.getAgentId());
        if (chatAgent == null) {
            throw new IllegalArgumentException(
                    String.format(AiServiceConstants.ERROR_AGENT_NOT_FOUND, askReq.getAgentId())
            );
        }

        // 获取文本模型配置
        ModelEntity textModelEntity = modelConfigRetriever.getRequiredModel(
                chatAgent.getModelId(), ModelType.TEXT);

        // 构建RAG参数（如果智能体关联了知识库）
        RagAssistantParams ragParams = buildRagParamsForAgent(chatAgent, textModelEntity);

        // 创建智能体助手并执行对话
        ChatAssistant assistant = assistantService.createAgentAssistant(chatAgent, textModelEntity, ragParams);
        TokenStream tokenStream = assistant.chatStream(conversationId, userPrompt);

        // 处理流式响应
        sseChatHelper.chatStreamToSse(askReq, sseEmitter, tokenStream, result ->
                saveAssistantMessage(conversationId, userId, tenantId, textModelEntity, conversationMessage.getId(), result)
        );
    }

    /**
     * 获取或创建会话
     */
    private Conversation getOrCreateConversation(Long userId, Long relatedId, ConversationType type) {
        Conversation conversation;

        if (type == ConversationType.KNOWLEDGE_BASE) {
            conversation = conversationService.getOne(
                    Wraps.<Conversation>lbQ()
                            .eq(Conversation::getUserId, userId)
                            .eq(Conversation::getKnowledgeBaseIds, relatedId)
            );
        } else {
            conversation = conversationService.getOne(
                    Wraps.<Conversation>lbQ()
                            .eq(Conversation::getUserId, userId)
                            .eq(Conversation::getAgentId, relatedId)
            );
        }

        if (conversation == null) {
            Conversation.ConversationBuilder builder = Conversation.builder()
                    .title("")
                    .type(type)
                    .userId(userId);

            if (type == ConversationType.KNOWLEDGE_BASE) {
                builder.knowledgeBaseIds(List.of(relatedId));
            } else {
                builder.agentId(relatedId);
            }

            conversation = builder.build();
            conversationService.save(conversation);
            log.info("创建新会话: conversationId={}, type={}, userId={}",
                    conversation.getId(), type, userId);
        }

        return conversation;
    }

    /**
     * 为智能体构建RAG参数
     */
    private RagAssistantParams buildRagParamsForAgent(ChatAgent chatAgent, ModelEntity textModelEntity) {
        if (chatAgent.getKbId() == null) {
            return null;
        }

        KnowledgeBase knowledgeBase = knowledgeBaseService.getById(chatAgent.getKbId());
        if (knowledgeBase == null) {
            log.warn("智能体关联的知识库不存在: agentId={}, kbId={}",
                    chatAgent.getId(), chatAgent.getKbId());
            return null;
        }

        ModelEntity embeddingModelEntity = modelConfigRetriever.getModelByIdAndType(
                knowledgeBase.getEmbedModelId(), ModelType.EMBEDDING);

        ModelEntity rerankModelEntity = modelConfigRetriever.getModel(knowledgeBase.getRerankModelId())
                .orElse(null);

        return RagAssistantParams.builder()
                .kbId(chatAgent.getKbId())
                .textModelEntity(textModelEntity)
                .embeddingModelEntity(embeddingModelEntity)
                .rerankModelEntity(rerankModelEntity)
                .enableGraphRetrieval(knowledgeBase.getEnableGraph())
                .build();
    }

    /**
     * 保存助手消息
     */
    private void saveAssistantMessage(Long conversationId, Long userId, Long tenantId,
                                      ModelEntity modelEntity, Long parentMessageId,
                                      Map<String, Object> result) {
        String rawContent = (String) result.get("content");
        Integer promptTokens = (Integer) result.get("inputTokens");
        Integer completionTokens = (Integer) result.get("outputTokens");

        conversationMessageService.saveAssistantMessageAsync(
                conversationId, userId, tenantId,
                rawContent, rawContent, null,
                modelEntity.getName(), modelEntity.getProvider().getLabel(),
                promptTokens, completionTokens,
                null, null, parentMessageId
        );
    }

    /**
     * 处理对话错误
     */
    private void handleChatError(SseEmitter sseEmitter, String errorMessage) {
        try {
            sseEmitter.send(SseEmitter.event()
                    .name("error")
                    .data(errorMessage));
            sseEmitter.complete();
        } catch (Exception ex) {
            log.error("发送错误信息失败", ex);
        }
    }
}