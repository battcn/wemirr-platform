package com.wemirr.framework.db.dynamic.event;

import com.wemirr.framework.db.dynamic.TenantDynamicDataSourceProcess;
import com.wemirr.framework.db.dynamic.event.body.EventAction;
import com.wemirr.framework.db.dynamic.event.body.TenantDynamicDatasource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;

import java.util.Objects;

/**
 * @author levin
 */
@Slf4j
@RequiredArgsConstructor
public class DynamicDatasourceEventListener implements ApplicationListener<DynamicDatasourceEvent> {

    private final TenantDynamicDataSourceProcess tenantDynamicDataSourceProcess;

    @Override
    public void onApplicationEvent(DynamicDatasourceEvent event) {
        final TenantDynamicDatasource dynamicDatasource = event.getDynamicDatasource();
        if (Objects.isNull(event.getDynamicDatasource())) {
            log.warn("event dynamicDatasource is null....");
            return;
        }
        log.info("接收租户事件消息: - {} - {}", event.getAction(), dynamicDatasource);
        tenantDynamicDataSourceProcess.handler(EventAction.of(event.getAction()), dynamicDatasource);
    }
}