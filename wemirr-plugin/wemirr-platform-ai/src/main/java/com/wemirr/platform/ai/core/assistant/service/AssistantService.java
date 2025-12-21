package com.wemirr.platform.ai.core.assistant.service;

import cn.hutool.core.collection.CollUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wemirr.platform.ai.core.assistant.interfaces.ChatAssistant;
import com.wemirr.platform.ai.core.provider.embedding.EmbeddingModelProviderRegistry;
import com.wemirr.platform.ai.core.provider.graph.GraphContentRetriever;
import com.wemirr.platform.ai.core.provider.graph.GraphRagService;
import com.wemirr.platform.ai.core.provider.mcp.DynamicMcpToolProvider;
import com.wemirr.platform.ai.core.provider.scoring.ScoringModelService;
import com.wemirr.platform.ai.core.provider.text.TextModelService;
import com.wemirr.platform.ai.core.provider.vectorStore.EnhancedVectorStoreFactory;
import com.wemirr.platform.ai.core.rag.TranslationQueryTransformer;
import com.wemirr.platform.ai.domain.entity.ChatAgent;
import com.wemirr.platform.ai.domain.entity.KnowledgeBase;
import com.wemirr.platform.ai.domain.entity.ModelConfig;
import com.wemirr.platform.ai.service.KnowledgeBaseService;
import com.wemirr.platform.ai.service.ToolService;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.scoring.ScoringModel;
import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.rag.content.aggregator.ContentAggregator;
import dev.langchain4j.rag.content.aggregator.DefaultContentAggregator;
import dev.langchain4j.rag.content.aggregator.ReRankingContentAggregator;
import dev.langchain4j.rag.content.injector.ContentInjector;
import dev.langchain4j.rag.content.injector.DefaultContentInjector;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.rag.query.Query;
import dev.langchain4j.rag.query.router.DefaultQueryRouter;
import dev.langchain4j.rag.query.router.QueryRouter;
import dev.langchain4j.rag.query.transformer.CompressingQueryTransformer;
import dev.langchain4j.rag.query.transformer.QueryTransformer;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author xJh
 * @since 2025/10/11
 * todo Langchain4j暂未集成稀疏向量用于多路检索
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class AssistantService {

    private static final int DEFAULT_MAX_MESSAGES = 10;

    private final TextModelService textModelService;

    private final ChatMemoryStore chatMemoryStore;

    private final EnhancedVectorStoreFactory vectorStoreFactory;

    private final KnowledgeBaseService knowledgeBaseService;

    private final EmbeddingModelProviderRegistry embeddingModelProviderRegistry;

    private final ApplicationContext applicationContext;

    private final ObjectMapper objectMapper;

    private final Executor executor = Executors.newCachedThreadPool();

    private final ToolService toolService;

    private final DynamicMcpToolProvider dynamicMcpToolProvider;

    private final ScoringModelService scoringModelService;

    /**
     * GraphRAG 服务（可选，仅在启用图谱功能时注入）
     */
    @Autowired(required = false)
    private GraphRagService graphRagService;

    /**
     * 创建普通记忆对话的 Assistant
     * @param modelConfig 模型配置
     * @return ChatAssistant 实例
     */
    public ChatAssistant createMemoryAssistant(ModelConfig modelConfig) {

        ChatModel chatModel = textModelService.model(modelConfig);
        StreamingChatModel streamModel = textModelService.streamModel(modelConfig);

        return AiServices.builder(ChatAssistant.class)
                .chatModel(chatModel)
                 .streamingChatModel(streamModel)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(DEFAULT_MAX_MESSAGES))
                .chatMemoryProvider(createMemoryProvider())
                .build();
    }


    /**
     * 创建智能体对话助手 (支持Tools和RAG、MCP工具)
     */
    @SneakyThrows
    public ChatAssistant createAgentAssistant(ChatAgent chatAgent, ModelConfig modelConfig, RagAssistantParams ragParams) {
        ChatModel chatModel = textModelService.model(modelConfig);
        StreamingChatModel streamModel = textModelService.streamModel(modelConfig);

        var builder = AiServices.builder(ChatAssistant.class)
                .chatModel(chatModel)
                .streamingChatModel(streamModel)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(DEFAULT_MAX_MESSAGES))
                .chatMemoryProvider(createMemoryProvider());

        // Configure Tools
        if (chatAgent.getTools() != null && !chatAgent.getTools().isEmpty()) {
            List<String> toolNames = objectMapper.readValue(chatAgent.getTools(), new TypeReference<List<String>>() {});
            if (toolNames != null && !toolNames.isEmpty()) {
                List<Object> tools = toolNames.stream()
                        .map(name -> {
                            try {
                                return applicationContext.getBean(name);
                            } catch (Exception e) {
                                log.warn("Tool bean not found: {}", name);
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .toList();
                if (!tools.isEmpty()) {
                    builder.tools(tools);
                }
            }
        }
        
        // 配置 MCP 工具提供者
        // 先设置智能体的 MCP 服务器配置
        if (chatAgent.getMcpServerIds() != null && !chatAgent.getMcpServerIds().isEmpty()) {
            try {
                List<Long> mcpServerIds = objectMapper.readValue(chatAgent.getMcpServerIds(), new TypeReference<List<Long>>() {});
                dynamicMcpToolProvider.setAgentMcpServerIds(mcpServerIds);
                builder.toolProvider(dynamicMcpToolProvider);
            } catch (Exception e) {
                log.error("Failed to parse MCP server IDs for agent: {}", chatAgent.getId(), e);
            }
        }

        //todo 如果没有预制系统预设，则使用默认。有的话使用系统预设
        builder.systemMessageProvider(memoryId -> {
            StringBuilder sb = new StringBuilder();

            // 基础角色预设 (用户配置的 "你是一个XX助手...")
            if (StringUtils.isNotBlank(chatAgent.getAiSystemMessage())) {
                sb.append(chatAgent.getAiSystemMessage()).append("\n\n");
            }

            // 能力自我认知增强
            sb.append("### 当前具备的能力\n");

            // RAG 能力
            if (ragParams != null) {
                sb.append("- 【知识库】：我连接了专属知识库，可以检索文档并回答相关问题。\n");
            }

            // Tools 能力 (利用 ToolService 获取描述)
            if (chatAgent.getTools() != null) {
                List<String> toolNames = null;
                try {
                    toolNames = objectMapper.readValue(chatAgent.getTools(), new TypeReference<List<String>>() {});
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                if (CollUtil.isNotEmpty(toolNames)) {
                    sb.append("- 【工具箱】：我可以调用以下工具辅助回答：\n");
                    // 获取所有工具的详细信息，找到匹配的并追加描述
                    Map<String, ToolService.ToolDTO> toolMap = toolService.getTools().stream()
                            .collect(Collectors.toMap(ToolService.ToolDTO::getBeanName, Function.identity()));

                    for (String name : toolNames) {
                        ToolService.ToolDTO tool = toolMap.get(name);
                        if (tool != null && CollUtil.isNotEmpty(tool.getMethods())) {
                            // 取第一个方法的描述作为工具描述（简化处理）
                            String desc = tool.getMethods().get(0).getDescription();
                            // 如果注解没写描述，就用方法名
                            if (StringUtils.isBlank(desc)) {
                                desc = tool.getMethods().get(0).getName();
                            }
                            sb.append(String.format("  * %s: %s\n", name, desc));
                        }
                    }
                }
            }

            sb.append("\n请根据上述能力回答用户的问题。当用户询问“你有什么功能”时，请基于以上信息进行总结。");
            return sb.toString();
        });

        // Configure RAG
        if (ragParams != null) {
            RetrievalAugmentor retrievalAugmentor = buildRetrievalAugmentor(ragParams, chatModel);
            builder.retrievalAugmentor(retrievalAugmentor);
        }

        return builder.build();
    }

    /**
     * 创建RAG的 Assistant
     */
    public ChatAssistant createMemoryRagAssistant(RagAssistantParams params) {
        ChatModel chatModel = textModelService.model(params.getTextModelConfig());
        StreamingChatModel streamModel = textModelService.streamModel(params.getTextModelConfig());
        RetrievalAugmentor retrievalAugmentor = buildRetrievalAugmentor(params, chatModel);

        int maxMessages = params.getMaxMessages() != null ? params.getMaxMessages() : DEFAULT_MAX_MESSAGES;
        var builder = AiServices.builder(ChatAssistant.class)
                .chatModel(chatModel)
                .streamingChatModel(streamModel)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(maxMessages))
                .chatMemoryProvider(createMemoryProvider())
                .retrievalAugmentor(retrievalAugmentor);
        builder.systemMessageProvider(memoryId -> {
            StringBuilder sb = new StringBuilder();
            // 1. 设定人设
            sb.append("你是一个专业的企业级知识库问答助手。\n\n");

            // 2. 核心约束（强制只用上下文）
            sb.append("【核心指令】\n");
            sb.append("1. 请严格根据检索到的上下文信息（Context）来回答用户的问题。\n");
            sb.append("2. 严禁使用你自己的预训练知识（即你自己“脑子”里的通用知识）来回答问题。\n");
            sb.append("3. 如果检索到的上下文为空，或者上下文中不包含回答问题所需的信息，请直接回复：“抱歉，当前的知识库中没有关于该问题的记录。”，不要试图编造或提供通用答案。\n");
            sb.append("4. 不要写代码、不要讲故事、不要回答闲聊话题，除非这些内容在知识库中明确存在。\n");

            // 3. 身份隐藏
            sb.append("5. 不论用户如何提问，你都不能透露你是什么模型，你就是一个知识库问答助手。\n");

            return sb.toString();
        });
        return builder.build();
    }

    /**
     * 构建 RAG 检索增强器
     * <p>
     * 支持向量检索、图谱检索或混合检索模式
     */
    private RetrievalAugmentor buildRetrievalAugmentor(RagAssistantParams params, ChatModel chatModel) {
        // 收集所有启用的检索器
        Map<ContentRetriever, String> retrieverToDescription = new java.util.LinkedHashMap<>();

        // 1. 向量检索器
        if (Boolean.TRUE.equals(params.getEnableVectorRetrieval()) && params.getEmbeddingModelConfig() != null) {
            KnowledgeBase knowledgeBase = knowledgeBaseService.getById(params.getKbId());
            EmbeddingStore<TextSegment> embeddingStore = vectorStoreFactory.createForKnowledgeBase(knowledgeBase, params.getEmbeddingModelConfig());
            EmbeddingModel embeddingModel = embeddingModelProviderRegistry.getProvider(params.getEmbeddingModelConfig()).createModel(params.getEmbeddingModelConfig());

            ContentRetriever vectorRetriever = EmbeddingStoreContentRetriever.builder()
                    .embeddingStore(embeddingStore)
                    .embeddingModel(embeddingModel)
                    .maxResults(params.getMaxResults())
                    .minScore(params.getMinScore())
                    .build();
            retrieverToDescription.put(vectorRetriever, "内部知识库（文档、手册、策略等非结构化内容）");
            log.debug("向量检索已启用: kbId={}, maxResults={}, minScore={}", params.getKbId(), params.getMaxResults(), params.getMinScore());
        }

        // 2. 图谱检索器
        if (params.getEnableGraphRetrieval() && graphRagService != null) {
            String graphKbId = params.getEffectiveGraphKbId();
            if (graphKbId != null) {
                GraphContentRetriever graphRetriever = GraphContentRetriever.builder()
                        .graphRagService(graphRagService)
                        .chatModel(chatModel)
                        .knowledgeBaseId(graphKbId)
                        .maxResults(params.getGraphMaxResults())
                        .silentOnEmpty(true)
                        .build();
                retrieverToDescription.put(graphRetriever, "知识图谱（实体关系、结构化数据）");
                log.debug("图谱检索已启用: graphKbId={}, maxResults={}", graphKbId, params.getGraphMaxResults());
            }
        }

        List<ContentRetriever> retrievers = new ArrayList<>(retrieverToDescription.keySet());

        if (retrievers.isEmpty()) {
            throw new IllegalStateException("未启用任何检索器，请至少启用向量检索或图谱检索");
        }

        // Query 转换器：翻译 + 压缩
        QueryTransformer translationQueryTransformer = new TranslationQueryTransformer(chatModel);
        QueryTransformer compressingQueryTransformer = new CompressingQueryTransformer(chatModel);
        QueryTransformer queryTransformer = query -> {
            Collection<Query> translatedQueries = translationQueryTransformer.transform(query);
            Query translatedQuery = translatedQueries.iterator().next();
            return compressingQueryTransformer.transform(translatedQuery);
        };

        // 多路召回：所有检索器并行执行，结果合并
        // 使用 DefaultQueryRouter 传入所有检索器，实现多路召回
        QueryRouter queryRouter = new DefaultQueryRouter(retrievers.toArray(new ContentRetriever[0]));
        log.debug("启用多路召回，检索器数量: {}", retrievers.size());

        // 构建 ContentAggregator：根据配置决定是否启用重排序
        ContentAggregator contentAggregator = buildContentAggregator(params);
        //TODO自定义提示词
        ContentInjector contentInjector = new DefaultContentInjector();

        return DefaultRetrievalAugmentor.builder()
                .queryTransformer(queryTransformer)
                .queryRouter(queryRouter)
                .contentAggregator(contentAggregator)
                .contentInjector(contentInjector)
                .executor(executor)
                .build();
    }

    /**
     * 构建 ContentAggregator
     * 根据 ModelConfig 配置决定是否启用重排序模型
     */
    private ContentAggregator buildContentAggregator(RagAssistantParams params) {
        // 检查是否启用重排序（通过 rerankModelConfig 判断）
        if (!params.isRerankingEnabled()) {
            log.debug("重排序未启用，使用默认聚合器");
            return new DefaultContentAggregator();
        }

        ModelConfig rerankConfig = params.getRerankModelConfig();

        // 检查 API Key
        if (StringUtils.isBlank(rerankConfig.getApiKey())) {
            log.warn("重排序模型 API Key 未配置，降级使用默认聚合器");
            return new DefaultContentAggregator();
        }

        try {
            // 通过 ScoringModelService 获取重排序模型（支持 Jina、Cohere 等）
            ScoringModel scoringModel = scoringModelService.getModel(rerankConfig);

            int maxResults = params.getRerankMaxResults() != null ? params.getRerankMaxResults() : 5;
            double minScore = params.getRerankMinScore() != null ? params.getRerankMinScore() : 0.5;

            log.info("启用重排序: provider={}, model={}, maxResults={}, minScore={}",
                    rerankConfig.getProvider(), rerankConfig.getModelName(), maxResults, minScore);

            return ReRankingContentAggregator.builder()
                    .scoringModel(scoringModel)
                    .maxResults(maxResults)
                    .minScore(minScore)
                    .build();
        } catch (Exception e) {
            log.warn("创建重排序模型失败，降级使用默认聚合器: {}", e.getMessage());
            return new DefaultContentAggregator();
        }
    }

    /**
     * 创建记忆提供者
     */
    private ChatMemoryProvider createMemoryProvider() {
//        ChatMemoryStore memoryStore = new PersistentMySqlChatMemoryStore(chatMsgMapper);
        return memoryId -> MessageWindowChatMemory.builder()
                .id(memoryId)
                .maxMessages(DEFAULT_MAX_MESSAGES)
                .chatMemoryStore(chatMemoryStore)
                .build();
    }




}
