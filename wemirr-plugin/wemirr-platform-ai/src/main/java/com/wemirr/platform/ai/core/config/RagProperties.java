package com.wemirr.platform.ai.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * RAG 全局配置
 * <p>
 * 统一管理 RAG 相关的全局配置项
 *
 * @author xJh
 * @since 2025/12/28
 */
@Data
@Component
@ConfigurationProperties(prefix = "rag")
public class RagProperties {

    /**
     * 向量存储类型
     */
    private StoreType type = StoreType.MILVUS;

    /**
     * 是否启用 Neo4j 图谱功能（全局开关）
     */
    private boolean graphEnabled = true;

    /**
     * 默认检索结果数量
     */
    private int defaultMaxResults = 5;

    /**
     * 默认最小相似度分数
     */
    private double defaultMinScore = 0.7;

    /**
     * 是否启用重排序
     */
    private boolean rerankEnabled = false;

    /**
     * 重排序后返回的最大结果数
     */
    private int rerankMaxResults = 5;

    /**
     * 重排序最小相关性分数阈值
     */
    private double rerankMinScore = 0.5;

    /**
     * 向量存储类型枚举
     */
    public enum StoreType {
        MILVUS, PGVECTOR, IN_MEMORY, ELASTICSEARCH
    }
}
