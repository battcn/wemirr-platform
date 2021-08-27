package com.wemirr.platform.bpm.camunda.tasks;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class SmsServiceTask implements JavaDelegate {

    private final TaskService taskService;

    public void execute(DelegateExecution delegateExecution) {
        Map<String, Object> variables = delegateExecution.getVariables();
        log.info("参数变量 {}", variables);

        String studentId = (String) variables.get("student");
        log.info("成功发送短信给学生 {}", studentId);
    }
}
