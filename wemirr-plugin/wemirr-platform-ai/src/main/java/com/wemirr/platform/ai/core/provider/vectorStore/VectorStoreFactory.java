package com.wemirr.platform.ai.core.provider.vectorStore;

import com.wemirr.platform.ai.core.config.VectorStoreProperties;
import com.wemirr.platform.ai.domain.entity.KnowledgeBase;
import com.wemirr.platform.ai.domain.entity.ModelConfig;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import dev.langchain4j.store.embedding.milvus.MilvusEmbeddingStore;
import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore;
import io.milvus.client.MilvusServiceClient;
import io.milvus.param.ConnectParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 向量存储工厂
 * 支持知识库专用的向量存储创建
 * 
 * @author xiao1
 * @since 2025-10
 */
@Slf4j
@Component
public class VectorStoreFactory {

    private final VectorStoreProperties properties;
    
    // 缓存已创建的向量存储实例
    private final Map<String, EmbeddingStore<TextSegment>> storeCache = new ConcurrentHashMap<>();

    public VectorStoreFactory(VectorStoreProperties properties) {
        this.properties = properties;
    }

    /**
     * 创建默认的向量存储
     */
    public EmbeddingStore<TextSegment> create() {
        VectorStoreProperties.StoreType type = properties.getType();
        return switch (type) {
            case MILVUS -> createMilvus();
            case PGVECTOR -> createPgVector();
            case IN_MEMORY -> new InMemoryEmbeddingStore<>();
            default -> throw new IllegalArgumentException("Unsupported vector store: " + type);
        };
    }
    
    /**
     * 根据知识库创建向量存储
     * 
     * @param knowledgeBase 知识库
     * @param modelConfig 模型配置
     * @return 向量存储实例
     */
    public EmbeddingStore<TextSegment> createForKnowledgeBase(KnowledgeBase knowledgeBase, ModelConfig modelConfig) {
        String cacheKey = generateCacheKey(knowledgeBase, modelConfig);
        
        // 检查缓存
        if (storeCache.containsKey(cacheKey)) {
            return storeCache.get(cacheKey);
        }
        
        // 创建新的向量存储实例
        EmbeddingStore<TextSegment> store = createStore(knowledgeBase, modelConfig);
        storeCache.put(cacheKey, store);
        
        return store;
    }
    
    /**
     * 创建向量存储实例
     */
    private EmbeddingStore<TextSegment> createStore(KnowledgeBase knowledgeBase, ModelConfig modelConfig) {
        VectorStoreProperties.StoreType type = properties.getType();
        return switch (type) {
            case MILVUS -> createMilvusForKnowledgeBase(knowledgeBase, modelConfig);
            case PGVECTOR -> createPgVectorForKnowledgeBase(knowledgeBase, modelConfig);
            case IN_MEMORY -> new InMemoryEmbeddingStore<>();
            default -> throw new IllegalArgumentException("Unsupported vector store: " + type);
        };
    }

    private EmbeddingStore<TextSegment> createMilvus() {
        VectorStoreProperties.MilvusConfig c = properties.getMilvus();
        MilvusServiceClient milvusClient = new MilvusServiceClient(
                ConnectParam.newBuilder()
                        .withHost(c.getHost())
                        .withPort(c.getPort())
                        .build()
        );
        return MilvusEmbeddingStore.builder()
                .milvusClient(milvusClient)
                .collectionName(c.getCollectionName())
                .dimension(c.getDimension())
                .build();
    }

    private EmbeddingStore<TextSegment> createPgVector() {
        VectorStoreProperties.PgVectorConfig c = properties.getPgvector();

        return PgVectorEmbeddingStore.builder()
                .host(c.getHost())
                .port(c.getPort())
                .database(c.getDatabase())
                .user(c.getUsername())
                .password(c.getPassword())
                .table(c.getTable())
                // 指定向量（embedding）的维度数
                .dimension(c.getDimension())
                // 自动建表
                .createTable(c.isCreateTable())
                // 是否在创建新表前先删除已存在的同名表
                .dropTableFirst(c.isDropTableFirst())
                .build();
    }
    
