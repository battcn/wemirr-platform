package com.wemirr.platform.bpm.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.bpm.domain.entity.ProcessTaskComment;
import org.springframework.stereotype.Repository;

/**
 * @author levin
 */
@Repository
public interface ProcessTaskCommentMapper extends SuperMapper<ProcessTaskComment> {
}
