package com.wemirr.platform.ai.core.builder;

import com.wemirr.platform.ai.core.provider.graph.GraphContentRetriever;
import com.wemirr.platform.ai.core.provider.graph.GraphRagService;
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
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * RAG 检索增强器构建器
 * <p>
 * 支持多种检索模式：向量检索、Web搜索、图谱检索，可灵活组合使用。
 *
 * @author xJh
 * @since 2025/10/22
 */
@Slf4j
public class RetrievalAugmentorBuilder {

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

    // 图谱检索配置
    private boolean enableGraphRetrieval = false;
    private GraphRagService graphRagService;
    private String graphKnowledgeBaseId;
    private int graphMaxResults = 10;

    // 增强策略
    private boolean enableQueryCompression = true;
    private boolean enableReRanking = false;
    private boolean enableParallelRetrieval = true;
    private ExecutorService executorService;

    // 重排序配置
    private ScoringModel scoringModel;
    private int rerankMaxResults = 5;
    private double rerankMinScore = 0.5;

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

    // === 图谱检索配置 ===

    public RetrievalAugmentorBuilder enableGraphRetrieval(boolean enable) {
        this.enableGraphRetrieval = enable;
        return this;
    }

    public RetrievalAugmentorBuilder graphRagService(GraphRagService graphRagService) {
        this.graphRagService = graphRagService;
        return this;
    }

    public RetrievalAugmentorBuilder graphKnowledgeBaseId(String knowledgeBaseId) {
        this.graphKnowledgeBaseId = knowledgeBaseId;
        return this;
    }

    public RetrievalAugmentorBuilder graphMaxResults(int maxResults) {
        this.graphMaxResults = maxResults;
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

        // 3. 图谱检索器
        if (enableGraphRetrieval && graphRagService != null && graphKnowledgeBaseId != null) {
            GraphContentRetriever graphRetriever = GraphContentRetriever.builder()
                    .graphRagService(graphRagService)
                    .chatModel(chatModel)
                    .knowledgeBaseId(graphKnowledgeBaseId)
                    .maxResults(graphMaxResults)
                    .silentOnEmpty(true)
                    .build();
            retrieverToDescription.put(graphRetriever, "Knowledge graph with structured entity and relationship data");
            log.debug("Enabled graph retrieval for kb={} with maxResults={}", graphKnowledgeBaseId, graphMaxResults);
        }

        List<ContentRetriever> retrievers = new ArrayList<>(retrieverToDescription.keySet());

        if (retrievers.isEmpty()) {
            throw new IllegalStateException("No retriever is enabled. Please enable at least one.");
        }

        // 多路召回：所有检索器并行执行，结果合并
        QueryRouter queryRouter = new DefaultQueryRouter(retrievers.toArray(new ContentRetriever[0]));
        log.debug("Enabled multi-retrieval with {} retrievers", retrievers.size());

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
        if (enableGraphRetrieval && (graphRagService == null || graphKnowledgeBaseId == null)) {
            throw new IllegalArgumentException("graphRagService and graphKnowledgeBaseId are required when graph retrieval is enabled");
        }
    }

    // 可选：优雅关闭
    public void shutdown() {
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdown();
        }
    }
}
