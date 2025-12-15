package com.wemirr.platform.workflow.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.platform.workflow.domain.dto.req.InstancePageReq;
import com.wemirr.platform.workflow.domain.dto.resp.FlowTaskApproveListResp;
import com.wemirr.platform.workflow.domain.dto.resp.InstanceExtDetailResp;
import com.wemirr.platform.workflow.domain.dto.resp.InstancePageResp;
import com.wemirr.platform.workflow.domain.dto.resp.ProcessInstanceFormPreviewResp;
import com.wemirr.platform.workflow.feign.domain.req.WorkflowInstanceTerminationReq;
import com.wemirr.platform.workflow.service.InstanceExtService;
import com.wemirr.platform.workflow.service.TaskExtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.warm.flow.core.entity.Instance;
import org.dromara.warm.flow.core.entity.Task;
import org.dromara.warm.flow.core.service.InsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 流程实例控制器
 * <p>
 * 管理流程实例的查询、激活、挂起、终止等操作
 *
 * @author Levin
 * @since 2025-05
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/flow-instances")
@Tag(name = "流程实例", description = "流程实例管理")
@RequiredArgsConstructor
public class FlowInstanceController {

    private final InsService insService;
    private final InstanceExtService instanceExtService;
    private final TaskExtService taskExtService;

    @GetMapping
    @Operation(summary = "分页查询", description = "分页查询流程实例列表")
    public IPage<InstancePageResp> page(InstancePageReq req) {
        return instanceExtService.pageList(req);
    }

    @GetMapping("/{id}/detail")
    @Operation(summary = "实例详情", description = "获取流程实例详细信息")
    public InstanceExtDetailResp detail(@PathVariable Long id) {
        return instanceExtService.extInfo(id);
    }

    @GetMapping("/{id}/basic")
    @Operation(summary = "基本信息", description = "获取流程实例基本信息")
    public Instance basic(@PathVariable Long id) {
        return insService.getById(id);
    }

    @GetMapping("/mine")
    @Operation(summary = "我的流程", description = "查询当前用户发起的流程实例")
    public IPage<InstancePageResp> mine(InstancePageReq req) {
        return instanceExtService.mePageList(req);
    }

    @PutMapping("/{id}/active")
    @Operation(summary = "激活实例", description = "激活流程实例")
    public void active(@PathVariable Long id) {
        insService.active(id);
    }

    @PutMapping("/{id}/suspend")
    @Operation(summary = "挂起实例", description = "挂起流程实例")
    public void suspend(@PathVariable Long id) {
        insService.unActive(id);
    }

    @PostMapping("/{id}/terminate")
    @Operation(summary = "终止实例", description = "终止流程实例")
    public void terminate(@PathVariable Long id, @RequestBody WorkflowInstanceTerminationReq req) {
        // TODO: taskExtService.terminationByInstanceId(id, req);
    }

    /**
     * 获取实例任务列表
     */
    @GetMapping("/{id}/tasks")
    @Operation(summary = "任务列表", description = "获取流程实例的任务列表")
    public List<Task> tasks(@PathVariable Long id) {
        return taskExtService.getTaskByInstantId(id);
    }

    /**
     * 获取实例全部任务（含历史）
     */
    @GetMapping("/{id}/tasks/history")
    @Operation(summary = "历史任务", description = "获取流程实例的全部任务（含历史）")
    public List<FlowTaskApproveListResp> taskHistory(@PathVariable Long id) {
        return instanceExtService.allTask(id);
    }

    /**
     * 获取实例表单预览
     */
    @GetMapping("/{id}/form")
    @Operation(summary = "表单预览", description = "获取流程实例的表单数据")
    public ProcessInstanceFormPreviewResp form(@PathVariable String id) {
        return taskExtService.formPreview(id);
    }
}
