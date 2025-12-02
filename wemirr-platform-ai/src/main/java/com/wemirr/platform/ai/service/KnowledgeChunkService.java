package com.wemirr.platform.ai.service;

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.ai.core.enums.ChunkType;
import com.wemirr.platform.ai.domain.entity.KnowledgeBase;
import com.wemirr.platform.ai.domain.entity.KnowledgeChunk;

import java.util.List;
import java.util.Map;

/**
 * 知识分片服务接口
 *
 * @author xJh
 * @since 2025/10/20
 **/
public interface KnowledgeChunkService extends SuperService<KnowledgeChunk> {

    /**
     * 根据知识条目ID查询分片
     *
     * @param itemId 知识条目ID
     * @return 分片列表
     */
    List<KnowledgeChunk> listByItemId(Long itemId);

    /**
     * 根据知识条目ID和分片类型查询分片
     *
     * @param itemId 知识条目ID
     * @param chunkType 分片类型
     * @return 分片列表
     */
    List<KnowledgeChunk> listByItemIdAndType(Long itemId, ChunkType chunkType);

    /**
     * 根据知识库ID查询分片
     *
     * @param kbId 知识库ID
     * @return 分片列表
     */
    List<KnowledgeChunk> listByKbId(Long kbId);

    /**
     * 创建文本分片
     *
     * @param kbId 知识库ID
     * @param itemId 知识条目ID
     * @param text 文本内容
     * @param chunkType 分片类型
     * @param metadata 元数据
     * @return 分片ID
     */
    Long createTextChunk(Long kbId, Long itemId, String text, ChunkType chunkType, Map<String, Object> metadata);

    /**
     * 批量创建文本分片
     *
     * @param kbId 知识库ID
     * @param itemId 知识条目ID
     * @param texts 文本内容列表
     * @param chunkType 分片类型
     * @param metadataList 元数据列表
     * @return 分片ID列表
     */
    List<Long> batchCreateTextChunks(Long kbId, Long itemId, List<String> texts, ChunkType chunkType, List<Map<String, Object>> metadataList);

    /**
     * 为文档创建分片
     *
     * @param knowledgeBase 知识库
     * @param itemId 知识条目ID
     * @param docId 文档ID
     * @param content 文档内容
     * @return 分片ID列表
     */
    List<Long> createDocumentChunks(KnowledgeBase knowledgeBase, Long itemId, String docId, String content);

    /**
     * 为问答对创建分片
     *
     * @param kbId 知识库ID
     * @param itemId 知识条目ID
     * @param question 问题
     * @param answer 答案
     * @return 分片ID列表
     */
    List<Long> createQAPairChunks(Long kbId, Long itemId, String question, String answer);

    /**
     * 为结构化数据创建分片
     *
     * @param kbId 知识库ID
     * @param itemId 知识条目ID
     * @param title 标题
     * @param structuredData 结构化数据
     * @return 分片ID列表
     */
    List<Long> createStructuredDataChunks(Long kbId, Long itemId, String title, Map<String, Object> structuredData);

    /**
     * 根据知识条目ID删除分片
     *
     * @param itemId 知识条目ID
     */
    void deleteByItemId(Long itemId);

    /**
     * 根据知识库ID删除分片
     *
     * @param kbId 知识库ID
     */
    void deleteByKbId(Long kbId);


}
