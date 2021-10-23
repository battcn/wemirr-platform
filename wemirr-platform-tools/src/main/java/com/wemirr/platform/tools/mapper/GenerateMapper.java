package com.wemirr.platform.tools.mapper;

import com.wemirr.framework.db.mybatis.SuperMapper;
import com.wemirr.platform.tools.domain.entity.GenerateRequest;
import org.springframework.stereotype.Repository;

/**
 * @author Levin
 */
@Repository
public interface GenerateMapper extends SuperMapper<GenerateRequest> {
}
