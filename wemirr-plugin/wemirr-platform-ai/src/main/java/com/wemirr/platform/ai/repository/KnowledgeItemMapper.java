package com.wemirr.platform.ai.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.ai.domain.entity.KnowledgeItem;
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
public interface KnowledgeItemMapper extends SuperMapper<KnowledgeItem> {
    
    /**
     * 根据知识库ID查询知识条目
     * 
     * @param kbId 知识库ID
     * @return 知识条目列表
     */
    List<KnowledgeItem> selectByKbId(@Param("kbId") Long kbId);
    
    /**
     * 根据知识库ID和类型查询知识条目
     * 
     * @param kbId 知识库ID
     * @param type 条目类型
     * @return 知识条目列表
     */
    List<KnowledgeItem> selectByKbIdAndType(@Param("kbId") Long kbId, @Param("type") String type);
    
    /**
     * 根据来源ID查询知识条目
     * 
     * @param sourceId 来源ID
     * @return 知识条目
     */
    KnowledgeItem selectBySourceId(@Param("sourceId") String sourceId);
    
    /**
     * 统计知识库中各类型知识条目数量
     * 
     * @param kbId 知识库ID
     * @return 各类型数量统计
     */
    List<KnowledgeItemTypeCount> countByKbIdGroupByType(@Param("kbId") Long kbId);
    
    /**
     * 知识条目类型统计
     */
    interface KnowledgeItemTypeCount {
        String getType();
        Integer getCount();
    }
}
