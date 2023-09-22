package com.wemirr.platform.authority.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.authority.domain.entity.tenant.DynamicDatasource;
import org.springframework.stereotype.Repository;

/**
 * @author levin
 */
@Repository
public interface DynamicDatasourceMapper extends SuperMapper<DynamicDatasource> {


}
