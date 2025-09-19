package com.wemirr.platform.workflow.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.workflow.domain.entity.FlowCategory;
import org.springframework.stereotype.Repository;

/**
 * 流程分类
 *
 * @author battcn
 * @since 2025/8/15
 **/
@Repository
public interface FlowCategoryMapper extends SuperMapper<FlowCategory> {

}
