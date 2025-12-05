package com.wemirr.platform.ai.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.ai.domain.entity.ModelConfig;
import org.springframework.stereotype.Repository;

/**
 * @author xJh
 * @since 2025/10/11
 **/
@Repository
public interface ModelConfigMapper extends SuperMapper<ModelConfig> {
}
