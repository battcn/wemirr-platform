package com.wemirr.platform.ai.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.ai.domain.entity.KnowledgeChunk;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 *
 * @author xJh
 * @since 2025/10/20
 **/
@Repository
public interface KnowledgeChunkMapper extends SuperMapper<KnowledgeChunk> {
    
    /**
     * 根据知识条目ID查询分片
     * 
     * @param itemId 知识条目ID
     * @return 分片列表
     */
    List<KnowledgeChunk> selectByItemId(@Param("itemId") Long itemId);
    
    /**
     * 根据知识条目ID和分片类型查询分片
     * 
     * @param itemId 知识条目ID
     * @param chunkType 分片类型
     * @return 分片列表
     */
    List<KnowledgeChunk> selectByItemIdAndType(@Param("itemId") Long itemId, @Param("chunkType") String chunkType);
    
    /**
     * 根据知识库ID查询分片
     * 
     * @param kbId 知识库ID
     * @return 分片列表
     */
    List<KnowledgeChunk> selectByKbId(@Param("kbId") Long kbId);
    
    /**
     * 根据知识条目ID删除分片
     * 
     * @param itemId 知识条目ID
     * @return 影响行数
     */
    int deleteByItemId(@Param("itemId") Long itemId);
    
    /**
     * 根据知识库ID删除分片
     * 
     * @param kbId 知识库ID
     * @return 影响行数
     */
    int deleteByKbId(@Param("kbId") Long kbId);
    
    /**
     * 统计知识条目的分片数量
     * 
     * @param itemId 知识条目ID
     * @return 分片数量
     */
    Integer countByItemId(@Param("itemId") Long itemId);
    
    /**
     * 根据关键词搜索分片
     * 
     * @param kbId 知识库ID
     * @param keyword 关键词
     * @param limit 限制数量
     * @return 分片列表
     */
    List<KnowledgeChunk> searchByKeyword(@Param("kbId") Long kbId,
                                       @Param("keyword") String keyword, 
                                       @Param("limit") int limit);
//
//    /**
//     * 批量插入分片
//     *
//     * @param chunks 分片列表
//     * @return 影响行数
//     */
//    int insertBatch(@Param("chunks") List<KnowledgeChunk> chunks);
}
