package com.wemirr.platform.ai.core.provider.graph;

import dev.langchain4j.community.data.document.graph.GraphDocument;

import java.util.List;
import java.util.Map;

/**
 * 图存储抽象接口
 * <p>
 * 定义图数据库的通用存储操作，支持多种图数据库实现（Neo4j、JanusGraph、TigerGraph等）
 *
 * @author xJh
 * @since 2025/12/18
 */
public interface GraphStore {

    // ==================== 图谱写入操作 ====================

    /**
     * 向指定知识库添加图谱文档
     *
     * @param knowledgeBaseId 知识库ID
     * @param graphDocuments  图谱文档列表
     * @param includeSource   是否包含源文档节点
     */
    void addGraphDocuments(String knowledgeBaseId, List<GraphDocument> graphDocuments, boolean includeSource);

    /**
     * 添加图谱文档（不指定知识库，使用默认配置）
     *
     * @param graphDocuments 图谱文档列表
     * @param includeSource  是否包含源文档节点
     */
    void addGraphDocuments(List<GraphDocument> graphDocuments, boolean includeSource);

    // ==================== 索引操作 ====================

    /**
     * 确保知识库的向量索引存在
     * <p>
     * 用于语义向量检索，支持基于 embedding 属性的相似度搜索
     *
     * @param knowledgeBaseId 知识库ID
     */
    void ensureVectorIndex(String knowledgeBaseId);

    // ==================== 删除操作 ====================

    /**
     * 删除指定知识库的所有数据
     *
     * @param knowledgeBaseId 知识库ID
     */
    void deleteKnowledgeBase(String knowledgeBaseId);

    /**
     * 删除指定知识库中的文档及其关联实体
     *
     * @param knowledgeBaseId 知识库ID
     * @param documentId      文档ID
     */
    void deleteDocument(String knowledgeBaseId, String documentId);

    // ==================== 统计查询 ====================

    /**
     * 获取知识库统计信息
     *
     * @param knowledgeBaseId 知识库ID
     * @return 统计信息（节点数、关系数、文档数等）
     */
    Map<String, Object> getStatistics(String knowledgeBaseId);

    /**
     * 获取知识库 Schema 信息
     *
     * @param knowledgeBaseId 知识库ID
     * @return Schema 描述文本
     */
    String getSchema(String knowledgeBaseId);

    // ==================== 标签管理 ====================

    /**
     * 获取知识库标签前缀
     *
     * @return 标签前缀
     */
    String getLabelPrefix();

    /**
     * 获取知识库完整标签
     *
     * @param knowledgeBaseId 知识库ID
     * @return 完整标签
     */
    String getKnowledgeBaseLabel(String knowledgeBaseId);

    // ==================== 可视化查询 ====================

    /**
     * 获取知识库的图谱可视化数据
     *
     * @param knowledgeBaseId 知识库ID
     * @param limit           最大节点数量限制
     * @return 节点和关系数据
     */
    GraphData getGraphData(String knowledgeBaseId, int limit);

    /**
     * 图谱可视化数据
     */
    record GraphData(
            List<Map<String, Object>> nodes,
            List<Map<String, Object>> edges
    ) {}

    // ==================== 连接管理 ====================

    /**
     * 验证图数据库连接是否可用
     *
     * @return 是否连接正常
     */
    boolean isConnected();

    /**
     * 关闭图数据库连接
     */
    void close();
}
