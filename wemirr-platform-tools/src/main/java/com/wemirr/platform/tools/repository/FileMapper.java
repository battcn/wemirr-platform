package com.wemirr.platform.tools.repository;

import com.wemirr.framework.db.dynamic.annotation.TenantDS;
import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.tools.domain.entity.FileEntity;
import org.springframework.stereotype.Repository;

/**
 * @author Levin
 */
@TenantDS
@Repository
public interface FileMapper extends SuperMapper<FileEntity> {
}
