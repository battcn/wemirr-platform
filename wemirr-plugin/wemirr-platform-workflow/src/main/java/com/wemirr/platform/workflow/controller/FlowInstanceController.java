package com.wemirr.platform.workflow.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.platform.workflow.domain.dto.req.InstancePageReq;
import com.wemirr.platform.workflow.domain.dto.resp.FlowTaskApproveListResp;
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
 * work flow instance controller
 *
 * @author battcn
 * @since 2025/5/22
 **/
@Slf4j
@Validated
@RestController
@RequestMapping("/flow-instances")
@Tag(name = "流程实例", description = "流程实例")
@RequiredArgsConstructor
public class FlowInstanceController {

    private final InsService insService;
    private final InstanceExtService instanceExtService;
    private final TaskExtService taskExtService;

    @GetMapping("/{id}")
    @Operation(summary = "实例信息", description = "实例信息")
    public Instance info(@PathVariable("id") Long id) {
        return insService.getById(id);
    }

    @PutMapping("/{id}/un-active")
    @Operation(summary = "实例挂起", description = "挂起流程实例")
    public void unActive(@PathVariable("id") Long id) {
        insService.unActive(id);
    }

    @PutMapping("/{id}/active")
    @Operation(summary = "实例激活", description = "激活流程实例")
    public void active(@PathVariable("id") Long id) {
        insService.active(id);
    }

    @PostMapping("/me-page")
    @Operation(summary = "我的流程", description = "查询当前登陆人发起的流程实例")
    public IPage<InstancePageResp> mePageList(@RequestBody InstancePageReq req) {
        return instanceExtService.mePageList(req);
    }

    @PostMapping("/page")
    @Operation(summary = "流程实例列表", description = "查询流程实例列表")
    public IPage<InstancePageResp> pageList(@RequestBody InstancePageReq req) {
        return instanceExtService.pageList(req);
    }

    @GetMapping("/{id}/all-tasks")
    @Operation(summary = "流程任务（含历史）", description = "根据流程实例查询任务,包含历史任务")
    public List<FlowTaskApproveListResp> allTask(@PathVariable("id") Long id) {
        return instanceExtService.allTask(id);
    }

    @GetMapping("/{id}/tasks")
    @Operation(summary = "流程任务", description = "根据流程实例ID查询任务列表")
    public List<Task> queryTaskListByInstanceId(@PathVariable("id") Long id) {
        return taskExtService.getTaskByInstantId(id);
    }

    @PostMapping("/{id}/termination")
    @Operation(summary = "实例终止 - [DONE]", description = "根据实例id终止实例")
    public void terminationInstance(@PathVariable("id") Long id, @RequestBody WorkflowInstanceTerminationReq req) {
//        taskExtService.terminationByInstanceId(id, req);
    }


    @GetMapping("/{id}/form-preview")
    @Operation(summary = "表单渲染", description = "表单渲染")
    public ProcessInstanceFormPreviewResp formPreview(@PathVariable String id) {
        return taskExtService.formPreview(id);
    }
}
