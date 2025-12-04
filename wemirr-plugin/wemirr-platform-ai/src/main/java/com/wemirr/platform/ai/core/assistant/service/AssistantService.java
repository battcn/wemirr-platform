package com.wemirr.platform.ai.core.assistant.service;

import com.wemirr.platform.ai.core.assistant.interfaces.ChatAssistant;
import com.wemirr.platform.ai.core.enums.ChunkType;
import com.wemirr.platform.ai.core.provider.embedding.EmbeddingModelProviderRegistry;
import com.wemirr.platform.ai.core.provider.text.TextModelService;
import com.wemirr.platform.ai.core.provider.vectorStore.EnhancedVectorStoreFactory;
import com.wemirr.platform.ai.core.rag.TranslationQueryTransformer;
import com.wemirr.platform.ai.domain.entity.KnowledgeBase;
import com.wemirr.platform.ai.domain.entity.ModelConfig;
import com.wemirr.platform.ai.service.KnowledgeBaseService;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.rag.content.aggregator.ContentAggregator;
import dev.langchain4j.rag.content.aggregator.DefaultContentAggregator;
import dev.langchain4j.rag.content.injector.ContentInjector;
import dev.langchain4j.rag.content.injector.DefaultContentInjector;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.rag.content.retriever.WebSearchContentRetriever;
import dev.langchain4j.rag.query.Query;
import dev.langchain4j.rag.query.router.DefaultQueryRouter;
import dev.langchain4j.rag.query.router.LanguageModelQueryRouter;
import dev.langchain4j.rag.query.router.QueryRouter;
import dev.langchain4j.rag.query.transformer.CompressingQueryTransformer;
import dev.langchain4j.rag.query.transformer.QueryTransformer;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.filter.Filter;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import dev.langchain4j.web.search.tavily.TavilyWebSearchEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Function;

