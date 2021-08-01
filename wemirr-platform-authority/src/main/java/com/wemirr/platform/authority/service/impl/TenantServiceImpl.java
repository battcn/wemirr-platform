package com.wemirr.platform.authority.service.impl;

import com.wemirr.framework.boot.service.impl.SuperServiceImpl;
import com.wemirr.platform.authority.domain.entity.baseinfo.Tenant;
import com.wemirr.platform.authority.repository.TenantMapper;
import com.wemirr.platform.authority.service.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Levin
 */
@Service
@RequiredArgsConstructor
public class TenantServiceImpl extends SuperServiceImpl<TenantMapper, Tenant> implements TenantService {
}
