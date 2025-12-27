package com.wemirr.platform.ai.core.provider.vector;

import com.wemirr.platform.ai.core.config.VectorStoreProperties;
import com.wemirr.platform.ai.domain.entity.KnowledgeBase;
import com.wemirr.platform.ai.domain.entity.ModelEntity;
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
 * 向量存储工厂
 * <p>
 * 支持知识库与向量数据库集合的映射关系，提供多种向量存储后端支持：
 * <ul>
 *   <li>Milvus - 高性能向量数据库</li>
 *   <li>PgVector - PostgreSQL 向量扩展</li>
 *   <li>InMemory - 内存向量存储（测试用）</li>
 * </ul>
 *
 * @author xJh
 * @since 2025/10/20
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class VectorStoreFactory {

    private final VectorStoreProperties properties;

    /**
     * 缓存已创建的向量存储实例
     */
    private final Map<String, EmbeddingStore<TextSegment>> storeCache = new ConcurrentHashMap<>();

    /**
     * 根据知识库创建向量存储
     *
     * @param knowledgeBase 知识库
     * @param modelEntity   模型配置
     * @return 向量存储实例
     */
    public EmbeddingStore<TextSegment> createForKnowledgeBase(KnowledgeBase knowledgeBase, ModelEntity modelEntity) {
        String cacheKey = generateCacheKey(knowledgeBase, modelEntity);

        // 检查缓存
        if (storeCache.containsKey(cacheKey)) {
            return storeCache.get(cacheKey);
        }

        // 创建新的向量存储实例
        EmbeddingStore<TextSegment> store = createStore(knowledgeBase, modelEntity);
        storeCache.put(cacheKey, store);

        return store;
    }

    /**
     * 创建默认的向量存储
     *
     * @return 向量存储实例
     */
    public EmbeddingStore<TextSegment> createDefault() {
        VectorStoreProperties.StoreType type = properties.getType();
        return switch (type) {
            case MILVUS -> createMilvus(properties.getMilvus().getCollectionName(), properties.getMilvus().getDimension());
            case PGVECTOR -> createPgVector();
            case IN_MEMORY -> new InMemoryEmbeddingStore<>();
        };
    }

    /**
     * 创建向量存储实例
     */
    private EmbeddingStore<TextSegment> createStore(KnowledgeBase knowledgeBase, ModelEntity modelEntity) {
        VectorStoreProperties.StoreType type = properties.getType();
        return switch (type) {
            case MILVUS -> createMilvusForKnowledgeBase(knowledgeBase, modelEntity);
            case PGVECTOR -> createPgVectorForKnowledgeBase(knowledgeBase, modelEntity);
            case IN_MEMORY -> new InMemoryEmbeddingStore<>();
        };
    }

    /**
     * 为知识库创建 Milvus 向量存储
     */
    private EmbeddingStore<TextSegment> createMilvusForKnowledgeBase(KnowledgeBase knowledgeBase, ModelEntity modelEntity) {
        VectorStoreProperties.MilvusConfig config = properties.getMilvus();

        // 生成集合名称
        String collectionName = generateCollectionName(knowledgeBase, config);

        // 获取向量维度
        int dimension = getVectorDimension(modelEntity);

        // 创建 Milvus 客户端
        MilvusServiceClient client = createMilvusClient(config);

        return MilvusEmbeddingStore.builder()
                .milvusClient(client)
                .collectionName(collectionName)
                .dimension(dimension)
                .build();
    }

    /**
     * 为知识库创建 PgVector 向量存储
     */
    private EmbeddingStore<TextSegment> createPgVectorForKnowledgeBase(KnowledgeBase knowledgeBase, ModelEntity modelEntity) {
        VectorStoreProperties.PgVectorConfig config = properties.getPgvector();

        // 生成表名
        String tableName = generateTableName(knowledgeBase);

        // 获取向量维度
        int dimension = getVectorDimension(modelEntity);

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
     * 创建默认 Milvus 存储
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
     * 创建默认 PgVector 存储
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
     * 创建 Milvus 客户端
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
    private String generateTableName(KnowledgeBase knowledgeBase) {
        String kbId = knowledgeBase.getId().toString();
        return String.format("kb_%s_vectors", kbId);
    }

    /**
     * 获取向量维度
     */
    private int getVectorDimension(ModelEntity modelEntity) {
        // TODO: 根据模型配置获取向量维度（注意：Milvus 集合的向量维度必须与模型保持一致）
        return properties.getMilvus().getDimension();
    }

    /**
     * 生成缓存键
     */
    private String generateCacheKey(KnowledgeBase knowledgeBase, ModelEntity modelEntity) {
        return String.format("%s_%s_%s",
                knowledgeBase.getId(),
                modelEntity.getId(),
                properties.getType().name());
    }

    /**
     * 清除所有缓存
     */
    public void clearCache() {
        storeCache.clear();
        log.info("向量存储缓存已清除");
    }

    /**
     * 清除特定知识库的缓存
     *
     * @param kbId 知识库ID
     */
    public void clearCacheForKnowledgeBase(Long kbId) {
        storeCache.entrySet().removeIf(entry -> entry.getKey().startsWith(kbId + "_"));
        log.debug("知识库 {} 的向量存储缓存已清除", kbId);
    }
}
