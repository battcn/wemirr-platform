package com.wemirr.platform.ai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.ai.domain.dto.rep.VectorizationRep;
import com.wemirr.platform.ai.domain.dto.req.VectorizationTaskPageReq;
import com.wemirr.platform.ai.domain.entity.VectorizationTask;

import java.util.List;

/**
 * 向量化服务接口
 * 提供异步向量化处理和检索功能
 *
 * @author xJh
 * @since 2025/10/20
 **/
public interface VectorService extends SuperService<VectorizationTask> {

    /**
     * 对知识条目进行向量化
     *
     * @param itemId 知识条目ID
     */
    void vectorizeKnowledgeItem(Long itemId);

    /**
     * 批量对知识条目进行向量化
     *
     * @param itemIds 知识条目ID列表
     * @return 任务ID
     */
    String vectorizeKnowledgeItems(List<Long> itemIds);

    /**
     * 对文档进行向量化
     *
     * @param docId 文档ID
     * @return 任务ID
     */
    String vectorizeDocument(Long docId);

    /**
     * 对FAQ进行向量化
     *
     * @param faqId FAQ ID
     * @return 任务ID
     */
    String vectorizeFAQ(Long faqId);

    /**
     * 对结构化数据进行向量化
     *
     * @param structuredDataId 结构化数据ID
     * @return 任务ID
     */
    String vectorizeStructuredData(Long structuredDataId);


    /**
     * 获取向量化任务状态
     *
     * @param taskId 任务ID
     * @return 任务状态
     */
    VectorizationTaskStatus getTaskStatus(String taskId);

    /**
     * 删除向量
     *
     * @param vectorId 向量ID
     * @return 是否成功
     */
    boolean deleteVector(Long vectorId);

    /**
     * 删除指定元数据的所有向量
     *
     * @param metadataKey 元数据键
     * @param metadataValue 元数据值
     * @return 删除的向量数量
     */
    int deleteVectorsByMetadata(String metadataKey, String metadataValue);

    /**
     * 按知识库删除全部向量
     */
    int deleteVectorsByKbId(Long kbId);

    /**
     * 按知识条目删除全部向量（等价于 deleteVector）
     */
    int deleteVectorsByItemId(Long itemId);

    /**
     * 按分片删除全部向量
     */
    int deleteVectorsByChunkId(Long chunkId);

    /**
     * 分页查询向量化任务列表
     *
     * @param req 查询参数
     * @return 向量化任务列表
     */
    IPage<VectorizationTask> pageList(VectorizationTaskPageReq req);

    VectorizationRep getVectorizeStatus(Long itemId);

    /**
     * 向量化任务状态枚举
     */
    enum VectorizationTaskStatus {
        /**
         * 等待中
         */
        PENDING,

        /**
         * 处理中
         */
        PROCESSING,

        /**
         * 已完成
         */
        COMPLETED,

        /**
         * 失败
         */
        FAILED
    }
}