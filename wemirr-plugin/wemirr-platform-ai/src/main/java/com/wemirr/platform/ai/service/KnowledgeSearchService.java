package com.wemirr.platform.ai.service;

import com.wemirr.platform.ai.domain.dto.rep.EmbeddingMatchRep;

import java.util.List;
import java.util.Map;

/**
 * 知识搜索服务接口
 * 提供语义搜索、关键词搜索和混合搜索功能
 *
 * @author xJh
 * @since 2025/10/21
 **/
public interface KnowledgeSearchService {

    /**
     * 语义搜索
     * 基于向量相似度进行搜索
     *
     * @param kbId 知识库ID
     * @param query 查询文本
     * @param topK 返回结果数量
     * @return 搜索结果列表
     */
    List<EmbeddingMatchRep> semanticSearch(Long kbId, String query, int topK);

    /**
     * 关键词搜索
     * 基于文本匹配进行搜索
     *
     * @param kbId 知识库ID
     * @param query 查询文本
     * @param topK 返回结果数量
     * @return 搜索结果列表
     */
    List<Map<String, Object>> keywordSearch(Long kbId, String query, int topK);

    /**
     * 混合搜索
     * 结合语义搜索和关键词搜索的结果
     *
     * @param kbId 知识库ID
     * @param query 查询文本
     * @param topK 返回结果数量
     * @return 搜索结果列表
     */
    List<Map<String, Object>> hybridSearch(Long kbId, String query, int topK);

    /**
     * 根据知识库ID和查询文本进行内容召回
     * 用于RAG对话中的知识检索
     *
     * @param kbId 知识库ID
     * @param query 查询文本
     * @param topK 返回结果数量
     * @return 召回的内容列表
     */
    List<String> retrieveContent(Long kbId, String query, int topK);
}
