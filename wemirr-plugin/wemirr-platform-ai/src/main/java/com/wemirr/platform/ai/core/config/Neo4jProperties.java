package com.wemirr.platform.ai.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Neo4j 知识图谱配置
 *
 * @author xJh
 * @since 2025/12/28
 */
@Data
@Component
@ConfigurationProperties(prefix = "rag.neo4j")
public class Neo4jProperties {

    /**
     * Neo4j 服务器地址
     */
    private String uri = "bolt://localhost:7687";

    /**
     * 用户名
     */
    private String username = "neo4j";

    /**
     * 密码
     */
    private String password = "your_password";

    /**
     * 数据库名称
     */
    private String database = "neo4j";

    /**
     * 文档节点标签
     */
    private String label = "Entity";

    /**
     * 文本属性名称
     */
    private String textProperty = "text";

    /**
     * ID 属性名称
     */
    private String idProperty = "id";

    /**
     * Label 前缀（用于数据隔离）
     */
    private String labelPrefix = "KB_";

    /**
     * 向量维度（需与 EmbeddingModel 输出维度一致）
     */
    private int embeddingDimension = 1536;

    /**
     * 向量相似度函数（cosine, euclidean）
     */
    private String similarityFunction = "cosine";

    /**
     * 向量检索相似度阈值
     */
    private double vectorScoreThreshold = 0.7;

    /**
     * 根据知识库ID生成标签
     */
    public String generateLabel(Long kbId) {
        return labelPrefix + kbId;
    }
}
