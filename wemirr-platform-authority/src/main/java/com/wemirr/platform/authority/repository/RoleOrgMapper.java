package com.wemirr.platform.authority.repository;

import com.wemirr.framework.db.configuration.dynamic.ann.TenantDS;
import com.wemirr.framework.db.mybatis.SuperMapper;
import com.wemirr.platform.authority.domain.entity.baseinfo.RoleOrg;
import org.springframework.stereotype.Repository;

/**
 * @author Levin
 */
@TenantDS
@Repository
public interface RoleOrgMapper extends SuperMapper<RoleOrg> {

}
