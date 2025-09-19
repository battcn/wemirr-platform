package com.wemirr.platform.workflow.mq.listener;

import lombok.extern.slf4j.Slf4j;
import org.dromara.warm.flow.core.listener.Listener;
import org.dromara.warm.flow.core.listener.ListenerVariable;
import org.springframework.stereotype.Component;

/**
 * 任务分发监听器
 *
 * @author battcn
 * @since 2025-09-18
 **/
@Slf4j
@Component
public class DefaultAssignmentListener implements Listener {
    @Override
    public void notify(ListenerVariable listenerVariable) {
        log.info("任务分发监听器----");

        log.info("任务分发监听器结束----");
    }
}
