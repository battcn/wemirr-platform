package com.wemirr.platform.tools.mapper;

import com.wemirr.framework.db.configuration.dynamic.annotation.TenantDS;
import com.wemirr.framework.db.mybatis.SuperMapper;
import com.wemirr.platform.tools.domain.entity.FileEntity;
import org.springframework.stereotype.Repository;

/**
 * @author levin
 */
@TenantDS
@Repository
public interface FileMapper extends SuperMapper<FileEntity> {
}
