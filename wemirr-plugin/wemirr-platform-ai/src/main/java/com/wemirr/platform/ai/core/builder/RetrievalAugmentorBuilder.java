package com.wemirr.platform.ai.core.builder;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.chat.ChatModel;
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
import dev.langchain4j.rag.query.router.LanguageModelQueryRouter;
import dev.langchain4j.rag.query.router.QueryRouter;
import dev.langchain4j.rag.query.transformer.CompressingQueryTransformer;
import dev.langchain4j.rag.query.transformer.QueryTransformer;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.web.search.WebSearchEngine;
import dev.langchain4j.web.search.tavily.TavilyWebSearchEngine;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xJh
 * @since 2025/10/22
 **/
@Slf4j
public class RetrievalAugmentorBuilder {

    private ChatModel chatModel;
    private EmbeddingModel embeddingModel;
    private EmbeddingStore<TextSegment> embeddingStore;

    // 检索器
    private boolean enableVectorRetrieval = true;
    private boolean enableWebSearch = false;
    private String webSearchApiKey;
    private String webSearchEngineType = "tavily";
    private int webMaxResults = 5;
    private int embeddingMaxResults = 8;

    // 增强策略
    private boolean enableQueryCompression = true;
    private boolean enableReRanking = false;
    private boolean enableParallelRetrieval = true;
    private ExecutorService executorService;

    // === 构造函数私有，使用静态工厂方法 ===
    private RetrievalAugmentorBuilder() {}

    // === 静态入口 ===
    public static RetrievalAugmentorBuilder builder() {
        return new RetrievalAugmentorBuilder();
    }

    // === 配置方法（链式调用）===

    public RetrievalAugmentorBuilder chatModel(ChatModel chatModel) {
        this.chatModel = chatModel;
        return this;
    }

    public RetrievalAugmentorBuilder embeddingModel(EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
        return this;
    }

    public RetrievalAugmentorBuilder embeddingStore(EmbeddingStore<TextSegment> embeddingStore) {
        this.embeddingStore = embeddingStore;
        return this;
    }

    public RetrievalAugmentorBuilder enableVectorRetrieval(boolean enable) {
        this.enableVectorRetrieval = enable;
        return this;
    }

    public RetrievalAugmentorBuilder enableWebSearch(boolean enable) {
        this.enableWebSearch = enable;
        return this;
    }

    public RetrievalAugmentorBuilder webSearchApiKey(String apiKey) {
        this.webSearchApiKey = apiKey;
        return this;
    }

    public RetrievalAugmentorBuilder webSearchEngineType(String type) {
        this.webSearchEngineType = type;
        return this;
    }

    public RetrievalAugmentorBuilder webMaxResults(int maxResults) {
        this.webMaxResults = maxResults;
        return this;
    }

    public RetrievalAugmentorBuilder embeddingMaxResults(int maxResults) {
        this.embeddingMaxResults = maxResults;
        return this;
    }

    public RetrievalAugmentorBuilder enableQueryCompression(boolean enable) {
        this.enableQueryCompression = enable;
        return this;
    }

    public RetrievalAugmentorBuilder enableReRanking(boolean enable) {
        this.enableReRanking = enable;
        return this;
    }

    public RetrievalAugmentorBuilder enableParallelRetrieval(boolean enable) {
        this.enableParallelRetrieval = enable;
        return this;
    }

    public RetrievalAugmentorBuilder executorService(ExecutorService executor) {
        this.executorService = executor;
        return this;
    }

