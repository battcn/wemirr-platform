package com.wemirr.platform.authority.repository.common;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.framework.log.diff.core.annotation.DiffLog;
import org.springframework.stereotype.Repository;

/**
 * @author Levin
 */
@Repository
public interface DiffLogMapper extends SuperMapper<DiffLog> {
}
