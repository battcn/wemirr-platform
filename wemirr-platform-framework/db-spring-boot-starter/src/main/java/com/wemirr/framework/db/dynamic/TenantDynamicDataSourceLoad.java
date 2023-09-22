package com.wemirr.framework.db.dynamic;

import cn.hutool.core.collection.CollectionUtil;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.db.dynamic.event.body.EventAction;
import com.wemirr.framework.db.dynamic.event.body.TenantDynamicDatasource;
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

    private final TenantDynamicDataSourceProcess tenantDynamicDataSourceProcess;
    private final TenantFeignClient tenantFeignClient;

    public void init() {
        log.debug("extend.mybatis-plus.multi-tenant.strategy eq feign , pull dynamic begin...");
        final Result<List<TenantDynamicDatasource>> result = tenantFeignClient.selectAll();
        if (!result.isSuccessful() || CollectionUtil.isEmpty(result.getData())) {
            log.warn("feign pull tenantDynamicDataSources is null......");
            return;
        }
        result.getData().forEach(tenantDynamicDataSource -> tenantDynamicDataSourceProcess.handler(EventAction.ADD, tenantDynamicDataSource));
        log.debug("extend.mybatis-plus.multi-tenant.strategy eq feign , pull dynamic end...");
    }


}
