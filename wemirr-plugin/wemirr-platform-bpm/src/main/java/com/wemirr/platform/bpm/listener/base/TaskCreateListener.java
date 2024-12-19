package com.wemirr.platform.bpm.listener.base;


import cn.hutool.extra.spring.SpringUtil;
import com.wemirr.platform.bpm.domain.entity.ProcessInstanceExt;
import com.wemirr.platform.bpm.domain.entity.ProcessModel;
import com.wemirr.platform.bpm.domain.entity.ProcessTaskExt;
import com.wemirr.platform.bpm.repository.ProcessInstanceExtMapper;
import com.wemirr.platform.bpm.repository.ProcessModelMapper;
import com.wemirr.platform.bpm.repository.ProcessTaskExtMapper;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.model.bpmn.instance.MultiInstanceLoopCharacteristics;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * 任务创建监听器
 * 处理一些创建任务时的扩展业务
 *
 * @author Levin
 */
@Slf4j
@Component
public class TaskCreateListener implements TaskListener {

    @Override
    public void notify(DelegateTask task) {
        Map<String, Object> variables = task.getVariables();
        MultiInstanceLoopCharacteristics multiInstance = (MultiInstanceLoopCharacteristics) task.getBpmnModelElementInstance().getLoopCharacteristics();
        if (multiInstance != null) {
            boolean sequential = multiInstance.isSequential();
            log.info("sequential - {}", sequential);
        }
        String assignee = task.getAssignee();
        Object currentAssignee = task.getVariable("assignee");
        log.info("task - {} - {} - {}", task.getTaskDefinitionKey(), assignee, variables);
        //将任务信息插入任务运行扩展表中
        ProcessTaskExtMapper processTaskExtMapper = SpringUtil.getBean(ProcessTaskExtMapper.class);
        ProcessInstanceExtMapper processInstanceExtMapper = SpringUtil.getBean(ProcessInstanceExtMapper.class);
        ProcessModelMapper processModelMapper = SpringUtil.getBean(ProcessModelMapper.class);
        ProcessInstanceExt instanceExt = Optional.ofNullable(processInstanceExtMapper.selectOne(ProcessInstanceExt::getProcInstId, task.getProcessInstanceId())).orElseGet(ProcessInstanceExt::new);
        ProcessModel processModel = processModelMapper.selectOne(ProcessModel::getDefinitionKey, instanceExt.getProcDefKey());
        processTaskExtMapper.insert(ProcessTaskExt.builder()
                .procTaskId(task.getId())
                .procTaskName(task.getName())
                .procTaskDefKey(task.getTaskDefinitionKey())
                // 如果节点存在当前审批人,那么任务审核人就是当前审核人
                .assignee(currentAssignee != null ? currentAssignee + "" : task.getAssignee())
                .initiatorName(task.getAssignee())
                .createdName(instanceExt.getCreatedName())
                .procInstName(instanceExt.getProcInstName())
                .procInstId(task.getProcessInstanceId())
                .procDefId(task.getProcessDefinitionId())
                .procDefKey(processModel.getDefinitionKey())
                .procDefName(processModel.getDiagramName())
                .initiatorId(instanceExt.getCreatedBy())
                .initiatorName(instanceExt.getCreatedName())
                .procCategoryId(processModel.getCategoryId())
                .procCategoryName(processModel.getCategoryName())
                .build());
    }
}
