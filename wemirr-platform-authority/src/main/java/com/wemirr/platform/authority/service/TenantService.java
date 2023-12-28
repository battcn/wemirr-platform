package com.wemirr.platform.authority.service;

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.authority.domain.tenant.entity.Tenant;
import com.wemirr.platform.authority.domain.tenant.req.TenantConfigReq;

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

    /**
     * 租户配置
     *
     * @param tenantId tenantId
     * @param req      租户配置
     */
    void tenantConfig(Long tenantId, TenantConfigReq req);

    /**
     * 初始化SQL脚本
     *
     * @param id id
     */
    void initSqlScript(Long id);
}
