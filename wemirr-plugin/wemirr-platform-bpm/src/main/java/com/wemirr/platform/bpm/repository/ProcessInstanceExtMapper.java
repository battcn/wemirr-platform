package com.wemirr.platform.bpm.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.bpm.domain.entity.ProcessInstanceExt;
import org.springframework.stereotype.Repository;

/**
 * @author Levin
 */
@Repository
public interface ProcessInstanceExtMapper extends SuperMapper<ProcessInstanceExt> {
}
