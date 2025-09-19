package com.wemirr.platform.workflow.mq.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.warm.flow.core.listener.Listener;
import org.dromara.warm.flow.core.listener.ListenerVariable;
import org.springframework.stereotype.Component;

/**
 * 流程启动 监听器 一般用来设置一些权限之类的
 *
 * @author battcn
 * @since 2025-09-18
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultStartListener implements Listener {

    @Override
    public void notify(ListenerVariable listenerVariable) {
        log.info("流程任务创建监听器------");

        log.info("流程启动监听器处理结束------");
    }
}
