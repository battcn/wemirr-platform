package com.wemirr.platform.bpm.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.platform.bpm.domain.entity.ProcessTaskComment;
import com.wemirr.platform.bpm.domain.entity.ProcessTaskExt;
import com.wemirr.platform.bpm.domain.enums.ApprovalStatus;
import com.wemirr.platform.bpm.domain.enums.TaskCommentType;
import com.wemirr.platform.bpm.domain.req.ProcessTaskApprovalReq;
import com.wemirr.platform.bpm.domain.req.ProcessTaskAssigneeReq;
import com.wemirr.platform.bpm.domain.req.ProcessTaskPageReq;
import com.wemirr.platform.bpm.domain.resp.ProcessTaskExtResp;
import com.wemirr.platform.bpm.domain.resp.ProcessTaskHistoryResp;
import com.wemirr.platform.bpm.repository.ProcessTaskCommentMapper;
import com.wemirr.platform.bpm.repository.ProcessTaskExtMapper;
import com.wemirr.platform.bpm.repository.ProcessTaskHistoryMapper;
import com.wemirr.platform.bpm.service.ProcessInstanceService;
import com.wemirr.platform.bpm.service.ProcessTaskExtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 流程任务业务实现层
 *
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessTaskExtServiceImpl implements ProcessTaskExtService {

    private final TaskService taskService;
    private final ProcessTaskExtMapper processTaskExtMapper;
    private final ProcessTaskHistoryMapper processTaskHistoryMapper;
    private final ProcessInstanceService processInstanceService;
    private final AuthenticationContext context;
    private final ProcessTaskCommentMapper processTaskCommentMapper;

    @Override
    public Page<ProcessTaskExtResp> pageList(ProcessTaskPageReq req) {
        req.setApproverId(context.userId());
        // 用于测试任务准确度
        // List<Task> tasks = taskService.createTaskQuery().active().list();
        return processTaskExtMapper.pageList(req.buildPage(), req);
    }


    @Override
    public Page<ProcessTaskHistoryResp> hisPageList(ProcessTaskPageReq req) {
        if (req.getApproverId() == null) {
            req.setApproverId(context.userId());
        }
        return processTaskHistoryMapper.pageList(req.buildPage(), req);
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void approval(String taskId, ApprovalStatus status, ProcessTaskApprovalReq req) {
        Task task = Optional.ofNullable(taskService.createTaskQuery().taskId(taskId).singleResult()).orElseThrow(() -> CheckedException.notFound("bpm.task.not-exists-or-completed"));
        taskService.setVariablesLocal(taskId, JSONObject.from(req));
        if (status == ApprovalStatus.APPROVED) {
            taskService.complete(taskId);
        }
        if (status == ApprovalStatus.REJECT) {
            processInstanceService.cancel(task.getProcessInstanceId());
        }
        this.processTaskCommentMapper.insert(ProcessTaskComment.builder()
                .type(TaskCommentType.APPROVAL)
                .procTaskId(task.getId())
                .procInstId(task.getProcessInstanceId())
                .remark(req.getRemark())
                .taskDefinitionKey(task.getTaskDefinitionKey())
                .attachment(StrUtil.join(",", req.getAttachments()))
                .build());
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void comment(String taskId, ProcessTaskApprovalReq req) {
        Task task = Optional.ofNullable(taskService.createTaskQuery().taskId(taskId).singleResult()).orElseThrow(() -> CheckedException.notFound("bpm.task.not-exists-or-completed"));
        // 理论上自己扩展表记录了就不需要再写到 camunda 中浪费存储空间了
        // Activity_1qeyyv2
        task.getTaskDefinitionKey();
        this.processTaskCommentMapper.insert(ProcessTaskComment.builder()
                .type(TaskCommentType.COMMENT)
                .procTaskId(task.getId())
                .procInstId(task.getProcessInstanceId())
                .remark(req.getRemark())
                .taskDefinitionKey(task.getTaskDefinitionKey())
                .build());
    }

    @Override
    public void claimTask(String taskId) {
        taskService.claim(taskId, String.valueOf(context.userId()));
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void transfer(String taskId, ProcessTaskAssigneeReq req) {
        ProcessTaskExt ext = this.processTaskExtMapper.selectOne(ProcessTaskExt::getProcTaskId, taskId, ProcessTaskExt::getAssignee, context.userId());
        if (ext == null) {
            log.warn("任务扩展信息为空 - 任务ID - {}", taskId);
            return;
        }
        // 先调用 BPM 自身的转办API
        this.taskService.setAssignee(taskId, req.getUserId());
        // 修改审核人信息
        this.processTaskExtMapper.updateById(ProcessTaskExt.builder().id(ext.getId()).assignee(req.getUserId()).build());
        log.debug("任务转办成功......");
        String remark = StrUtil.blankToDefault(req.getRemark(), "任务转办成功");
        this.processTaskCommentMapper.insert(ProcessTaskComment.builder()
                .type(TaskCommentType.TRANSFER)
                .procTaskId(ext.getProcTaskId())
                .procInstId(ext.getProcInstId())
                .remark(remark)
                .attachment("todo 暂时没考虑好咋存储")
                .build());
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void delegate(String taskId, ProcessTaskAssigneeReq req) {
        ProcessTaskExt ext = this.processTaskExtMapper.selectOne(ProcessTaskExt::getProcTaskId, taskId, ProcessTaskExt::getAssignee, context.userId());
        if (ext == null) {
            log.warn("任务扩展信息为空 - 任务ID - {}", taskId);
            return;
        }
        this.taskService.delegateTask(taskId, req.getUserId());
//        this.processTaskExtMapper.updateById(ProcessTaskExt.builder().id(ext.getId()).assignee(req.getUserId()).build());
        log.debug("任务委派成功......");
        this.processTaskCommentMapper.insert(ProcessTaskComment.builder()
                .type(TaskCommentType.TRANSFER)
                .procTaskId(ext.getProcTaskId())
                .procInstId(ext.getProcInstId())
                .remark(req.getRemark())
                .attachment("todo 暂时没考虑好咋存储")
                .build());
    }


}
