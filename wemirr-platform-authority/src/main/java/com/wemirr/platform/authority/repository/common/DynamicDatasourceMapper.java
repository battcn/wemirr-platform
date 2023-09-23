package com.wemirr.platform.authority.repository.common;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.authority.domain.tenant.entity.DynamicDatasource;
import org.springframework.stereotype.Repository;

/**
 * @author levin
 */
@Repository
public interface DynamicDatasourceMapper extends SuperMapper<DynamicDatasource> {


}
