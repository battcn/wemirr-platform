package com.wemirr.platform.authority.service.impl;

import com.wemirr.framework.boot.service.impl.SuperServiceImpl;
import com.wemirr.platform.authority.domain.entity.baseinfo.Tenant;
import com.wemirr.platform.authority.domain.entity.common.AreaEntity;
import com.wemirr.platform.authority.repository.AreaMapper;
import com.wemirr.platform.authority.repository.TenantMapper;
import com.wemirr.platform.authority.service.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author Levin
 */
@Service
@RequiredArgsConstructor
public class TenantServiceImpl extends SuperServiceImpl<TenantMapper, Tenant> implements TenantService {

    private final AreaMapper areaMapper;


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


}
