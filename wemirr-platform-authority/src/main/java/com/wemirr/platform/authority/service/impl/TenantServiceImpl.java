package com.wemirr.platform.authority.service.impl;

import com.wemirr.framework.boot.service.impl.SuperServiceImpl;
import com.wemirr.framework.commons.StringUtils;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.database.SpringUtils;
import com.wemirr.framework.database.configuration.dynamic.TenantDynamicDataSourceProcess;
import com.wemirr.framework.database.configuration.dynamic.event.body.EventAction;
import com.wemirr.framework.database.mybatis.conditions.Wraps;
import com.wemirr.framework.database.properties.DatabaseProperties;
import com.wemirr.framework.database.properties.MultiTenantType;
import com.wemirr.platform.authority.domain.entity.common.AreaEntity;
import com.wemirr.platform.authority.domain.entity.tenant.Tenant;
import com.wemirr.platform.authority.domain.entity.tenant.TenantConfig;
import com.wemirr.platform.authority.repository.AreaMapper;
import com.wemirr.platform.authority.repository.TenantConfigMapper;
import com.wemirr.platform.authority.repository.TenantMapper;
import com.wemirr.platform.authority.service.DynamicDatasourceService;
import com.wemirr.platform.authority.service.TenantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TenantServiceImpl extends SuperServiceImpl<TenantMapper, Tenant> implements TenantService {

    private final AreaMapper areaMapper;
    private final TenantConfigMapper tenantConfigMapper;
    private final DynamicDatasourceService dynamicDatasourceService;
    private final DatabaseProperties properties;

    @Override
    public void saveOrUpdateTenant(Tenant tenant) {
        tenant.setProvinceName(getNameById(tenant.getProvinceId()));
        tenant.setCityName(getNameById(tenant.getCityId()));
        tenant.setDistrictName(getNameById(tenant.getDistrictId()));
        if (tenant.getId() != null) {
            baseMapper.updateById(tenant);
            return;
        }
        baseMapper.insert(tenant);
    }

    private String getNameById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        final AreaEntity areaEntity = areaMapper.selectById(id);
        if (Objects.isNull(areaEntity)) {
            return null;
        }
        return areaEntity.getName();
    }

    @Override
    public void tenantConfig(TenantConfig tenantConfig) {
        final Tenant tenant = Optional.ofNullable(this.baseMapper.selectById(tenantConfig.getTenantId()))
                .orElseThrow(() -> CheckedException.notFound("租户不存在"));
        if (tenant.getLocked()) {
            throw CheckedException.badRequest("租户已被禁用");
        }
        if (StringUtils.equals(tenant.getCode(), properties.getMultiTenant().getSuperTenantCode())) {
            throw CheckedException.badRequest("超级租户,禁止操作");
        }
        if (tenantConfig.getId() == null) {
            tenantConfigMapper.delete(Wraps.<TenantConfig>lbQ().eq(TenantConfig::getTenantId, tenantConfig.getTenantId()));
            tenantConfigMapper.insert(tenantConfig);
        } else {
            tenantConfigMapper.updateById(tenantConfig);
        }
        // 先创建
        dynamicDatasourceService.publishEvent(EventAction.INIT, tenantConfig.getTenantId());
    }

    @Override
    public void initSqlScript(Long id) {
        final Tenant tenant = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("租户信息不存在"));
        if (tenant.getLocked()) {
            throw CheckedException.badRequest("租户已被禁用");
        }
        final DatabaseProperties.MultiTenant multiTenant = properties.getMultiTenant();
        if (StringUtils.equals(tenant.getCode(), multiTenant.getSuperTenantCode())) {
            throw CheckedException.badRequest("超级租户,禁止操作");
        }
        if (multiTenant.getType() == MultiTenantType.DATASOURCE) {
            TenantDynamicDataSourceProcess tenantDynamicDataSourceProcess = SpringUtils.getBean(TenantDynamicDataSourceProcess.class);
            tenantDynamicDataSourceProcess.initSqlScript(tenant.getCode());
        }
    }
}
