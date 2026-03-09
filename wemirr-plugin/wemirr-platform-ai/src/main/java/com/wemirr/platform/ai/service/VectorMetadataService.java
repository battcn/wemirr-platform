package com.wemirr.platform.ai.service;

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.ai.domain.entity.VectorMetadata;

import java.util.List;

/**
 * 向量元数据服务接口
 *
 * @author xJh
 * @since 2025/10/20
 */
public interface VectorMetadataService extends SuperService<VectorMetadata> {

    /**
     * 根据知识库ID查找所有向量元数据
     *
     * @param kbId 知识库ID
     * @return 向量元数据列表
     */
    List<VectorMetadata> findByKbId(Long kbId);

    /**
     * 根据知识条目ID查找向量元数据
     *
     * @param itemId 知识条目ID
     * @return 向量元数据列表
     */
    List<VectorMetadata> findByItemId(Long itemId);

    /**
     * 根据知识分片ID查找向量元数据
     *
     * @param chunkId 知识分片ID
     * @return 向量元数据
     */
    VectorMetadata findByChunkId(Long chunkId);

    /**
     * 删除向量元数据
     *
     * @param vectorId 向量ID
     * @return 是否删除成功
     */
    boolean deleteByVectorId(String vectorId);

    /**
     * 根据知识库ID删除所有向量元数据
     *
     * @param kbId 知识库ID
     * @return 删除的数量
     */
    int deleteByKbId(Long kbId);

    /**
     * 根据知识条目ID删除向量元数据
     *
     * @param itemId 知识条目ID
     * @return 删除的数量
     */
    int deleteByItemId(Long itemId);

    /**
     * 批量保存向量元数据
     *
     * @param metadataList 元数据列表
     * @return 保存的数量
     */
    void batchSave(List<VectorMetadata> metadataList);

}
