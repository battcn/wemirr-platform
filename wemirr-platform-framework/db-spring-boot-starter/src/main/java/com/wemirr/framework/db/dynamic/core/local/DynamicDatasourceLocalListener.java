package com.wemirr.framework.db.dynamic.core.local;

import com.wemirr.framework.db.dynamic.TenantDynamicDataSourceHandler;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;

/**
 * 本地监听
 *
 * @author levin
 */
@Slf4j
@RequiredArgsConstructor
public class DynamicDatasourceLocalListener implements ApplicationListener<DynamicDatasourceEvent> {

    private final TenantDynamicDataSourceHandler tenantDynamicDataSourceHandler;

    @Override
    @EventListener
    public void onApplicationEvent(@Nonnull DynamicDatasourceEvent event) {
        tenantDynamicDataSourceHandler.handler(event.getAction(), event.getDatasource());
    }
}