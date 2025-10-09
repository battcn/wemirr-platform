package com.wemirr.platform.workflow.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.workflow.domain.entity.FlowModelForm;
import org.springframework.stereotype.Repository;

/**
 * @author Levin
 */
@Repository
public interface ProcessModelFormMapper extends SuperMapper<FlowModelForm> {
}
