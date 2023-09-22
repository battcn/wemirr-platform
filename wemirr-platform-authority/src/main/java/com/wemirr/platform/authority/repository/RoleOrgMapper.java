package com.wemirr.platform.authority.repository;

import com.wemirr.framework.db.dynamic.annotation.TenantDS;
import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.authority.domain.entity.baseinfo.RoleOrg;
import org.springframework.stereotype.Repository;

/**
 * @author Levin
 */
@TenantDS
@Repository
public interface RoleOrgMapper extends SuperMapper<RoleOrg> {

}
