package com.wemirr.framework.db.dynamic.event;

import com.wemirr.framework.db.dynamic.event.body.TenantDynamicDatasource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.bus.event.Destination;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;


/**
 * @author Levin
 */
public class DynamicDatasourceEvent extends RemoteApplicationEvent {

    @Getter
    @Setter
    private TenantDynamicDatasource dynamicDatasource;

    @Getter
    @Setter
    private Integer action;

    public DynamicDatasourceEvent() {
    }

    public DynamicDatasourceEvent(Object body, String originService, Destination destinationService, TenantDynamicDatasource dynamicDatasource) {
        super(body, originService, destinationService);
        this.dynamicDatasource = dynamicDatasource;

    }

    public DynamicDatasourceEvent(Object body, String originService, TenantDynamicDatasource dynamicDatasource, Integer action) {
        super(body, originService, () -> originService);
        this.dynamicDatasource = dynamicDatasource;
        this.action = action;
    }

}