import static dev.langchain4j.store.embedding.filter.MetadataFilterBuilder.metadataKey;

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

    private final Executor executor = Executors.newCachedThreadPool();

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
     * 创建RAG的 Assistant
     * @param modelConfig 模型配置
     * @return ChatAssistant 实例
     * todo 抽离参数
     */
    public ChatAssistant createMemoryRagAssistant(Long kbId,ModelConfig textModelConfig,ModelConfig embeddingModelConfig) {
        KnowledgeBase knowledgeBase = knowledgeBaseService.getById(kbId);
        EmbeddingStore<TextSegment> embeddingStore = vectorStoreFactory.createForKnowledgeBase(knowledgeBase, embeddingModelConfig);
        ChatModel chatModel = textModelService.model(textModelConfig);
        StreamingChatModel streamModel = textModelService.streamModel(textModelConfig);
        EmbeddingModel embeddingModel = embeddingModelProviderRegistry.getProvider(embeddingModelConfig).createModel(embeddingModelConfig);


        // 动态过滤器，根据问答类型过滤
        Function<Query, Filter> queryFilterFunction =
                (query) -> metadataKey("chunkType").isEqualTo(ChunkType.ANSWER.getCode());

        ContentRetriever contentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .maxResults(2)
                .dynamicFilter(queryFilterFunction)
                // 对产品信息设置更严格的分数要求
                .minScore(0.8)
                .build();
        //todo 如果检索不到，应该返回搜索不到相关内容

        // CompressingQueryTransformer压缩对话
        QueryTransformer queryTransformer = new CompressingQueryTransformer(chatModel);


        // QueryRouter: 根据查询内容将查询路由到一个或多个 ContentRetriever。 可实现多路召回
//        QueryRouter queryRouter = (query) -> {
//            System.out.println("Routing query: " + query.text());
////            String lowerCaseQuery = query.text().toLowerCase();
////            if (lowerCaseQuery.contains("policy") || lowerCaseQuery.contains("vacation") || lowerCaseQuery.contains("leave")) {
////                System.out.println(" --> Routing to Policy Retriever (基于关键词)");
////                return Arrays.asList(policyRetriever); // 路由到政策检索器
////            } else if (lowerCaseQuery.contains("product") || lowerCaseQuery.contains("quantum leap") || lowerCaseQuery.contains("drone")) {
////                System.out.println(" --> Routing to Product Retriever (基于关键词)");
////                return Arrays.asList(productRetriever); // 路由到产品检索器
////            } else {
////                System.out.println(" --> Routing to BOTH Policy and Product Retrievers (默认多路召回)");
////                return Arrays.asList(policyRetriever, productRetriever); // 默认情况下，同时使用两个检索器
////            }
//            return Arrays.asList(productRetriever);
//        };

        QueryRouter queryRouter = new DefaultQueryRouter(contentRetriever);

        // ContentAggregator: 聚合所有 ContentRetriever 返回的内容。
        // 可以自定义排序、去重、截断等逻辑。这里使用默认实现。 todo 接入重排序模型
        ContentAggregator contentAggregator = new DefaultContentAggregator();

        // ContentInjector: 将聚合后的相关内容注入到用户消息中，发送给LLM。
        // 默认会将内容作为SystemMessage注入。
        ContentInjector contentInjector = new DefaultContentInjector();

        // 构建 RetrievalAugmentor ---
        // 将所有RAG组件组合起来，形成完整的RAG管道。
        RetrievalAugmentor retrievalAugmentor = DefaultRetrievalAugmentor.builder()
                .queryTransformer(queryTransformer)
                .queryRouter(queryRouter)
                .contentAggregator(contentAggregator)
                .contentInjector(contentInjector)
                // 使用Executor进行异步处理，提升RAG检索效率
                .executor(executor)
                .build();

        return AiServices.builder(ChatAssistant.class)
                .chatModel(chatModel)
                .streamingChatModel(streamModel)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(DEFAULT_MAX_MESSAGES))
                .chatMemoryProvider(createMemoryProvider())
                .retrievalAugmentor(retrievalAugmentor)
                .build();
    }

    /**
     * 创建RAG的 Assistant
     */
    public ChatAssistant createMemoryRagAssistant(RagAssistantParams params) {
        KnowledgeBase knowledgeBase = knowledgeBaseService.getById(params.getKbId());
        EmbeddingStore<TextSegment> embeddingStore = vectorStoreFactory.createForKnowledgeBase(knowledgeBase, params.getEmbeddingModelConfig());
        ChatModel chatModel = textModelService.model(params.getTextModelConfig());
        StreamingChatModel streamModel = textModelService.streamModel(params.getTextModelConfig());
        EmbeddingModel embeddingModel = embeddingModelProviderRegistry.getProvider(params.getEmbeddingModelConfig()).createModel(params.getEmbeddingModelConfig());

        //元数据过滤
//        Function<Query, Filter> queryFilterFunction =
//                (query) -> metadataKey("chunkType").isEqualTo(params.getFilterChunkType().getCode());

        ContentRetriever contentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .maxResults(params.getMaxResults())
//                .dynamicFilter(queryFilterFunction)
                .minScore(params.getMinScore())
                .build();

//       QueryTransformer compressingQueryTransformer = new CompressingQueryTransformer(chatModel);
//         QueryTransformer queryTransformer = query -> compressingQueryTransformer.transform(defaultQueryTransformer.transform(query));
//        QueryTransformer queryTransformer = new DefaultQueryTransformer();
        // 创建翻译转换器
        QueryTransformer translationQueryTransformer = new TranslationQueryTransformer(chatModel);
        // 创建压缩转换器
        QueryTransformer compressingQueryTransformer = new CompressingQueryTransformer(chatModel);
        // 组合转换器：先翻译，再压缩
        QueryTransformer queryTransformer = query -> {
            // 第一步：翻译
            Collection<Query> translatedQueries = translationQueryTransformer.transform(query);
            Query translatedQuery = translatedQueries.iterator().next();

            // 第二步：压缩（如果需要考虑对话历史）
            return compressingQueryTransformer.transform(translatedQuery);
        };
        QueryRouter queryRouter = new DefaultQueryRouter(contentRetriever);
        ContentAggregator contentAggregator = new DefaultContentAggregator();
        ContentInjector contentInjector = new DefaultContentInjector();

        RetrievalAugmentor retrievalAugmentor = DefaultRetrievalAugmentor.builder()
                .queryTransformer(queryTransformer)
                .queryRouter(queryRouter)
                .contentAggregator(contentAggregator)
                .contentInjector(contentInjector)
                .executor(executor)
                .build();

        int maxMessages = params.getMaxMessages() != null ? params.getMaxMessages() : DEFAULT_MAX_MESSAGES;
        return AiServices.builder(ChatAssistant.class)
                .chatModel(chatModel)
                .streamingChatModel(streamModel)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(maxMessages))
                .chatMemoryProvider(createMemoryProvider())
                .retrievalAugmentor(retrievalAugmentor)
                .build();
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

    public RetrievalAugmentor buildAdvancedRetrievalAugmentor(ChatModel chatModel,EmbeddingStore embeddingStore,EmbeddingModel embeddingModel) {
        // Query 转换器。也可以什么换用 ExpandingQueryTransformer 进行 Query 扩展
        QueryTransformer queryTransformer = new CompressingQueryTransformer(chatModel);
        // web 搜索引擎，此处选择的是 tavily 搜索引擎
        final var tavilyWebSearchEngine = TavilyWebSearchEngine.builder()
                .apiKey("")
                .build();
        WebSearchContentRetriever webSearchContentRetriever = WebSearchContentRetriever.builder()
                .maxResults(10)
                .webSearchEngine(tavilyWebSearchEngine)
                .build();
        // 内嵌数据库检索器，此处选择的是 milvus 数据库
        EmbeddingStoreContentRetriever embeddingStoreContentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .maxResults(10)
                .build();
        //  Query 路由，让大模型决定选择哪一个检索器或者哪几个检索器。retrieverToDescription 属性的 key 为检索器，value 为检索器的描述
        QueryRouter queryRouter = LanguageModelQueryRouter.builder()
                .chatModel(chatModel)
                .retrieverToDescription(Map.of(
                        webSearchContentRetriever, "Web Search",
                        embeddingStoreContentRetriever, "Embedding Database"
                ))
                .build();
        // content 聚合器，如果有 ReRanking 模型，也可以选择使用 ReRankingContentAggregator 对检索结果进行排序
        DefaultContentAggregator contentAggregator = new DefaultContentAggregator();
        // content 注入器，可以自定义注入内容的模型
        DefaultContentInjector contentInjector = new DefaultContentInjector();

        // 检索增强器，简单理解为把上面的几个组件联系到一起协作处理 Query，最终返回增强后的 ChatMessage
        return DefaultRetrievalAugmentor.builder()
                .queryTransformer(queryTransformer)
                .queryRouter(queryRouter)
                .contentAggregator(contentAggregator)
                .contentInjector(contentInjector)
//                .executor(executorService) 多线程处理
                .build();
    }
}
