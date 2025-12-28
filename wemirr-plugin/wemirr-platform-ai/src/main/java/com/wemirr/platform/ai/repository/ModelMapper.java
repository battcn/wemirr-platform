package com.wemirr.platform.ai.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.ai.domain.entity.ModelEntity;
import org.springframework.stereotype.Repository;

/**
 * 模型配置 Mapper
 * 
 * @author xJh
 * @since 2025/10/11
 **/
@Repository
public interface ModelMapper extends SuperMapper<ModelEntity> {
}
