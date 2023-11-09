package com.wemirr.framework.db.dynamic;

import cn.hutool.core.collection.CollUtil;
import com.wemirr.framework.db.dynamic.core.EventAction;
import com.wemirr.framework.db.dynamic.core.TenantDynamicDatasource;
import com.wemirr.framework.db.dynamic.feign.TenantFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author levin
 */
@Slf4j
@RequiredArgsConstructor
public class TenantDynamicDataSourceLoad {

    private final TenantDynamicDataSourceHandler tenantDynamicDataSourceHandler;
    private final TenantFeignClient tenantFeignClient;

    public void init() {
        log.debug("extend.mybatis-plus.multi-tenant.strategy eq feign , pull dynamic begin...");
        final List<TenantDynamicDatasource> result = tenantFeignClient.selectAll();
        if (CollUtil.isEmpty(result)) {
            log.warn("feign pull tenantDynamicDataSources is null......");
            return;
        }
        result.forEach(tenantDynamicDataSource -> tenantDynamicDataSourceHandler.handler(EventAction.ADD, tenantDynamicDataSource));
        log.debug("extend.mybatis-plus.multi-tenant.strategy eq feign , pull dynamic end...");
    }


}
