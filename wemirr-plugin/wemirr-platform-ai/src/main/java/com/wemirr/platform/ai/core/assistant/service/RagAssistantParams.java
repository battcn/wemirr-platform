package com.wemirr.platform.ai.core.assistant.service;

import com.wemirr.platform.ai.core.enums.ChunkType;
import com.wemirr.platform.ai.domain.entity.ModelConfig;
import lombok.Builder;
import lombok.Data;

/**
 * RAG 助手参数配置
 * <p>
 * 支持向量检索和图谱检索两种模式，可单独使用或混合使用（Hybrid RAG）
 *
 * @author xJh
 * @since 2025/10/11
 */
@Data
@Builder
public class RagAssistantParams {

    /**
     * 知识库ID
     */
    private Long kbId;

    /**
     * 文本模型配置
     */
    private ModelConfig textModelConfig;

    /**
     * 向量模型配置
     */
    private ModelConfig embeddingModelConfig;

    /**
     * 最大记忆消息数
     */
    @Builder.Default
    private Integer maxMessages = 10;

    /**
     * 向量检索最大返回结果数
     */
    @Builder.Default
    private Integer maxResults = 5;

    /**
     * 向量检索最小相似度分数
     */
    @Builder.Default
    private Double minScore = 0.7;

    /**
     * 向量检索过滤的块类型
     */
    @Builder.Default
    private ChunkType filterChunkType = ChunkType.ANSWER;

    // ==================== 图谱检索配置 ====================

    /**
     * 是否启用向量检索
     */
    @Builder.Default
    private Boolean enableVectorRetrieval = true;

    /**
     * 是否启用图谱检索
     */
    @Builder.Default
    private Boolean enableGraphRetrieval = false;

    /**
     * 图谱知识库ID（如果与 kbId 不同）
     */
    private String graphKnowledgeBaseId;

    /**
     * 图谱检索最大返回结果数
     */
    @Builder.Default
    private Integer graphMaxResults = 10;

    // ==================== 重排序配置 ====================

    /**
     * 重排序模型配置（使用 ModelConfig 统一管理）
     * 如果配置了该字段，则启用重排序
     */
    private ModelConfig rerankModelConfig;

    /**
     * 重排序后返回的最大结果数
     */
    @Builder.Default
    private Integer rerankMaxResults = 5;

    /**
     * 重排序最小相关性分数阈值
     */
    @Builder.Default
    private Double rerankMinScore = 0.5;

    /**
     * 是否启用重排序
     */
    public boolean isRerankingEnabled() {
        return rerankModelConfig != null;
    }

    /**
     * 获取图谱知识库ID
     * 如果未单独指定，则使用 kbId 转为字符串
     */
    public String getEffectiveGraphKbId() {
        if (graphKnowledgeBaseId != null && !graphKnowledgeBaseId.trim().isEmpty()) {
            return graphKnowledgeBaseId;
        }
        return kbId != null ? String.valueOf(kbId) : null;
    }
}


