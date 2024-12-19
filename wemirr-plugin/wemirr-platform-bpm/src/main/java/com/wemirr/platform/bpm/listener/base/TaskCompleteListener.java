package com.wemirr.platform.bpm.listener.base;


import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.bpm.domain.entity.ProcessTaskExt;
import com.wemirr.platform.bpm.domain.entity.ProcessTaskHistory;
import com.wemirr.platform.bpm.domain.enums.ProcTaskStatus;
import com.wemirr.platform.bpm.repository.ProcessTaskExtMapper;
import com.wemirr.platform.bpm.repository.ProcessTaskHistoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 任务审核通过
 *
 * @author Levin
 */
@Slf4j
@Component
public class TaskCompleteListener implements TaskListener {

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void notify(DelegateTask task) {
        Map<String, Object> map = task.getVariables();
        Integer loopCounter = (Integer) task.getVariable("loopCounter");
        Integer totalCount = (Integer) task.getVariable("nrOfInstances");
        log.info("loopCounter - {} , totalCount - {}", loopCounter, totalCount);
        var taskService = SpringUtil.getBean(TaskService.class);
        var taskExtMapper = SpringUtil.getBean(ProcessTaskExtMapper.class);
        var variables = taskService.getVariablesLocal(task.getId());
        log.debug("审批内容 - {}", variables);
        String remark = (String) variables.get("remark");
        var tenantId = Long.parseLong(task.getTenantId());
        var procInstId = task.getProcessInstanceId();
        ProcTaskStatus taskStatus = ProcTaskStatus.of(task.getEventName());
        ProcessTaskHistoryMapper taskHistoryMapper = SpringUtil.getBean(ProcessTaskHistoryMapper.class);
        ProcessTaskHistory history = ProcessTaskHistory.builder().tenantId(tenantId)
                .procTaskId(task.getId()).procTaskName(task.getName()).procTaskStatus(taskStatus)
                .procTaskDefKey(task.getTaskDefinitionKey())
                .procInstId(procInstId)
                .activityId(task.getTaskDefinitionKey())
                .remark(remark)
                .description("审批通过")
                .build();
        taskHistoryMapper.insert(history);
        //删除任务运行扩展表中的数据
        taskExtMapper.delete(Wraps.<ProcessTaskExt>lbQ().eq(ProcessTaskExt::getProcTaskId, task.getId()));
    }
}
