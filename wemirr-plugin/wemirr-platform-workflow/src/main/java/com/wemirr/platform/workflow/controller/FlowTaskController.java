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
 * @author battcn
 * @since 2025/5/22
 **/
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/flow-tasks")
@Tag(name = "流程任务", description = "流程任务")
public class FlowTaskController {

    private final TaskExtService taskExtService;

    @PostMapping("/todo-page")
    @Operation(summary = "待办任务 - [DONE]", description = "所有的待办任务清单")
    public IPage<TodoTaskPageResp> pageList(@RequestBody TaskPageReq req) {
        return taskExtService.todoPageList(req);
    }

    @PostMapping("/me-todo-page")
    @Operation(summary = "我的待办 - [DONE]", description = "查询当前登录人需要处理的待办任务")
    public IPage<TodoTaskPageResp> meTodoPageList(@RequestBody TaskPageReq req) {
        return taskExtService.meTodoPageList(req);
    }


    @PostMapping("/me-done-page")
    @Operation(summary = "我的已办 - [DONE]", description = "查询当前登陆人已办的任务列表")
    public IPage<DoneTaskPageResp> queryDoneTaskByUser(@RequestBody TaskPageReq req) {
        return taskExtService.meDonePageList(req);
    }

    @PostMapping("/{id}/pass")
    @RedisLock(prefix = "lock:workflow:task:handle", message = "当前已有任务处理中,请稍后在试")
    @Operation(summary = "审批通过 - [DONE]", description = "审批通过")
    public void pass(@PathVariable @RedisParam Long id, @Validated @RequestBody WorkflowTaskReq req) {
        taskExtService.pass(id, req);
    }

    @PostMapping("/{id}/reject")
    @RedisLock(prefix = "lock:workflow:task:handle", message = "当前已有任务处理中,请稍后在试")
    @Operation(summary = "审批拒绝 - [DONE]", description = "审批拒绝")
    public void reject(@PathVariable @RedisParam Long id, @Validated @RequestBody WorkflowTaskReq req) {
        taskExtService.reject(id, req);
    }

    @PostMapping("/{id}/return")
    @RedisLock(prefix = "lock:workflow:task:handle", message = "当前已有任务处理中,请稍后在试")
    @Operation(summary = "任务驳回 - [DONE]", description = "驳回任务到上一个节点")
    public void taskReturn(@PathVariable @RedisParam Long id, @RequestBody WorkflowTaskReq req) {
        taskExtService.taskReturn(id, req);
    }

    @PostMapping("/{id}/termination")
    @RedisLock(prefix = "lock:workflow:task:handle", message = "当前已有任务处理中,请稍后在试")
    @Operation(summary = "任务终止 - [DONE]", description = "根据任务id终止实例")
    public void termination(@PathVariable("id") @RedisParam Long id, @RequestBody WorkflowTaskReq req) {
        taskExtService.termination(id, req);
    }

    @PostMapping("/{id}/transfer")
    @Operation(summary = "任务转办 - [DONE]", description = "转办")
    public void transfer(@PathVariable("id") Long id, @RequestBody WorkflowTaskReq req) {
        taskExtService.transfer(id, req);
    }

    @PostMapping("/{id}/add-signature")
    @Operation(summary = "任务加签 - [DONE]", description = "加签")
    public void addSignature(@PathVariable("id") Long id, @RequestBody WorkflowTaskReq req) {
        taskExtService.addSignature(id, req);
    }

    @PostMapping("/{id}/remove-signature")
    @Operation(summary = "任务减签 - [DONE]", description = "减签")
    public void removeSignature(@PathVariable("id") Long id, @RequestBody WorkflowTaskReq req) {
        taskExtService.removeSignature(id, req);
    }

}
