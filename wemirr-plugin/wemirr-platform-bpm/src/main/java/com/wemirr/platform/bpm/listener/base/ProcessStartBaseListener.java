package com.wemirr.platform.bpm.listener.base;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson2.JSONObject;
import com.wemirr.platform.bpm.domain.entity.ProcessDeployHistory;
import com.wemirr.platform.bpm.domain.entity.ProcessInstanceExt;
import com.wemirr.platform.bpm.domain.entity.ProcessModelForm;
import com.wemirr.platform.bpm.domain.enums.ProcInstStatus;
import com.wemirr.platform.bpm.repository.ProcessDeployHistoryMapper;
import com.wemirr.platform.bpm.repository.ProcessInstanceExtMapper;
import com.wemirr.platform.bpm.repository.ProcessModelFormMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

/**
 * 流程启动监听器
 * 处理启动流程实例后 针对流程实例扩展表的一些操作
 *
 * @author Levin
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ProcessStartBaseListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution execution) {
        long startTime = System.currentTimeMillis();
        // 获取启动的流程实例信息加载到扩展表中
        var processInstanceExtMapper = SpringUtil.getBean(ProcessInstanceExtMapper.class);
        var deployHistoryMapper = SpringUtil.getBean(ProcessDeployHistoryMapper.class);
        //根据流程XML中的节点信息 获取流程标题
        var processDefinition = execution.getProcessEngine().getRepositoryService()
                .createProcessDefinitionQuery().processDefinitionId(execution.getProcessDefinitionId()).singleResult();
        String deploymentId = processDefinition.getDeploymentId();
        //存储扩展表
        final JSONObject variables = JSONObject.from(execution.getVariables());
        var deployHistory = Optional.ofNullable(deployHistoryMapper.selectOne(ProcessDeployHistory::getDeploymentId, deploymentId))
                .orElseGet(ProcessDeployHistory::new);
        var processModelFormMapper = SpringUtil.getBean(ProcessModelFormMapper.class);
        var form = Optional.ofNullable(processModelFormMapper.selectOne(ProcessModelForm::getModelId, deployHistory.getModelId()))
                .orElseGet(ProcessModelForm::new);
        processInstanceExtMapper.insert(ProcessInstanceExt.builder()
                .diagramName(deployHistory.getDiagramNames())
                .diagramData(deployHistory.getDiagramData())
                .procDefId(execution.getProcessDefinitionId())
                .procDefKey(processDefinition.getKey())
                .procDefName(processDefinition.getName())
                .procInstId(execution.getProcessInstanceId())
                .procInstName(variables.getString("ext.processInstName"))
                .businessKey(variables.getString("ext.businessKey"))
                .businessGroup(variables.getString("ext.businessGroup"))
                .procInstVersion(deployHistory.getVersion())
                .procInstCategoryId(deployHistory.getProcessCategoryId())
                .procInstCategoryCode(deployHistory.getProcessCategoryCode())
                .procInstCategoryName(deployHistory.getProcessCategoryName())
                .procInstStatus(ProcInstStatus.IN_PROGRESS)
                .procInstActivate(true)
                .procInstStartTime(Instant.now())
                .formSchemas(form.getFormSchemas())
                .formScript(form.getFormScript())
                .formData(variables.getString("ext.formData"))
                .build());
        log.debug("listener 耗时 => {}", (System.currentTimeMillis() - startTime));
    }
}