    /**
     * 为知识库创建Milvus向量存储
     */
    private EmbeddingStore<TextSegment> createMilvusForKnowledgeBase(KnowledgeBase knowledgeBase, ModelConfig modelConfig) {
        VectorStoreProperties.MilvusConfig config = properties.getMilvus();
        
        // 生成集合名称
        String collectionName = generateCollectionName(knowledgeBase, config);
        
        // 获取向量维度
        int dimension = getVectorDimension(modelConfig, config.getDimension());
        
        // 创建Milvus客户端
        MilvusServiceClient client = createMilvusClient(config);
        
        return MilvusEmbeddingStore.builder()
                .milvusClient(client)
                .collectionName(collectionName)
                .dimension(dimension)
                .build();
    }
    
    /**
     * 为知识库创建PgVector向量存储
     */
    private EmbeddingStore<TextSegment> createPgVectorForKnowledgeBase(KnowledgeBase knowledgeBase, ModelConfig modelConfig) {
        VectorStoreProperties.PgVectorConfig config = properties.getPgvector();
        
        // 生成表名
        String tableName = generateTableName(knowledgeBase, config);
        
        // 获取向量维度
        int dimension = getVectorDimension(modelConfig, config.getDimension());
        
        return PgVectorEmbeddingStore.builder()
                .host(config.getHost())
                .port(config.getPort())
                .database(config.getDatabase())
                .user(config.getUsername())
                .password(config.getPassword())
                .table(tableName)
                .dimension(dimension)
                .createTable(config.isCreateTable())
                .dropTableFirst(config.isDropTableFirst())
                .build();
    }
    
    /**
     * 创建Milvus客户端
     */
    private MilvusServiceClient createMilvusClient(VectorStoreProperties.MilvusConfig config) {
        ConnectParam.Builder builder = ConnectParam.newBuilder()
                .withHost(config.getHost())
                .withPort(config.getPort());
        
        if (config.getToken() != null && !config.getToken().isEmpty()) {
            builder.withToken(config.getToken());
        }
        
        if (config.getUri() != null && !config.getUri().isEmpty()) {
            builder.withUri(config.getUri());
        }
        
        return new MilvusServiceClient(builder.build());
    }
    
    /**
     * 生成集合名称
     */
    private String generateCollectionName(KnowledgeBase knowledgeBase, VectorStoreProperties.MilvusConfig config) {
        String kbId = knowledgeBase.getId().toString();
        
        return switch (config.getNamingStrategy()) {
            case PREFIX -> config.getCollectionPrefix() + kbId;
            case SUFFIX -> kbId + config.getCollectionSuffix();
            case CUSTOM -> String.format("kb_%s_vectors", kbId);
        };
    }
    
    /**
     * 生成表名
     */
    private String generateTableName(KnowledgeBase knowledgeBase, VectorStoreProperties.PgVectorConfig config) {
        String kbId = knowledgeBase.getId().toString();
        return String.format("kb_%s_vectors", kbId);
    }
    
    /**
     * 获取向量维度
     */
    private int getVectorDimension(ModelConfig modelConfig, int defaultDimension) {
        // 根据模型配置获取向量维度
        // 这里需要根据实际的模型配置来获取维度
        return defaultDimension;
    }
    
    /**
     * 生成缓存键
     */
    private String generateCacheKey(KnowledgeBase knowledgeBase, ModelConfig modelConfig) {
        return String.format("%s_%s_%s", 
                knowledgeBase.getId(), 
                modelConfig.getId(), 
                properties.getType().name());
    }
    
    /**
     * 清除缓存
     */
    public void clearCache() {
        storeCache.clear();
    }
    
    /**
     * 清除特定知识库的缓存
     */
    public void clearCacheForKnowledgeBase(Long kbId) {
        storeCache.entrySet().removeIf(entry -> entry.getKey().startsWith(kbId + "_"));
    }
}
