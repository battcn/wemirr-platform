package com.wemirr.platform.workflow.controller;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.redis.plus.anontation.RedisLock;
import com.wemirr.framework.redis.plus.anontation.RedisParam;
import com.wemirr.platform.workflow.domain.dto.req.DefinitionDeployReq;
import com.wemirr.platform.workflow.domain.dto.req.DefinitionPageReq;
import com.wemirr.platform.workflow.domain.dto.req.FormDesignSaveReq;
import com.wemirr.platform.workflow.domain.dto.req.InstanceStartReq;
import com.wemirr.platform.workflow.domain.dto.resp.DesignModelFormResp;
import com.wemirr.platform.workflow.domain.dto.resp.DesignModelGroupListResp;
import com.wemirr.platform.workflow.domain.dto.resp.FlowDefinitionPageResp;
import com.wemirr.platform.workflow.domain.dto.resp.WorkflowDefinitionResp;
import com.wemirr.platform.workflow.feign.domain.req.WorkflowStartReq;
import com.wemirr.platform.workflow.feign.domain.resp.InstanceStartResp;
import com.wemirr.platform.workflow.service.DefExtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.warm.flow.core.dto.DefJson;
import org.dromara.warm.flow.orm.entity.FlowDefinition;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * workflow controller
 *
 * @author battcn
 * @since 2025/5/21
 **/
@Slf4j
@Validated
@RestController
@RequestMapping("/flow-definitions")
@Tag(name = "流程定义", description = "流程定义")
@RequiredArgsConstructor
public class FlowDefinitionController {

    private final DefExtService defExtService;

    @GetMapping("/group-list")
    @Operation(summary = "分组查询", description = "流程模型分组查询")
    public List<DesignModelGroupListResp> groupList() {
        return defExtService.groupList();
    }

    @PostMapping("/page")
    @Operation(summary = "分页查询流程定义", description = "分页查询流程定义")
    public IPage<FlowDefinitionPageResp> pageList(@RequestBody DefinitionPageReq req) {
        return defExtService.pageList(req);
    }

    @GetMapping("/{id}")
    @Operation(summary = "定义明细", description = "获取流程定义的详细信息")
    public DefJson detail(@PathVariable("id") Long id) {
        return defExtService.detail(id);
    }


    @PostMapping("/deploy")
    @Operation(summary = "流程部署 - [DONE]", description = "部署流程实例")
    public WorkflowDefinitionResp deploy(@RequestBody DefinitionDeployReq req) {
        return defExtService.deploy(req);
    }

    @PutMapping("/{id}/publish")
    @Operation(summary = "发布流程 - [DONE]", description = "发布流程")
    public void publishFlow(@PathVariable("id") Long id) {
        defExtService.publish(id);
    }

    @PutMapping("/{id}/unpublish")
    @Operation(summary = "取消发布", description = "取消发布流程定义")
    public void unPublish(@PathVariable("id") Long id) {
        defExtService.unPublish(id);
    }


    @PostMapping("/start")
    @Operation(summary = "开启流程", description = "开启一个流程")
    public InstanceStartResp start(@RequestBody WorkflowStartReq req) {
        return defExtService.startFlow(req);
    }


    @PutMapping("/modify")
    @Operation(summary = "修改流程定义", description = "修改流程定义")
    public void modify(@RequestBody FlowDefinition flowDefinition) {
//        return defExtService.updateById(flowDefinition);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除定义 - [DONE]", description = "删除流程定义")
    public void remove(@PathVariable Long id) {
        defExtService.delete(id);
    }

    @GetMapping("/{id}/copy")
    @Operation(summary = "复制流程定义", description = "复制流程定义")
    public void copyDef(@PathVariable("id") Long id) {
        defExtService.copyDef(id);
    }

    @GetMapping("/{id}/def-json")
    @Operation(summary = "流程导出", description = "下载流程定义JSON")
    public JSONObject download(@PathVariable("id") Long id) {
        return defExtService.exportJson(id);
    }

    @PostMapping("/import-json")
    public void importDef(@RequestParam("file") MultipartFile file) throws Exception {
        defExtService.importDef(file.getInputStream());
    }


    @GetMapping("/{id}/active")
    @Operation(summary = "激活流程", description = "激活流程")
    public void active(@PathVariable("id") Long id) {
        defExtService.active(id);
    }


    @GetMapping("/{id}/un-active")
    @Operation(summary = "挂起流程", description = "挂起流程")
    public void unActive(@PathVariable("id") Long id) {
        defExtService.unActive(id);
    }


    @Operation(summary = "表单设计", description = "保存表单设计")
    @PostMapping(value = "/{id}/form-designs")
    public void formDesign(@PathVariable Long id, @Validated @RequestBody FormDesignSaveReq req) {
        defExtService.addFormDesign(id, req);
    }

    @Operation(summary = "表单设计", description = "表单设计详情")
    @GetMapping(value = "/{id}/form-designs")
    public DesignModelFormResp formDesign(@PathVariable Long id) {
        return defExtService.findFormDesign(id);
    }


    @RedisLock(prefix = "instance")
    @Operation(summary = "启动流程")
    @PostMapping("/{id}/start-instance")
    public InstanceStartResp start(@RedisParam @PathVariable("id") Long id, @Validated @RequestBody InstanceStartReq req) {
        return defExtService.startInstance(id, req);
    }

}
