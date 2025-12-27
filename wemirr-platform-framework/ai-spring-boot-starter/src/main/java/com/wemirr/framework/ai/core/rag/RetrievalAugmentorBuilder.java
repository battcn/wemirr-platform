package com.wemirr.framework.ai.core.rag;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.chat.ChatModel;
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
import dev.langchain4j.rag.content.retriever.WebSearchContentRetriever;
import dev.langchain4j.rag.query.router.DefaultQueryRouter;
import dev.langchain4j.rag.query.router.QueryRouter;
import dev.langchain4j.rag.query.transformer.CompressingQueryTransformer;
import dev.langchain4j.rag.query.transformer.QueryTransformer;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.web.search.WebSearchEngine;
import dev.langchain4j.web.search.tavily.TavilyWebSearchEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * RAG 检索增强器构建器
 * <p>
 * 支持多种检索模式：向量检索、Web搜索，可灵活组合使用
 * </p>
 *
 * @author Levin
 * @since 2025/10/22
 */
public class RetrievalAugmentorBuilder {

    private static final Logger log = LoggerFactory.getLogger(RetrievalAugmentorBuilder.class);

    private ChatModel chatModel;
    private EmbeddingModel embeddingModel;
    private EmbeddingStore<TextSegment> embeddingStore;

    // 向量检索配置
    private boolean enableVectorRetrieval = true;
    private int embeddingMaxResults = 8;

    // Web 搜索配置
    private boolean enableWebSearch = false;
    private String webSearchApiKey;
    private String webSearchEngineType = "tavily";
    private int webMaxResults = 5;

    // 增强策略
    private boolean enableQueryCompression = true;
    private boolean enableReRanking = false;
    private boolean enableParallelRetrieval = true;
    private ExecutorService executorService;

    // 重排序配置
    private ScoringModel scoringModel;
    private int rerankMaxResults = 5;
    private double rerankMinScore = 0.5;

    // 自定义检索器
    private final List<ContentRetriever> customRetrievers = new ArrayList<>();

    private RetrievalAugmentorBuilder() {}

    public static RetrievalAugmentorBuilder builder() {
        return new RetrievalAugmentorBuilder();
    }

    // === 配置方法 ===

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

    public RetrievalAugmentorBuilder scoringModel(ScoringModel scoringModel) {
        this.scoringModel = scoringModel;
        return this;
    }

    public RetrievalAugmentorBuilder rerankMaxResults(int maxResults) {
        this.rerankMaxResults = maxResults;
        return this;
    }

    public RetrievalAugmentorBuilder rerankMinScore(double minScore) {
        this.rerankMinScore = minScore;
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

    /**
     * 添加自定义检索器（如图谱检索器）
     */
    public RetrievalAugmentorBuilder addRetriever(ContentRetriever retriever) {
        this.customRetrievers.add(retriever);
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

        Map<ContentRetriever, String> retrieverToDescription = new LinkedHashMap<>();

        // 1. 向量库检索器
        if (enableVectorRetrieval && embeddingStore != null && embeddingModel != null) {
            EmbeddingStoreContentRetriever vectorRetriever = EmbeddingStoreContentRetriever.builder()
                    .embeddingStore(embeddingStore)
                    .embeddingModel(embeddingModel)
                    .maxResults(embeddingMaxResults)
                    .build();
            retrieverToDescription.put(vectorRetriever, "Internal knowledge base");
            log.debug("Enabled vector retrieval with maxResults={}", embeddingMaxResults);
        }

        // 2. Web 搜索检索器
        if (enableWebSearch && webSearchApiKey != null && !webSearchApiKey.trim().isEmpty()) {
            WebSearchEngine webSearchEngine = createWebSearchEngine();
            WebSearchContentRetriever webRetriever = WebSearchContentRetriever.builder()
                    .webSearchEngine(webSearchEngine)
                    .maxResults(webMaxResults)
                    .build();
            retrieverToDescription.put(webRetriever, "Real-time web information");
            log.debug("Enabled web search ({}) with maxResults={}", webSearchEngineType, webMaxResults);
        }

        // 3. 自定义检索器
        for (ContentRetriever customRetriever : customRetrievers) {
            retrieverToDescription.put(customRetriever, "Custom retriever");
            log.debug("Added custom retriever: {}", customRetriever.getClass().getSimpleName());
        }

        List<ContentRetriever> retrievers = new ArrayList<>(retrieverToDescription.keySet());

        if (retrievers.isEmpty()) {
            throw new IllegalStateException("No retriever is enabled. Please enable at least one.");
        }

        QueryRouter queryRouter = new DefaultQueryRouter(retrievers.toArray(new ContentRetriever[0]));
        log.debug("Enabled multi-retrieval with {} retrievers", retrievers.size());

        ContentAggregator contentAggregator = enableReRanking ?
                createReRankingAggregator() :
                new DefaultContentAggregator();

        ContentInjector contentInjector = new DefaultContentInjector();

        ExecutorService finalExecutor = this.executorService;
        if (enableParallelRetrieval && finalExecutor == null) {
            finalExecutor = Executors.newFixedThreadPool(Math.min(retrieverToDescription.size(), 4));
        }

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
            default -> throw new IllegalArgumentException("Unsupported web search engine: " + webSearchEngineType);
        };
    }

    private ContentAggregator createReRankingAggregator() {
        if (scoringModel == null) {
            log.warn("重排序已启用但未配置 ScoringModel，降级使用默认聚合器");
            return new DefaultContentAggregator();
        }

        log.debug("启用重排序: maxResults={}, minScore={}", rerankMaxResults, rerankMinScore);
        return ReRankingContentAggregator.builder()
                .scoringModel(scoringModel)
                .maxResults(rerankMaxResults)
                .minScore(rerankMinScore)
                .build();
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

    public void shutdown() {
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdown();
        }
    }
}
