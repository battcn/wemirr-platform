package com.wemirr.platform.workflow.mq.listener;


import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.workflow.domain.entity.InstanceExt;
import com.wemirr.platform.workflow.repository.InstanceExtMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.warm.flow.core.listener.Listener;
import org.dromara.warm.flow.core.listener.ListenerVariable;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * 实例完成的监听器 一般用于业务数据的更新处理
 *
 * @author battcn
 * @since 2025-09-18
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultFinishListener implements Listener {

    private final InstanceExtMapper instanceExtMapper;

    @Override
    public void notify(ListenerVariable listener) {
        log.info("流程完成监听器----");
        processInstanceFinishTime(listener);
        log.info("流程完成监听器结束----");
    }

    void processInstanceFinishTime(ListenerVariable listener) {
        var instance = listener.getInstance();
        if (instance == null) {
            return;
        }
        instanceExtMapper.update(Wraps.<InstanceExt>lbU().set(InstanceExt::getFinishTime, Instant.now())
                .eq(InstanceExt::getInstanceId, listener.getInstance().getId()));
    }
}
