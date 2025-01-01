package com.wemirr.platform.bpm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.platform.bpm.domain.enums.ApprovalStatus;
import com.wemirr.platform.bpm.domain.req.ProcessTaskApprovalReq;
import com.wemirr.platform.bpm.domain.req.ProcessTaskAssigneeReq;
import com.wemirr.platform.bpm.domain.req.ProcessTaskPageReq;
import com.wemirr.platform.bpm.domain.resp.ProcessTaskExtResp;
import com.wemirr.platform.bpm.domain.resp.ProcessTaskHistoryResp;
import com.wemirr.platform.bpm.service.ProcessTaskExtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 流程任务控制层
 *
 * @author Levin
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "流程任务", description = "流程任务管理")
@RequestMapping(value = "/process_tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProcessTaskController {

    private final ProcessTaskExtService processTaskExtService;

    @Operation(summary = "待办任务", description = "待办任务")
    @PostMapping(value = "/page")
    public Page<ProcessTaskExtResp> pageList(@RequestBody ProcessTaskPageReq req) {
        return processTaskExtService.pageList(req);
    }

    @Operation(summary = "历史任务", description = "历史任务")
    @PostMapping(value = "/history")
    public Page<ProcessTaskHistoryResp> hisPageList(@RequestBody ProcessTaskPageReq req) {
        return processTaskExtService.hisPageList(req);
    }

    @PutMapping(value = "/{taskId}/complete")
    @Operation(summary = "审批通过", description = "审批通过")
    public void complete(@PathVariable String taskId, @Validated @RequestBody ProcessTaskApprovalReq req) {
        processTaskExtService.approval(taskId, ApprovalStatus.APPROVED, req);
    }

    @PutMapping(value = "/{taskId}/comment")
    @Operation(summary = "添加评论", description = "添加评论")
    public void comment(@PathVariable String taskId, @Validated @RequestBody ProcessTaskApprovalReq req) {
        processTaskExtService.comment(taskId, req);
    }

    @AccessLog(module = "流程任务", description = "审批拒绝")
    @PutMapping(value = "/{taskId}/reject")
    @Operation(summary = "审批拒绝", description = "审批拒绝")
    public void cancel(@PathVariable String taskId, @RequestBody ProcessTaskApprovalReq req) {
        processTaskExtService.approval(taskId, ApprovalStatus.REJECT, req);
    }

    @AccessLog(module = "流程任务", description = "拾取任务")
    @PutMapping(value = "/{taskId}/claim")
    @Operation(summary = "拾取任务", description = "拾取任务")
    public void claim(@PathVariable String taskId) {
        processTaskExtService.claimTask(taskId);
    }

    @AccessLog(module = "流程任务", description = "委派任务")
    @PutMapping(value = "/{taskId}/delegate")
    @Operation(summary = "委派任务", description = "委派任务")
    public void delegate(@PathVariable String taskId,@Validated @RequestBody ProcessTaskAssigneeReq req) {
        processTaskExtService.delegate(taskId, req);
    }


    @AccessLog(module = "流程任务", description = "转办任务")
    @PutMapping(value = "/{taskId}/transfer")
    @Operation(summary = "转办任务", description = "转办任务")
    public void transfer(@PathVariable("taskId") String taskId, @RequestBody ProcessTaskAssigneeReq req) {
        processTaskExtService.transfer(taskId, req);
    }

}
