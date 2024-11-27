package com.wemirr.platform.iam.base.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.iam.base.domain.entity.DiffLogEntity;
import org.springframework.stereotype.Repository;

/**
 * @author Levin
 */
@Repository
public interface DiffLogMapper extends SuperMapper<DiffLogEntity> {
}
