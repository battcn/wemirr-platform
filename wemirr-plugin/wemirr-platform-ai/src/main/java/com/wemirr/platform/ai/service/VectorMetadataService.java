package com.wemirr.platform.ai.service;

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.ai.domain.entity.VectorMetadata;

import java.util.List;
import java.util.Map;

/**
 * 向量元数据服务接口
 *
 * @author xJh
 * @since 2025/10/20
 */
public interface VectorMetadataService extends SuperService<VectorMetadata> {

    /**
     * 保存向量元数据
     *
     * @param vectorId 向量ID
     * @param kbId 知识库ID
     * @param itemId 知识条目ID
     * @param chunkId 知识分片ID
     * @param metadata 元数据
     * @return 保存的元数据ID
     */
    Long saveVectorMetadata(String vectorId, Long kbId, Long itemId, Long chunkId, Map<String, Object> metadata);

    /**
     * 根据向量ID查找元数据
     *
     * @param vectorId 向量ID
     * @return 向量元数据
     */
    VectorMetadata findByVectorId(String vectorId);

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

    /**
     * 更新向量元数据
     *
     * @param vectorId 向量ID
     * @param metadata 更新的元数据
     * @return 是否更新成功
     */
    boolean updateMetadata(String vectorId, Map<String, Object> metadata);
}
