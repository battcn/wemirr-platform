package com.wemirr.framework.database.configuration.dynamic;

import cn.hutool.core.collection.CollectionUtil;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.database.configuration.dynamic.event.body.EventAction;
import com.wemirr.framework.database.configuration.dynamic.event.body.TenantDynamicDatasource;
import com.wemirr.framework.database.configuration.dynamic.feign.TenantFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author levin
 */
@Slf4j
@ConditionalOnProperty(prefix = "extend.mybatis-plus.multi-tenant", name = "strategy", havingValue = "feign")
@AutoConfigureAfter(DynamicDatasourceEventBusAutoConfiguration.class)
public class DynamicDatasourceLoadAutoConfiguration {

    @Resource
    private DynamicDataSourceProcess dynamicDataSourceProcess;
    @Resource
    private TenantFeignClient tenantFeignClient;

    @PostConstruct
    public void init() {
        log.debug("extend.mybatis-plus.multi-tenant.strategy eq feign , pull dynamic begin...");
        final Result<List<TenantDynamicDatasource>> result = tenantFeignClient.selectAll();
        if (!result.isSuccessful() || CollectionUtil.isEmpty(result.getData())) {
            log.warn("feign pull tenantDynamicDataSources is null......");
            return;
        }
        result.getData().forEach(tenantDynamicDataSource -> dynamicDataSourceProcess.handler(EventAction.ADD, tenantDynamicDataSource));
        log.debug("extend.mybatis-plus.multi-tenant.strategy eq feign , pull dynamic end...");
    }


}