    // === 构建方法 ===
    public RetrievalAugmentor build() {
        validate();

        QueryTransformer queryTransformer = null;
        if (enableQueryCompression) {
            queryTransformer = new CompressingQueryTransformer(chatModel);
            log.debug("Using CompressingQueryTransformer");
        }

        // 收集所有启用的检索器
        Map<ContentRetriever, String> retrieverToDescription = new LinkedHashMap<>();

        // 1. 向量库检索器
        if (enableVectorRetrieval && embeddingStore != null && embeddingModel != null) {
            EmbeddingStoreContentRetriever vectorRetriever = EmbeddingStoreContentRetriever.builder()
                    .embeddingStore(embeddingStore)
                    .embeddingModel(embeddingModel)
                    .maxResults(embeddingMaxResults)
                    .build();
            retrieverToDescription.put(vectorRetriever, "Internal/private knowledge base (documents, manuals, policies)");
            log.debug("Enabled vector retrieval with maxResults={}", embeddingMaxResults);
        }

        // 2. Web 搜索检索器
        if (enableWebSearch && webSearchApiKey != null && !webSearchApiKey.trim().isEmpty()) {
            WebSearchEngine webSearchEngine = createWebSearchEngine();
            WebSearchContentRetriever webRetriever = WebSearchContentRetriever.builder()
                    .webSearchEngine(webSearchEngine)
                    .maxResults(webMaxResults)
                    .build();
            retrieverToDescription.put(webRetriever, "Real-time web information (news, current events, facts)");
            log.debug("Enabled web search ({}) with maxResults={}", webSearchEngineType, webMaxResults);
        }

        if (retrieverToDescription.isEmpty()) {
            throw new IllegalStateException("No retriever is enabled. Please enable at least one.");
        }

        // Query Router
        QueryRouter queryRouter = LanguageModelQueryRouter.builder()
                .chatModel(chatModel)
                .retrieverToDescription(retrieverToDescription)
                .build();

        // Content Aggregator
        ContentAggregator contentAggregator = enableReRanking ?
                // TODO: 实现 ReRankingContentAggregator
                createReRankingAggregator() :
                new DefaultContentAggregator();

        // Content Injector
        ContentInjector contentInjector = new DefaultContentInjector();

        // 并行执行器
        ExecutorService finalExecutor = this.executorService;
        if (enableParallelRetrieval && finalExecutor == null) {
            finalExecutor = Executors.newFixedThreadPool(
                    Math.min(retrieverToDescription.size(), 4)
            );
            log.debug("create internal executor with {} threads", finalExecutor);
        }

        // 构建增强器
        DefaultRetrievalAugmentor.DefaultRetrievalAugmentorBuilder builder = DefaultRetrievalAugmentor.builder()
                .queryTransformer(queryTransformer)
                .queryRouter(queryRouter)
                .contentAggregator(contentAggregator)
                .contentInjector(contentInjector);

        if (finalExecutor != null) {
            builder.executor(finalExecutor);
        }

        return builder.build();
    }

    private WebSearchEngine createWebSearchEngine() {
        return switch (webSearchEngineType.toLowerCase()) {
            case "tavily" -> TavilyWebSearchEngine.builder().apiKey(webSearchApiKey).build();
            // case "bing" -> BingWebSearchEngine.builder().apiKey(webSearchApiKey).build();
            // case "google" -> GoogleWebSearchEngine.builder().apiKey(webSearchApiKey).cx("...").build();
            // case "exa" -> ExaWebSearchEngine.builder().apiKey(webSearchApiKey).build();
            default -> throw new IllegalArgumentException("Unsupported web search engine: " + webSearchEngineType);
        };
    }

    private ContentAggregator createReRankingAggregator() {
        // TODO: 注入 ReRankingModel 并返回 ReRankingContentAggregator
        // 示例：return new ReRankingContentAggregator(rerankingModel);
        log.warn("Re-Ranking is enabled but no implementation provided. Using default aggregator.");
        return new DefaultContentAggregator();
    }

    private void validate() {
        if (chatModel == null) {
            throw new IllegalArgumentException("chatModel is required");
        }
        if (enableVectorRetrieval && (embeddingStore == null || embeddingModel == null)) {
            throw new IllegalArgumentException("embeddingStore and embeddingModel are required when vector retrieval is enabled");
        }
        if (enableWebSearch && (webSearchApiKey == null || webSearchApiKey.trim().isEmpty())) {
            throw new IllegalArgumentException("webSearchApiKey is required when web search is enabled");
        }
    }

    // 可选：优雅关闭
    public void shutdown() {
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdown();
        }
    }
}
