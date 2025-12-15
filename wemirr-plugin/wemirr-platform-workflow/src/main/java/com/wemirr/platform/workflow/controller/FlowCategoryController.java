package com.wemirr.platform.workflow.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.workflow.domain.dto.req.FlowCategoryPageReq;
import com.wemirr.platform.workflow.domain.dto.req.FlowCategorySaveReq;
import com.wemirr.platform.workflow.domain.dto.resp.FlowCategoryPageResp;
import com.wemirr.platform.workflow.domain.entity.FlowCategory;
import com.wemirr.platform.workflow.service.FlowCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 流程分类控制器
 * <p>
 * 管理流程分类，用于对流程定义进行分类组织
 *
 * @author Levin
 * @since 2025-05
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/flow-categories")
@Tag(name = "流程分类", description = "流程分类管理")
public class FlowCategoryController {

    private final FlowCategoryService flowCategoryService;

    @GetMapping
    @Operation(summary = "分页查询", description = "分页查询流程分类列表")
    public IPage<FlowCategoryPageResp> page(FlowCategoryPageReq req) {
        return flowCategoryService.pageList(req);
    }


    @GetMapping("/enabled")
    @Operation(summary = "启用分类", description = "获取所有启用的流程分类")
    public List<FlowCategoryPageResp> enabled() {
        List<FlowCategory> list = flowCategoryService.list(Wraps.<FlowCategory>lbQ()
                .eq(FlowCategory::getStatus, true)
                .orderByDesc(FlowCategory::getId));
        return BeanUtilPlus.toBeans(list, FlowCategoryPageResp.class);
    }

    @PostMapping
    @AccessLog(module = "流程分类", description = "新增流程分类")
    @Operation(summary = "新增分类", description = "新增流程分类")
    public void create(@Validated @RequestBody FlowCategorySaveReq req) {
        flowCategoryService.create(req);
    }

    @PutMapping("/{id}")
    @AccessLog(module = "流程分类", description = "修改流程分类")
    @Operation(summary = "修改分类", description = "修改流程分类")
    public void modify(@PathVariable String id, @Validated @RequestBody FlowCategorySaveReq req) {
        flowCategoryService.modify(id, req);
    }

    @DeleteMapping("/{id}")
    @AccessLog(module = "流程分类", description = "删除流程分类")
    @Operation(summary = "删除分类", description = "删除流程分类")
    public void delete(@PathVariable Long id) {
        flowCategoryService.removeById(id);
    }
}