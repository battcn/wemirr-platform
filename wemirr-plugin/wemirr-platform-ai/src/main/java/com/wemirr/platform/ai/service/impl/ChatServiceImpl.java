package com.wemirr.platform.ai.service.impl;

import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.ai.core.assistant.interfaces.ChatAssistant;
import com.wemirr.platform.ai.core.assistant.service.AssistantService;
import com.wemirr.platform.ai.core.assistant.service.RagAssistantParams;
import com.wemirr.platform.ai.core.enums.ConversationType;
import com.wemirr.platform.ai.core.enums.ModelType;
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

/**
 * @author xJh
 * @since 2025/10/11
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final AuthenticationContext authenticationContext;

    private final SseChatHelper sseChatHelper;

    private final ModelConfigService modelConfigService;

    private final AssistantService assistantService;

    private final ConversationMessageService conversationMessageService;

    private final KnowledgeBaseService knowledgeBaseService;

    private final ChatAgentService chatAgentService;

    private final ConversationService conversationService;

    @Override
    @Transactional
    public SseEmitter chatStream(AskReq askReq) {
        SseEmitter emitter = sseChatHelper.createEmitter(String.valueOf(authenticationContext.userId()));
        switch (askReq.getChatType()) {
            case NORMAL_TEXT -> handleTextChat(askReq, emitter);
            case KNOWLEDGE_BASE -> handleKnowledgeChat(askReq, emitter);
            case GENERAL_AGENT, PLATFORM_AGENT -> handleAgentChat(askReq, emitter);
//            case IMAGE_GENERATION -> handleImageGeneration(askReq, sseEmitter);
            default -> throw new IllegalArgumentException("不支持的对话类型: " + askReq.getChatType());
        }
        return emitter;
    }

    private void handleTextChat(AskReq askReq, SseEmitter sseEmitter) {
        Long userId = authenticationContext.userId();
        Long tenantId = authenticationContext.tenantId();
        Long conversationId = askReq.getConversationId();
        String userPrompt = askReq.getPrompt();
        String modelId = askReq.getModelId();
        ConversationMessage conversationMessage = conversationMessageService.saveUserMessage(
                conversationId,
                userId,
                tenantId,
                userPrompt,
                // 如拼接知识库
                userPrompt,
                0
        );


        ModelConfig modelConfig = modelConfigService.getOne(
                Wraps.<ModelConfig>lbQ().eq(ModelConfig::getId, modelId)
        );
        if (modelConfig == null) {
            throw new IllegalArgumentException("模型未配置: " + modelId);
        }
        modelConfig.setReturnThinking(askReq.getReturnThinking());
        modelConfig.setEnableWebSearch(askReq.getEnableWebSearch());
        ChatAssistant assistant = assistantService.createMemoryAssistant(modelConfig);
        TokenStream tokenStream = assistant.chatStream(
                askReq.getConversationId(),
                askReq.getPrompt()
        );
        sseChatHelper.chatStreamToSse(askReq,sseEmitter, tokenStream,(result)->{
            String rawContent = (String) result.get("content");
            Integer promptTokens = (Integer) result.get("inputTokens");
            Integer completionTokens = (Integer) result.get("outputTokens");
            conversationMessageService.saveAssistantMessageAsync(
                    conversationId,
                    userId,
                    tenantId,
                    rawContent,
                    rawContent, // 脱敏、格式化
                    null,
                    modelConfig.getModelName(),
                    modelConfig.getProvider(), // 或从 modelConfig 获取
                    promptTokens,
                    completionTokens,
                    null,
                    null,// 如 CoT、Tool Call,
                    conversationMessage.getId()
            );

        });
    }

    /**
     * 处理知识库对话
     * 实现RAG（检索增强生成）功能
     */
    private void handleKnowledgeChat(AskReq askReq, SseEmitter sseEmitter) {
        Long userId = authenticationContext.userId();
        Long tenantId = authenticationContext.tenantId();
        KnowledgeBase knowledgeBase = knowledgeBaseService.getById(askReq.getKbId());
        Conversation one = conversationService.getOne(Wraps.<Conversation>lbQ().eq(Conversation::getUserId, userId)
                .eq(Conversation::getKnowledgeBaseIds, askReq.getKbId()));
        if (one == null) {
            //新建一个会话
            one = Conversation.builder()
                    .knowledgeBaseIds(askReq.getKbId())
                    .title("")
                    .type(ConversationType.KNOWLEDGE_BASE)
                    .userId(userId).build();
            conversationService.save(one);
        }
        Long conversationId = one.getId();
        String userPrompt = askReq.getPrompt();
        ConversationMessage conversationMessage = conversationMessageService.saveUserMessage(
                conversationId,
                userId,
                tenantId,
                userPrompt,
                // 如拼接知识库
                userPrompt,
                0
        );

        try {
            // 4. 获取模型配置,todo 这些都可以做缓存map，用模型id代替，不用模型名称
            ModelConfig textModelConfig = modelConfigService.getOne(
                    Wraps.<ModelConfig>lbQ().eq(ModelConfig::getId, knowledgeBase.getChatModelId())
                            .eq(ModelConfig::getModelType, ModelType.TEXT)
            );
            textModelConfig.setEnableWebSearch(askReq.getEnableWebSearch());
            textModelConfig.setReturnThinking(askReq.getReturnThinking());
            ModelConfig embeddingModelConfig = modelConfigService.getOne(Wraps.<ModelConfig>lbQ().eq(ModelConfig::getId, knowledgeBase.getEmbeddingModelId())
                    .eq(ModelConfig::getModelType, ModelType.EMBEDDING));

            // 获取重排序模型配置（如果知识库配置了）
            ModelConfig rerankModelConfig = null;
            if (knowledgeBase.getRerankModelId() != null) {
                rerankModelConfig = modelConfigService.getOne(Wraps.<ModelConfig>lbQ()
                        .eq(ModelConfig::getId, knowledgeBase.getRerankModelId())
                        .eq(ModelConfig::getModelType, ModelType.RERANK));
            }

            // 构造统一参数并创建 RAG Assistant
            RagAssistantParams params =
                   RagAssistantParams.builder()
                            .kbId(askReq.getKbId())
                            .textModelConfig(textModelConfig)
                            .embeddingModelConfig(embeddingModelConfig)
                            .rerankModelConfig(rerankModelConfig)
                            .enableGraphRetrieval(knowledgeBase.getEnableGraph())
                            .build();
            ChatAssistant memoryRagAssistant = assistantService.createMemoryRagAssistant(params);
            TokenStream tokenStream = memoryRagAssistant.chatStream(conversationId, askReq.getPrompt());
            
            // 7. 处理流式响应
            sseChatHelper.chatStreamToSse(askReq, sseEmitter, tokenStream, (result) -> {
                String rawContent = (String) result.get("content");
                Integer promptTokens = (Integer) result.get("inputTokens");
                Integer completionTokens = (Integer) result.get("outputTokens");
                
                // 保存助手回复
                conversationMessageService.saveAssistantMessageAsync(
                        conversationId,
                        userId,
                        tenantId,
                        rawContent,
                        rawContent,
                        null,
                        textModelConfig.getModelName(),
                        textModelConfig.getProvider(),
                        promptTokens,
                        completionTokens,
                        null,
                        null,
                        conversationMessage.getId()
                );
            });
            
        } catch (Exception e) {
            log.error("知识库对话失败: kbId={}, query={}", askReq.getKbId(), userPrompt, e);
            try {
                sseEmitter.send(SseEmitter.event()
                        .name("error")
                        .data("知识库对话失败: " + e.getMessage()));
                sseEmitter.complete();
            } catch (Exception ex) {
                log.error("发送错误信息失败", ex);
            }
        }
    }

    private void handleAgentChat(AskReq askReq, SseEmitter sseEmitter) {
        Long userId = authenticationContext.userId();
        Long tenantId = authenticationContext.tenantId();
        String userPrompt = askReq.getPrompt();
        Conversation one = conversationService.getOne(Wraps.<Conversation>lbQ().eq(Conversation::getUserId, userId)
                .eq(Conversation::getAgentId, askReq.getAgentId()));
        if (one == null) {
            //新建一个会话
            one = Conversation.builder()
                    .agentId(askReq.getAgentId())
                    .title("")
                    .type(ConversationType.GENERAL_AGENT)
                    .userId(userId).build();
            conversationService.save(one);
        }
        Long conversationId = one.getId();

        ConversationMessage conversationMessage = conversationMessageService.saveUserMessage(
                conversationId,
                userId,
                tenantId,
                userPrompt,
                userPrompt,
                0
        );

        ChatAgent chatAgent = chatAgentService.getById(askReq.getAgentId());
        if (chatAgent == null) {
            throw new IllegalArgumentException("智能体不存在");
        }

        ModelConfig textModelConfig = modelConfigService.getOne(
                Wraps.<ModelConfig>lbQ().eq(ModelConfig::getId, chatAgent.getChatModelId())
                        .eq(ModelConfig::getModelType, ModelType.TEXT)
        );
        if (textModelConfig == null) {
            throw new IllegalArgumentException("模型配置不存在: " + chatAgent.getChatModelId());
        }

        RagAssistantParams ragParams = null;
        if (chatAgent.getKbId() != null) {
            KnowledgeBase knowledgeBase = knowledgeBaseService.getById(chatAgent.getKbId());
            if (knowledgeBase != null) {
                ModelConfig embeddingModelConfig = modelConfigService.getOne(Wraps.<ModelConfig>lbQ()
                        .eq(ModelConfig::getId, knowledgeBase.getEmbeddingModelId())
                        .eq(ModelConfig::getModelType, ModelType.EMBEDDING));
                
                // 获取重排序模型配置（如果知识库配置了）
                ModelConfig rerankModelConfig = null;
                if (knowledgeBase.getRerankModelId() != null) {
                    rerankModelConfig = modelConfigService.getOne(Wraps.<ModelConfig>lbQ()
                            .eq(ModelConfig::getId, knowledgeBase.getRerankModelId())
                            .eq(ModelConfig::getModelType, ModelType.RERANK));
                }
                
                ragParams = RagAssistantParams.builder()
                        .kbId(chatAgent.getKbId())
                        .textModelConfig(textModelConfig)
                        .embeddingModelConfig(embeddingModelConfig)
                        .rerankModelConfig(rerankModelConfig)
                        .enableGraphRetrieval(knowledgeBase.getEnableGraph())
                        .build();
            }
        }

        ChatAssistant assistant = assistantService.createAgentAssistant(chatAgent, textModelConfig, ragParams);
        TokenStream tokenStream = assistant.chatStream(conversationId, userPrompt);

        sseChatHelper.chatStreamToSse(askReq, sseEmitter, tokenStream, (result) -> {
            String rawContent = (String) result.get("content");
            Integer promptTokens = (Integer) result.get("inputTokens");
            Integer completionTokens = (Integer) result.get("outputTokens");
            conversationMessageService.saveAssistantMessageAsync(
                    conversationId,
                    userId,
                    tenantId,
                    rawContent,
                    rawContent,
                    null,
                    String.valueOf(chatAgent.getChatModelId()),//todo 转成模型名称
                    textModelConfig.getProvider(),
                    promptTokens,
                    completionTokens,
                    null,
                    null,
                    conversationMessage.getId()
            );
        });
    }
    
    /**
     * 构建增强的提示词
     * 将检索到的知识内容与用户问题结合
     */
    private String buildEnhancedPrompt(String userPrompt, List<String> retrievedContent) {
        if (retrievedContent == null || retrievedContent.isEmpty()) {
            return userPrompt;
        }
        
        StringBuilder enhancedPrompt = new StringBuilder();
        enhancedPrompt.append("基于以下知识内容回答问题：\n\n");
        
        // 添加检索到的知识内容
        for (int i = 0; i < retrievedContent.size(); i++) {
            enhancedPrompt.append("知识片段").append(i + 1).append("：\n");
            enhancedPrompt.append(retrievedContent.get(i)).append("\n\n");
        }
        
        enhancedPrompt.append("用户问题：").append(userPrompt).append("\n\n");
        enhancedPrompt.append("请基于上述知识内容回答用户问题，如果知识内容不足以回答问题，请说明。");
        
        return enhancedPrompt.toString();
    }



}
