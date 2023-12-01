package com.wemirr.framework.db.dynamic.core.local;

import com.wemirr.framework.db.dynamic.core.EventAction;
import com.wemirr.framework.db.dynamic.core.TenantDynamicDatasource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;


/**
 * @author Levin
 */
@Getter
@Setter
public class DynamicDatasourceEvent extends ApplicationEvent {

    private EventAction action;
    private TenantDynamicDatasource datasource;

    public DynamicDatasourceEvent(EventAction action, TenantDynamicDatasource datasource) {
        super(datasource);
        this.datasource = datasource;
        this.action = action;
    }
}
