package com.wemirr.platform.bpm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.bpm.domain.entity.ProcessDeployHistory;
import com.wemirr.platform.bpm.domain.entity.ProcessInstanceExt;
import com.wemirr.platform.bpm.domain.entity.ProcessTaskComment;
import com.wemirr.platform.bpm.domain.enums.ProcInstStatus;
import com.wemirr.platform.bpm.domain.req.ProcessInstancePageReq;
import com.wemirr.platform.bpm.domain.resp.*;
import com.wemirr.platform.bpm.repository.ProcessDeployHistoryMapper;
import com.wemirr.platform.bpm.repository.ProcessInstanceExtMapper;
import com.wemirr.platform.bpm.repository.ProcessTaskCommentMapper;
import com.wemirr.platform.bpm.repository.ProcessTaskHistoryMapper;
import com.wemirr.platform.bpm.service.ProcessInstanceService;
import com.wemirr.platform.bpm.service.ProcessModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 流程实例业务层实现
 *
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessInstanceServiceImpl implements ProcessInstanceService {

    private final RuntimeService runtimeService;
    private final RepositoryService repositoryService;
    private final TaskService taskService;
    private final HistoryService historyService;
    private final ProcessDeployHistoryMapper processDeployHistoryMapper;
    private final ProcessInstanceExtMapper processInstanceExtMapper;
    private final ProcessModelService processModelService;
    private final ProcessTaskCommentMapper processTaskCommentMapper;
    private final ProcessTaskHistoryMapper processTaskHistoryMapper;

    @Override
    public IPage<ProcessInstancePageResp> pageList(ProcessInstancePageReq req) {
        //获取流程定义扩展表信息
        IPage<ProcessInstancePageResp> page = processInstanceExtMapper.selectPage(req.buildPage(), Wraps.<ProcessInstanceExt>lbQ()
                        .eq(ProcessInstanceExt::getProcInstCategoryId, req.getProcInstCategoryId())
                        .like(ProcessInstanceExt::getProcDefName, req.getProcDefName())
                        .like(ProcessInstanceExt::getProcDefKey, req.getProcDefKey())
                        .like(ProcessInstanceExt::getProcInstName, req.getProcInstName())
                        .eq(ProcessInstanceExt::getProcInstActivate, req.getProcInstActivate()).orderByDesc(ProcessInstanceExt::getId))
                .convert(x -> BeanUtil.toBean(x, ProcessInstancePageResp.class));
        List<ProcessInstancePageResp> records = page.getRecords();
        if (CollectionUtil.isEmpty(records)) {
            return new Page<>();
        }
        //获取流程任务并按流程实例ID分组
        List<Task> taskList = taskService.createTaskQuery().processInstanceIdIn(records.stream().map(ProcessInstancePageResp::getProcInstId).toArray(String[]::new)).list();
        List<ProcessTaskResp> processTaskRespList = taskList.stream().map(task ->
                ProcessTaskResp.builder().id(task.getId()).procInstId(task.getProcessInstanceId()).taskName(task.getName())
                        .createdName(task.getAssignee()).createdTime(task.getCreateTime()).build()).collect(Collectors.toList());
        final Map<String, List<ProcessTaskResp>> taskByProcInstId = processTaskRespList.parallelStream().collect(Collectors.groupingBy(ProcessTaskResp::getProcInstId));
        //组装数据
        List<ProcessInstancePageResp> list = records.stream().peek(record -> {
            List<ProcessTaskResp> tasks = taskByProcInstId.get(record.getProcInstId());
            record.setTasks(tasks);
        }).toList();
        page.setRecords(list);
        return page;
    }


    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void suspendOrResumeInstance(Long instanceId, Boolean activate) {
        final ProcessInstanceExt instance = this.processInstanceExtMapper.selectById(instanceId);
        processInstanceExtMapper.updateById(ProcessInstanceExt.builder().id(instanceId).procInstActivate(activate).build());
        if (activate) {
            runtimeService.activateProcessInstanceById(instance.getProcInstId());
        } else {
            runtimeService.suspendProcessInstanceById(instance.getProcInstId());
        }
    }

    @Override
    public ProcessInstanceDetailResp detail(String id) {
        var processInstanceExt = Optional.ofNullable(processInstanceExtMapper.selectOne(ProcessInstanceExt::getProcInstId, id)).orElseThrow(() -> CheckedException.badRequest("流程信息不存在"));
        //获取历史任务信息
        var historicActivityInstances = historyService.createHistoricActivityInstanceQuery().processInstanceId(id).orderByHistoricActivityInstanceStartTime().asc().list();
        BpmnModelInstance instance = repositoryService.getBpmnModelInstance(processInstanceExt.getProcDefId());
        Collection<SequenceFlow> sequenceFlows = instance.getModelElementsByType(SequenceFlow.class);
        // 使用 Stream API 优化遍历
        var nodeList = historicActivityInstances.stream()
                .flatMap(activityInstance -> sequenceFlows.stream()
                        .filter(flow -> StrUtil.equals(flow.getTarget().getId(), activityInstance.getActivityId()))
                        .flatMap(flow -> {
                            FlowNode sourceNode = flow.getSource();
                            FlowNode targetNode = flow.getTarget();
                            // 创建节点信息对象
                            var line = ProcessInstanceDetailResp.ProcessInstanceActivityNode.builder().id(flow.getId()).type("line").build();
                            var source = ProcessInstanceDetailResp.ProcessInstanceActivityNode.builder().id(sourceNode.getId()).type("node").build();
                            var target = ProcessInstanceDetailResp.ProcessInstanceActivityNode.builder().id(targetNode.getId()).type("node").build();
                            return Stream.of(line, source, target);
                        })
                )
                .collect(Collectors.toList());
        return ProcessInstanceDetailResp.builder().diagramData(processInstanceExt.getDiagramData()).diagramName(processInstanceExt.getDiagramName())
                .processDefinitionId(processInstanceExt.getProcDefId()).processDefinitionKey(processInstanceExt.getProcDefKey())
                .processInstanceId(processInstanceExt.getProcInstId()).processInstanceName(processInstanceExt.getProcInstName()).version(processInstanceExt.getProcInstVersion())
                .processStartTime(processInstanceExt.getProcInstStartTime())
                .processEndTime(processInstanceExt.getProcInstEndTime())
                .nodeList(nodeList).build();
    }

    @Override
    public RenderFormResp renderForm(String id) {
        //通过获取流程实例ID获取模型ID
        HistoricProcessInstance processInstance = Optional.ofNullable(historyService.createHistoricProcessInstanceQuery().processInstanceId(id).singleResult()).orElseThrow(() -> new CheckedException("不存在的流程实例"));
        //从历史表获取modelID
        ProcessDeployHistory deployHistory = processDeployHistoryMapper.selectOne(Wraps.<ProcessDeployHistory>lbQ().eq(ProcessDeployHistory::getProcessDefinitionId, processInstance.getProcessDefinitionId()));
        //通过modelId获取表单信息
        DesignModelFormResp formDesign = Optional.ofNullable(processModelService.findFormDesign(deployHistory.getModelId())).orElseGet(DesignModelFormResp::new);
        final ProcessInstanceExt ext = processInstanceExtMapper.selectOne(ProcessInstanceExt::getProcInstId, id);
        //通过实例ID获取表单数据
        return RenderFormResp.builder().formDesign(formDesign).dataJson(JSONObject.parse(ext.getFormData())).build();

    }

    @Override
    public List<ProcessTaskCommentResp> approvalInfo(String procInstId) {
        List<ProcessTaskComment> comments = this.processTaskCommentMapper.selectList(ProcessTaskComment::getProcInstId, procInstId);
        return comments.stream().map(x -> ProcessTaskCommentResp.builder().taskId(x.getProcTaskId())
                .remark(x.getRemark()).attachment(x.getAttachment())
                .approverTime(x.getCreatedTime()).approverName(x.getCreatedName())
                .build()).collect(Collectors.toList());
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void cancel(String instanceId) {
        final ProcessInstanceExt instance = Optional.ofNullable(this.processInstanceExtMapper.selectOne(ProcessInstanceExt::getProcInstId, instanceId))
                .orElseThrow(() -> CheckedException.badRequest("bpm.process-instance.config-not-exists"));
        if (!instance.getProcInstActivate()) {
            throw CheckedException.badRequest("无法作废挂起的流程");
        }
        this.processInstanceExtMapper.updateById(ProcessInstanceExt.builder().id(instance.getId()).procInstStatus(ProcInstStatus.CANCEL).build());
        try {
            runtimeService.createProcessInstanceModification(instance.getProcInstId()).cancelActivityInstance(instance.getProcInstId()).execute();
        } catch (ProcessEngineException e) {
            log.error("流程不存在或已作废", e);
            throw CheckedException.badRequest("流程不存在或已作废({0})", e.getLocalizedMessage());
        }
    }
}
