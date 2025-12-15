package com.wemirr.platform.workflow.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.redis.plus.anontation.RedisLock;
import com.wemirr.framework.redis.plus.anontation.RedisParam;
import com.wemirr.platform.workflow.domain.dto.req.TaskPageReq;
import com.wemirr.platform.workflow.domain.dto.req.WorkflowTaskReq;
import com.wemirr.platform.workflow.domain.dto.resp.DoneTaskPageResp;
import com.wemirr.platform.workflow.domain.dto.resp.TodoTaskPageResp;
import com.wemirr.platform.workflow.service.TaskExtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 流程任务控制器
 * <p>
 * 管理流程任务的审批、驳回、转办、加签等操作
 *
 * @author Levin
 * @since 2025-05
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/workflow/tasks")
@Tag(name = "流程任务", description = "流程任务管理")
public class FlowTaskController {

    private final TaskExtService taskExtService;

    /**
     * 所有待办任务
     */
    @GetMapping("/todo")
    @Operation(summary = "待办任务", description = "查询所有待办任务")
    public IPage<TodoTaskPageResp> todo(TaskPageReq req) {
        return taskExtService.todoPageList(req);
    }

    /**
     * 我的待办任务
     */
    @GetMapping("/todo/mine")
    @Operation(summary = "我的待办", description = "查询当前用户的待办任务")
    public IPage<TodoTaskPageResp> myTodo(TaskPageReq req) {
        return taskExtService.meTodoPageList(req);
    }

    /**
     * 我的已办任务
     */
    @GetMapping("/done/mine")
    @Operation(summary = "我的已办", description = "查询当前用户的已办任务")
    public IPage<DoneTaskPageResp> myDone(TaskPageReq req) {
        return taskExtService.meDonePageList(req);
    }

    /**
     * 审批通过
     */
    @PostMapping("/{id}/approve")
    @RedisLock(prefix = "workflow:task:handle", message = "当前已有任务处理中，请稍后重试")
    @Operation(summary = "审批通过", description = "审批通过任务")
    public void approve(@PathVariable @RedisParam Long id, @Validated @RequestBody WorkflowTaskReq req) {
        taskExtService.pass(id, req);
    }

    /**
     * 审批拒绝
     */
    @PostMapping("/{id}/reject")
    @RedisLock(prefix = "workflow:task:handle", message = "当前已有任务处理中，请稍后重试")
    @Operation(summary = "审批拒绝", description = "拒绝任务")
    public void reject(@PathVariable @RedisParam Long id, @Validated @RequestBody WorkflowTaskReq req) {
        taskExtService.reject(id, req);
    }

    /**
     * 任务驳回
     */
    @PostMapping("/{id}/return")
    @RedisLock(prefix = "workflow:task:handle", message = "当前已有任务处理中，请稍后重试")
    @Operation(summary = "任务驳回", description = "驳回到上一节点")
    public void returnTask(@PathVariable @RedisParam Long id, @RequestBody WorkflowTaskReq req) {
        taskExtService.taskReturn(id, req);
    }

    /**
     * 任务终止
     */
    @PostMapping("/{id}/terminate")
    @RedisLock(prefix = "workflow:task:handle", message = "当前已有任务处理中，请稍后重试")
    @Operation(summary = "任务终止", description = "终止流程")
    public void terminate(@PathVariable @RedisParam Long id, @RequestBody WorkflowTaskReq req) {
        taskExtService.termination(id, req);
    }

    /**
     * 任务转办
     */
    @PostMapping("/{id}/transfer")
    @Operation(summary = "任务转办", description = "转办给其他人")
    public void transfer(@PathVariable Long id, @RequestBody WorkflowTaskReq req) {
        taskExtService.transfer(id, req);
    }

    /**
     * 任务加签
     */
    @PostMapping("/{id}/sign/add")
    @Operation(summary = "任务加签", description = "添加会签人")
    public void addSign(@PathVariable Long id, @RequestBody WorkflowTaskReq req) {
        taskExtService.addSignature(id, req);
    }

    /**
     * 任务减签
     */
    @PostMapping("/{id}/sign/remove")
    @Operation(summary = "任务减签", description = "移除会签人")
    public void removeSign(@PathVariable Long id, @RequestBody WorkflowTaskReq req) {
        taskExtService.removeSignature(id, req);
    }
}
