package com.wemirr.platform.authority.service;

import com.wemirr.framework.boot.service.SuperService;
import com.wemirr.platform.authority.domain.entity.tenant.Tenant;
import com.wemirr.platform.authority.domain.entity.tenant.TenantConfig;

/**
 * @author Levin
 */
public interface TenantService extends SuperService<Tenant> {


    /**
     * 保存租户
     *
     * @param tenant 租户信息
     */
    void saveOrUpdateTenant(Tenant tenant);

    void tenantConfig(TenantConfig tenantConfig);
}
