package com.wemirr.platform.workflow.mq.listener;


import lombok.extern.slf4j.Slf4j;
import org.dromara.warm.flow.core.listener.Listener;
import org.dromara.warm.flow.core.listener.ListenerVariable;
import org.springframework.stereotype.Component;

/**
 * 实例完成的监听器 一般用于业务数据的更新处理
 *
 * @author battcn
 * @since 2025-09-18
 **/
@Slf4j
@Component
public class DefaultFinishListener implements Listener {

    @Override
    public void notify(ListenerVariable listenerVariable) {
        log.info("流程完成监听器----");

        log.info("流程完成监听器结束----");
    }
}
