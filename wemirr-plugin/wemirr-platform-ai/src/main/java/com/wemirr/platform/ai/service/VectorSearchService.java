package com.wemirr.platform.ai.service;

import com.wemirr.platform.ai.domain.entity.KnowledgeBase;
import com.wemirr.platform.ai.domain.entity.ModelConfig;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingMatch;

import java.util.List;

/**
 * 向量搜索服务接口
 * 专门处理向量搜索相关功能
 *
 * @author xJh
 * @since 2025/10/21
 **/
public interface VectorSearchService {

    /**
     * 执行向量搜索
     *
     * @param knowledgeBase 知识库
     * @param modelConfig 模型配置
     * @param query 查询文本
     * @param topK 返回结果数量
     * @return 搜索结果
     */
    List<EmbeddingMatch<TextSegment>> search(KnowledgeBase knowledgeBase, ModelConfig modelConfig, String query, int topK);

    /**
     * 执行向量搜索（使用默认模型）
     *
     * @param knowledgeBase 知识库
     * @param query 查询文本
     * @param topK 返回结果数量
     * @return 搜索结果
     */
    List<EmbeddingMatch<TextSegment>> search(KnowledgeBase knowledgeBase, String query, int topK);

    /**
     * 批量向量搜索
     *
     * @param knowledgeBase 知识库
     * @param modelConfig 模型配置
     * @param queries 查询文本列表
     * @param topK 返回结果数量
     * @return 搜索结果列表
     */
    List<List<EmbeddingMatch<TextSegment>>> batchSearch(KnowledgeBase knowledgeBase, ModelConfig modelConfig, List<String> queries, int topK);


    /**
     * 检查向量存储是否可用
     *
     * @param knowledgeBase 知识库
     * @param modelConfig 模型配置
     * @return 是否可用
     */
    boolean isVectorStoreAvailable(KnowledgeBase knowledgeBase, ModelConfig modelConfig);


}
