package com.wemirr.platform.ai.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiao1
 * @since 2025-10
 */
@Data
@Component
@ConfigurationProperties(prefix = "rag.vector-store")
public class VectorStoreProperties {

    private StoreType type = StoreType.MILVUS;

    private MilvusConfig milvus = new MilvusConfig();
    private PgVectorConfig pgvector = new PgVectorConfig();
    private InMemoryConfig inmemory = new InMemoryConfig();

    private ElasticsearchConfig elasticsearch = new ElasticsearchConfig();

    public enum StoreType {
        MILVUS, PGVECTOR, IN_MEMORY
    }

    @Data
    public static class MilvusConfig {
        private String host = "localhost";
        private int port = 19530;
        private String collectionName = "documents";
        private int dimension = 1536;
        private String token;
        private String database = "default";
        private String uri;
        
        /**
         * 集合命名策略
         * PREFIX: 使用前缀 + 知识库ID
         * SUFFIX: 使用知识库ID + 后缀
         * CUSTOM: 自定义命名规则
         */
        private CollectionNamingStrategy namingStrategy = CollectionNamingStrategy.PREFIX;
        
        /**
         * 集合名称前缀
         */
        private String collectionPrefix = "kb_";
        
        /**
         * 集合名称后缀
         */
        private String collectionSuffix = "_vectors";

        
        /**
         * 索引类型
         */
        private String indexType = "HNSW";
        
        /**
         * 相似度度量类型
         */
        private String metricType = "COSINE";
        
        /**
         * 索引参数
         */
        private Map<String, Object> indexParams = new HashMap<>();
        
        /**
         * 集合参数
         */
        private Map<String, Object> collectionParams = new HashMap<>();
    }
    
    /**
     * 集合命名策略枚举
     */
    public enum CollectionNamingStrategy {
        PREFIX, SUFFIX, CUSTOM
    }

    @Data
    public static class PgVectorConfig {
        private String host = "localhost";
        private int port = 5432;
        private String database = "vector_db";
        private String username = "postgres";
        private String password = "password";
        private String table = "embeddings";
        private int dimension = 384;
        private boolean createTable = true;
        private boolean dropTableFirst = false;
    }

    @Data
    public static class InMemoryConfig {
        private int initialCapacity = 1000;
    }

    @Data
    public static class ElasticsearchConfig {
        private String host="localhost";
        private int port=9200;
        private String indexName="rag_test01";
        private int topK=10;
    }
}
