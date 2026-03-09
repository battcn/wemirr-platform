package com.wemirr.platform.workflow.controller;

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
import com.wemirr.platform.workflow.feign.domain.resp.InstanceStartResp;
import com.wemirr.platform.workflow.service.DefExtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.warm.flow.core.dto.DefJson;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


/**
 * 流程定义控制器
 * <p>
 * 管理流程定义的创建、部署、发布、导入导出等操作
 *
 * @author Levin
 * @since 2025-05
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/flow-definitions")
@Tag(name = "流程定义", description = "流程定义管理")
@RequiredArgsConstructor
public class FlowDefinitionController {

    private final DefExtService defExtService;

    @GetMapping
    @Operation(summary = "分页查询", description = "分页查询流程定义列表")
    public IPage<FlowDefinitionPageResp> page(DefinitionPageReq req) {
        return defExtService.pageList(req);
    }

    @GetMapping("/{id}/detail")
    @Operation(summary = "定义详情", description = "获取流程定义的详细信息")
    public DefJson detail(@PathVariable Long id) {
        return defExtService.detail(id);
    }

    @GetMapping("/group-list")
    @Operation(summary = "分组查询", description = "按分类查询流程定义")
    public List<DesignModelGroupListResp> groupList() {
        return defExtService.groupList();
    }

    @PostMapping("/deploy")
    @Operation(summary = "部署流程", description = "部署新的流程定义")
    public WorkflowDefinitionResp deploy(@Validated @RequestBody DefinitionDeployReq req) {
        return defExtService.deploy(req);
    }

    @PutMapping("/{id}/publish")
    @Operation(summary = "发布流程", description = "发布流程定义")
    public void publish(@PathVariable Long id) {
        defExtService.publish(id);
    }

    @PutMapping("/{id}/unpublish")
    @Operation(summary = "取消发布", description = "取消发布流程定义")
    public void unpublish(@PathVariable Long id) {
        defExtService.unPublish(id);
    }

    @PutMapping("/{id}/active")
    @Operation(summary = "激活流程", description = "激活流程定义")
    public void active(@PathVariable Long id) {
        defExtService.active(id);
    }

    @PutMapping("/{id}/suspend")
    @Operation(summary = "挂起流程", description = "挂起流程定义")
    public void suspend(@PathVariable Long id) {
        defExtService.unActive(id);
    }

    @PostMapping("/{id}/copy")
    @Operation(summary = "复制定义", description = "复制流程定义")
    public void copy(@PathVariable Long id) {
        defExtService.copyDef(id);
    }

    /**
     * 删除流程定义
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除定义", description = "删除流程定义")
    public void delete(@PathVariable Long id) {
        defExtService.delete(id);
    }

    /**
     * 导出流程定义
     */
    @GetMapping("/{id}/export")
    @Operation(summary = "导出定义", description = "导出流程定义 JSON")
    public Map<String, Object> export(@PathVariable Long id) {
        return defExtService.exportJson(id);
    }

    /**
     * 导入流程定义
     */
    @PostMapping("/import")
    @Operation(summary = "导入定义", description = "导入流程定义 JSON")
    public void importDefinition(@RequestParam("file") MultipartFile file) throws Exception {
        defExtService.importDef(file.getInputStream());
    }

    @PostMapping("/{id}/form-design")
    @Operation(summary = "保存表单设计", description = "保存流程定义的表单设计")
    public void saveFormDesign(@PathVariable Long id, @Validated @RequestBody FormDesignSaveReq req) {
        defExtService.addFormDesign(id, req);
    }

    @GetMapping("/{id}/form-design")
    @Operation(summary = "获取表单设计", description = "获取流程定义的表单设计")
    public DesignModelFormResp getFormDesign(@PathVariable Long id) {
        return defExtService.findFormDesign(id);
    }

    @RedisLock(prefix = "workflow:definition:start")
    @PostMapping("/{id}/start")
    @Operation(summary = "启动流程", description = "根据流程定义启动流程实例")
    public InstanceStartResp start(@RedisParam @PathVariable Long id, @Validated @RequestBody InstanceStartReq req) {
        return defExtService.startInstance(id, req);
    }
}
