package com.wemirr.framework.db.dynamic.core.local;

import com.wemirr.framework.db.dynamic.TenantDynamicDataSourceHandler;
import com.wemirr.framework.db.dynamic.core.EventAction;
import com.wemirr.framework.db.dynamic.core.TenantDynamicDatasource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;

import java.util.Objects;

/**
 * spring cloud bus 玩法好像有点问题
 *
 * @author levin
 */
@Slf4j
@RequiredArgsConstructor
@Deprecated
public class DynamicDatasourceEventListener implements ApplicationListener<DynamicDatasourceEvent> {

    private final TenantDynamicDataSourceHandler tenantDynamicDataSourceHandler;

    @Override
    @EventListener
    public void onApplicationEvent(DynamicDatasourceEvent event) {
        final TenantDynamicDatasource dynamicDatasource = event.getDynamicDatasource();
        if (Objects.isNull(event.getDynamicDatasource())) {
            log.warn("event dynamicDatasource is null....");
            return;
        }
        log.info("接收租户事件消息: - {} - {}", event.getAction(), dynamicDatasource);
        tenantDynamicDataSourceHandler.handler(EventAction.of(event.getAction()), dynamicDatasource);
    }
}