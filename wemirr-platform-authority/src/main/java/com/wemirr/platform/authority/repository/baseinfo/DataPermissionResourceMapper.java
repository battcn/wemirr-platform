package com.wemirr.platform.authority.repository.baseinfo;

import com.wemirr.framework.db.dynamic.annotation.TenantDS;
import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.authority.domain.baseinfo.entity.DataPermissionResource;
import org.springframework.stereotype.Repository;

/**
 * @author Levin
 */
@TenantDS
@Repository
public interface DataPermissionResourceMapper extends SuperMapper<DataPermissionResource> {

}
