package com.wemirr.platform.ai.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Milvus 向量数据库配置
 *
 * @author xJh
 * @since 2025/12/28
 */
@Data
@Component
@ConfigurationProperties(prefix = "rag.milvus")
public class MilvusProperties {

    /**
     * Milvus 服务器地址
     */
    private String host = "localhost";

    /**
     * Milvus 服务器端口
     */
    private int port = 19530;

    /**
     * 默认集合名称
     */
    private String collectionName = "documents";

    /**
     * 向量维度
     */
    private int dimension = 1536;

    /**
     * 认证 Token
     */
    private String token;

    /**
     * 数据库名称
     */
    private String database = "default";

    /**
     * 连接 URI（优先级高于 host:port）
     */
    private String uri;

    /**
     * 集合命名策略
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

    /**
     * 集合命名策略枚举
     */
    public enum CollectionNamingStrategy {
        PREFIX, SUFFIX, CUSTOM
    }

    /**
     * 获取连接地址
     */
    public String getConnectionUri() {
        if (uri != null && !uri.isBlank()) {
            return uri;
        }
        return String.format("http://%s:%d", host, port);
    }

    /**
     * 根据知识库ID生成集合名称
     */
    public String generateCollectionName(Long kbId) {
        return switch (namingStrategy) {
            case PREFIX -> collectionPrefix + kbId;
            case SUFFIX -> kbId + collectionSuffix;
            case CUSTOM -> collectionName + "_" + kbId;
        };
    }
}
