package com.wemirr.platform.authority.repository;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.wemirr.framework.boot.SuperMapper;
import com.wemirr.platform.authority.domain.entity.baseinfo.RoleOrg;
import org.springframework.stereotype.Repository;

/**
 * @author Levin
 */
@DS("#header.tenant_code")
@Repository
public interface RoleOrgMapper extends SuperMapper<RoleOrg> {

}
