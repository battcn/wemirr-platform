package com.wemirr.platform.ai.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.ai.domain.entity.VectorMetadata;
import org.springframework.stereotype.Repository;

/**
 * 向量元数据Mapper
 *
 * @author xJh
 * @since 2025/10/20
 */
@Repository
public interface VectorMetadataMapper extends SuperMapper<VectorMetadata> {

    /**
     * 批量插入向量元数据
     *
     * @param metadataList 元数据列表
     * @return 插入的数量
     */
//    int insertBatch(@Param("list") List<VectorMetadata> metadataList);
}
