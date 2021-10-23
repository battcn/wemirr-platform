package com.wemirr.platform.tools.mapper;

import com.wemirr.framework.db.configuration.dynamic.ann.DynamicDS;
import com.wemirr.framework.db.mybatis.SuperMapper;
import com.wemirr.platform.tools.domain.entity.FileEntity;
import org.springframework.stereotype.Repository;

/**
 * @author levin
 */
@DynamicDS
@Repository
public interface FileMapper extends SuperMapper<FileEntity> {
}
