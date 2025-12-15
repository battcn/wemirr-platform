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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 增强的向量存储工厂
 * 支持知识库与向量数据库集合的映射关系
 *
 * @author xJh
 * @since 2025/10/20
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class EnhancedVectorStoreFactory {

    private final VectorStoreProperties properties;
    
    // 缓存已创建的向量存储实例
    private final Map<String, EmbeddingStore<TextSegment>> storeCache = new ConcurrentHashMap<>();
    
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
     * 创建默认的向量存储
     * 
     * @return 向量存储实例
     */
    public EmbeddingStore<TextSegment> createefault() {
        VectorStoreProperties.StoreType type = properties.getType();
        return switch (type) {
            case MILVUS -> createMilvus(properties.getMilvus().getCollectionName(), properties.getMilvus().getDimension());
            case PGVECTOR -> createPgVector();
            case IN_MEMORY -> new InMemoryEmbeddingStore<>();
            default -> throw new IllegalArgumentException("Unsupported vector store: " + type);
        };
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
    
    /**
     * 为知识库创建Milvus向量存储
     */
    private EmbeddingStore<TextSegment> createMilvusForKnowledgeBase(KnowledgeBase knowledgeBase, ModelConfig modelConfig) {
        VectorStoreProperties.MilvusConfig config = properties.getMilvus();
        
        // 生成集合名称
        String collectionName = generateCollectionName(knowledgeBase, config);
        
        // 获取向量维度
        int dimension = getVectorDimension(modelConfig);
        
        // 创建Milvus客户端
        MilvusServiceClient client = createMilvusClient(config);
        
        //milvus会自动创建集合
        
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
        int dimension = getVectorDimension(modelConfig);
        
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
     * 创建默认Milvus存储
     */
    private EmbeddingStore<TextSegment> createMilvus(String collectionName, int dimension) {
        VectorStoreProperties.MilvusConfig config = properties.getMilvus();
        MilvusServiceClient client = createMilvusClient(config);
        
        return MilvusEmbeddingStore.builder()
                .milvusClient(client)
                .collectionName(collectionName)
                .dimension(dimension)
                .build();
    }
    
    /**
     * 创建默认PgVector存储
     */
    private EmbeddingStore<TextSegment> createPgVector() {
        VectorStoreProperties.PgVectorConfig config = properties.getPgvector();
        
        return PgVectorEmbeddingStore.builder()
                .host(config.getHost())
                .port(config.getPort())
                .database(config.getDatabase())
                .user(config.getUsername())
                .password(config.getPassword())
                .table(config.getTable())
                .dimension(config.getDimension())
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
    private int getVectorDimension(ModelConfig modelConfig) {
        // todo 根据模型配置获取向量维度 (注意，milvus集合的向量维度一定要跟模型保持一致）
        // 这里需要根据实际的模型配置来获取维度
        return properties.getMilvus().getDimension();
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
