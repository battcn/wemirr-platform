package com.wemirr.platform.nepxion.controller;

import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Levin
 */
@Slf4j
@RequestMapping("/bpmn")
@RestController
@RequiredArgsConstructor
public class BpmnController {

    private final RepositoryService repositoryService;
    private final RuntimeService runtimeService;
    private final TaskService taskService;

    /**
     * 部署流程
     */
    @GetMapping("/deploy")
    public void deploy() {
        Deployment deploy = repositoryService.createDeployment()
                .name("请假申请").addClasspathResource("diagram_1.bpmn")
                .deploy();
        log.info("Name - {},ID - {}", deploy.getName(), deploy.getId());
    }

    /**
     * 开始一个流程实例
     */
    @GetMapping("/start")
    public String start(@RequestParam(required = false, defaultValue = "Process_007jh58") String proInsId) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(proInsId);
        log.info("processInstance Id - {} - {}", processInstance.getId(), processInstance.getBusinessKey());
        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstance.getId())
                .active()
                .singleResult();
        log.info("processInstance to task - {} - {}", task.getId(), task.getAssignee());
        return processInstance.getId();
    }

    /**
     * 完成一个任务
     */
    @GetMapping("/run")
    public void run(Integer ts) {
        Deployment deploy = repositoryService.createDeployment().name("请假申请").addClasspathResource("diagram_1.bpmn").deploy();
        log.info("Deploy Name - {},ID - {}", deploy.getName(), deploy.getId());
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Process_007jh58");
        log.info("processInstance Id - {} - {}", processInstance.getId(), processInstance.getBusinessKey());
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).active().singleResult();
        List<Task> list = taskService.createTaskQuery().taskAssignee("sqr").listPage(0, 1000);
        log.debug("list - {}", list);
        Map<String, Object> map = new HashMap<>();
        if (ts != null) {
            // 完一个任务，并且可以设置变量参数
            map.put("ts", ts);
            taskService.complete(task.getId(), map);
        } else {
            // 完成一个任务 不带参数
            taskService.complete(task.getId());
        }
    }


}
