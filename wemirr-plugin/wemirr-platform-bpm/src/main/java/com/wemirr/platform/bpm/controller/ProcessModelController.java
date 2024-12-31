package com.wemirr.platform.bpm.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.bpm.domain.entity.ProcessModel;
import com.wemirr.platform.bpm.domain.req.*;
import com.wemirr.platform.bpm.domain.resp.*;
import com.wemirr.platform.bpm.service.ProcessModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 流程模型管理(DesignModel)控制层
 *
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "模型管理", description = "流程模型管理")
@RequestMapping(value = "/process-models", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProcessModelController {

    private final ProcessModelService processModelService;

    @GetMapping("/list")
    @Operation(summary = "模型列表", description = "流程模型查询")
    public List<DesignModelListResp> list() {
        return processModelService.list(Wraps.<ProcessModel>lbQ().isNotNull(ProcessModel::getDefinitionId)).stream()
                .map(x -> DesignModelListResp.builder().id(x.getId()).diagramName(x.getDiagramName())
                        .definitionId(x.getDefinitionId()).diagramIcon(x.getDiagramIcon()).build())
                .collect(Collectors.toList());
    }

    @GetMapping("/group-list")
    @Operation(summary = "分组查询", description = "流程模型分组查询")
    public List<DesignModelGroupListResp> groupList() {
        return processModelService.groupList();
    }

    @PostMapping("/page")
    @Operation(summary = "分页查询", description = "分页查询流程模型管理")
    public IPage<ProcessModelPageResp> page(@RequestBody ProcessModelPageReq vo) {
        return processModelService.pageList(vo);
    }

    @Operation(summary = "创建模型", description = "添加流程模型管理")
    @PostMapping("/create")
    public void create(@Validated @RequestBody DesignModelSaveReq req) {
        processModelService.create(req);
    }

    @Operation(summary = "修改模型", description = "修改流程模型管理")
    @Parameter(in = ParameterIn.PATH, description = "模型id")
    @PutMapping("/{id}")
    public void modify(@PathVariable Long id, @Validated @RequestBody DesignModelSaveReq req) {
        processModelService.modify(id, req);
    }

    @Operation(summary = "删除模型", description = "删除流程模型管理")
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id, @RequestBody DesignModelDeleteReq req) {
        processModelService.deleteByModel(id, req);
    }

    @Operation(summary = "通过模型id查询详情", description = "查询流程模型管理详情")
    @Parameter(in = ParameterIn.PATH, description = "模型id")
    @GetMapping(value = "/{id}")
    public ProcessModelDetailResp detail(@PathVariable Long id) {
        return processModelService.detail(id);
    }


    @Operation(summary = "流程部署")
    @PostMapping("/{id}/deploy")
    public void deploy(@PathVariable("id") Long id) {
        processModelService.deployById(id);
    }


    @Operation(summary = "启动流程")
    @PostMapping("/{id}/start-instance")
    public void start(@PathVariable("id") Long id, @Validated @RequestBody InstanceStartReq req) {
        processModelService.startInstance(id, req);
    }


    @Operation(summary = "表单设计", description = "保存表单设计")
    @PostMapping(value = "/{id}/form-designs")
    public void formDesign(@PathVariable Long id, @Validated @RequestBody FormDesignSaveReq req) {
        processModelService.saveFormDesign(id, req);
    }

    @Operation(summary = "表单设计", description = "表单设计详情")
    @GetMapping(value = "/{id}/form-designs")
    public DesignModelFormResp formDesign(@PathVariable Long id) {
        return processModelService.findFormDesign(id);
    }
}
