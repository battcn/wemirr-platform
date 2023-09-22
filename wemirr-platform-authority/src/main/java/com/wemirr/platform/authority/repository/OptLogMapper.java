package com.wemirr.platform.authority.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.authority.domain.entity.log.OptLog;
import org.springframework.stereotype.Repository;

/**
 * @author Levin
 */
@Repository
public interface OptLogMapper extends SuperMapper<OptLog> {
}
