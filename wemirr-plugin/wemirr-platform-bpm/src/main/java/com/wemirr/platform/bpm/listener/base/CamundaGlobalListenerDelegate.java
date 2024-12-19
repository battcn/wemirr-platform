package com.wemirr.platform.bpm.listener.base;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.TaskListener;

/**
 * 全局监听代理
 *
 * @author Levin
 */
@Slf4j
@RequiredArgsConstructor
public class CamundaGlobalListenerDelegate implements ExecutionListener, TaskListener {

    private final String method;
    private final String type;
    private static final String EVENT_NAME = "end";
    private static final String METHOD_PARSE_PROCESS = "parseProcess";

    @Override
    public void notify(DelegateTask delegateTask) {
        log.info("delegate task - method - {} - type - {} - event name - {}", method, type, delegateTask.getEventName());
    }


    @Override
    public void notify(DelegateExecution execution) {
        if (StringUtils.equals(execution.getEventName(), EVENT_NAME) && StringUtils.equals(method, METHOD_PARSE_PROCESS)) {
            log.info("execution - {}", execution);
        }
        log.info("execution method - {} - type - {} - event name - {}", method, type, execution.getEventName());
    }
}
