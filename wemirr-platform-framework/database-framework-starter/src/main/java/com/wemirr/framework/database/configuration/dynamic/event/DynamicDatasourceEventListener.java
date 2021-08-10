package com.wemirr.framework.database.configuration.dynamic.event;

import com.wemirr.framework.database.configuration.dynamic.DynamicDataSourceProcess;
import com.wemirr.framework.database.configuration.dynamic.event.body.EventAction;
import com.wemirr.framework.database.configuration.dynamic.event.body.TenantDynamicDatasource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author levin
 */
@Slf4j
@Component
public class DynamicDatasourceEventListener implements ApplicationListener<DynamicDatasourceEvent> {

    @Resource
    private DynamicDataSourceProcess dynamicDataSourceProcess;

    @Override
    public void onApplicationEvent(DynamicDatasourceEvent event) {
        final TenantDynamicDatasource dynamicDatasource = event.getDynamicDatasource();
        if (Objects.isNull(event.getDynamicDatasource())) {
            log.warn("event dynamicDatasource is null....");
            return;
        }
        log.info("接收租户事件消息: - {} - {}", event.getAction(), dynamicDatasource);
        dynamicDataSourceProcess.handler(EventAction.of(event.getAction()), dynamicDatasource);
    }
}