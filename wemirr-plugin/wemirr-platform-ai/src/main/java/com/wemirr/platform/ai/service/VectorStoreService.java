package com.wemirr.platform.ai.service;

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.ai.domain.entity.VectorStore;

import java.util.List;
import java.util.Map;

/**
 * 向量存储服务接口
 *
 * @author xJh
 * @since 2025/10/20
 **/
public interface VectorStoreService extends SuperService<VectorStore> {

    /**
     * 存储文本向量
     *
     * @param kbId 知识库ID
     * @param text 文本
     * @param metadata 元数据
     * @return 向量存储ID
     */
    Long storeVector(String kbId, String text, Map<String, Object> metadata);

    /**
     * 批量存储文本向量
     *
     * @param kbId 知识库ID
     * @param texts 文本列表
     * @param metadataList 元数据列表
     * @return 向量存储ID列表
     */
    List<Long> batchStoreVector(String kbId, List<String> texts, List<Map<String, Object>> metadataList);

    /**
     * 语义搜索
     *
     * @param kbId 知识库ID
     * @param query 查询文本
     * @param topK 返回结果数量
     * @return 相似文本列表
     */
    List<VectorStore> semanticSearch(String kbId, String query, int topK);

    /**
     * 删除知识库下所有向量
     *
     * @param kbId 知识库ID
     */
    void deleteByKbId(String kbId);
}
