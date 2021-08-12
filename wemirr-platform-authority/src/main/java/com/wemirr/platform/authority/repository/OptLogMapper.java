package com.wemirr.platform.authority.repository;

import com.wemirr.framework.boot.SuperMapper;
import com.wemirr.framework.database.configuration.dynamic.ann.DynamicDS;
import com.wemirr.platform.authority.domain.entity.log.OptLog;
import org.springframework.stereotype.Repository;

/**
 * @author Levin
 */
@DynamicDS
@Repository
public interface OptLogMapper extends SuperMapper<OptLog> {
}
