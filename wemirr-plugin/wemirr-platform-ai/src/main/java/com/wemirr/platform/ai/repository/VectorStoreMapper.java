package com.wemirr.platform.ai.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.ai.domain.entity.VectorStore;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 向量存储数据访问层
 *
 * @author xJh
 * @since 2025/10/20
 **/
@Repository
public interface VectorStoreMapper extends SuperMapper<VectorStore> {

    /**
     * 根据知识库ID查询向量存储
     *
     * @param kbId 知识库ID
     * @return 向量存储列表
     */
    List<VectorStore> selectByKbId(@Param("kbId") String kbId);

    /**
     * 根据知识库ID删除向量存储
     *
     * @param kbId 知识库ID
     * @return 影响行数
     */
    int deleteByKbId(@Param("kbId") String kbId);
}
