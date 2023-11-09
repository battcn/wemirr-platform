package com.wemirr.framework.db.dynamic.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Levin
 */
public interface DynamicDatasourceEventPublish {

    Logger log = LoggerFactory.getLogger(DynamicDatasourceEventPublish.class);

    String DEFAULT_EVENT_TOPIC = "dynamic-db-topic";

    /**
     * 发布数据源
     *
     * @param message 数据源
     */
    void publish(TenantDynamicDatasource message);


}
