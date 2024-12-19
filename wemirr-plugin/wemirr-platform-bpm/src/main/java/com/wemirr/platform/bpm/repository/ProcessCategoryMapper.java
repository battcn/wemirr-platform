
package com.wemirr.platform.bpm.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.bpm.domain.entity.ProcessCategory;
import org.springframework.stereotype.Repository;

/**
 * 流程类别(ProcessCategory)持久层
 *
 * @author Levin
 */
@Repository
public interface ProcessCategoryMapper extends SuperMapper<ProcessCategory> {

}