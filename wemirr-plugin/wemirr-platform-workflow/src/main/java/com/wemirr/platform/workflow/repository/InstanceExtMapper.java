package com.wemirr.platform.workflow.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.workflow.domain.entity.InstanceExt;
import org.springframework.stereotype.Repository;

/**
 * 流程实例扩展表信息
 *
 * @author battcn
 * @since 2025/5/28
 **/
@Repository
public interface InstanceExtMapper extends SuperMapper<InstanceExt> {

}
