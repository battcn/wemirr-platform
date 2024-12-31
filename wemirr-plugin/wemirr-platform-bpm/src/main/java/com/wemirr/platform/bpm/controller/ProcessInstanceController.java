package com.wemirr.platform.bpm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.platform.bpm.domain.req.ProcessInstancePageReq;
import com.wemirr.platform.bpm.domain.resp.ProcessInstanceDetailResp;
import com.wemirr.platform.bpm.domain.resp.ProcessInstancePageResp;
import com.wemirr.platform.bpm.domain.resp.ProcessTaskCommentResp;
import com.wemirr.platform.bpm.domain.resp.RenderFormResp;
import com.wemirr.platform.bpm.feign.domain.req.StartInstanceReq;
import com.wemirr.platform.bpm.feign.domain.resp.StartInstanceResp;
import com.wemirr.platform.bpm.service.ProcessInstanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 流程实例控制层
 *
 * @author Levin
 */

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "流程实例", description = "流程实例管理")
@RequestMapping(value = "/process-instances", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProcessInstanceController {

    private final ProcessInstanceService processInstanceService;

    @PostMapping("/page")
    @Operation(summary = "流程实例列表", description = "流程实例列表")
    public IPage<ProcessInstancePageResp> pageList(@RequestBody ProcessInstancePageReq req) {
        return processInstanceService.pageList(req);
    }

    @PostMapping("/start")
    @Operation(summary = "启动实例", description = "启动流程实例")
    public StartInstanceResp start(@RequestBody StartInstanceReq req) {
        return processInstanceService.startProcess(req);
    }


    @PutMapping("/{instance_id}/status/{status}")
    @Operation(summary = "状态变更", description = "挂起或激活流程实例")
    public void suspendOrResumeInstance(@PathVariable("instance_id") Long instanceId, @PathVariable("status") Boolean status) {
        processInstanceService.suspendOrResumeInstance(instanceId, status);
    }

    @PostMapping("/{id}/cancel")
    @Operation(summary = "作废流程实例", description = "作废流程实例")
    public void cancel(@PathVariable String id) {
        processInstanceService.cancel(id);
    }

    @GetMapping("/{id}/detail")
    @Operation(summary = "实例详情", description = "获取流程图展示流程图节点")
    public ProcessInstanceDetailResp detail(@PathVariable String id) {
        return processInstanceService.detail(id);
    }

    @GetMapping("/{id}/form-preview")
    @Operation(summary = "表单渲染", description = "表单渲染")
    public RenderFormResp renderForm(@PathVariable String id) {
        return processInstanceService.renderForm(id);
    }

    @GetMapping("/{id}/approval_infos")
    @Operation(summary = "审核信息", description = "审核信息")
    public List<ProcessTaskCommentResp> approvalInfo(@PathVariable String id) {
        return processInstanceService.approvalInfo(id);
    }


}
