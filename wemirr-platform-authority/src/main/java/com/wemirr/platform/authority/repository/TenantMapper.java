package com.wemirr.platform.authority.repository;

import com.wemirr.framework.boot.SuperMapper;
import com.wemirr.platform.authority.domain.entity.baseinfo.Tenant;
import org.springframework.stereotype.Repository;

/**
 * @author Levin
 */
@Repository
public interface TenantMapper extends SuperMapper<Tenant> {
}
