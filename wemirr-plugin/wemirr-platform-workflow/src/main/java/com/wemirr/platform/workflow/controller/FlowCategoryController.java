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
 * 流程分类管理
 *
 * @author battcn
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/flow-categories")
@Tag(name = "流程分类管理", description = "流程分类管理")
public class FlowCategoryController {

    private final FlowCategoryService flowCategoryService;

    @GetMapping("/list")
    @Operation(summary = "流程分类列表")
    public List<FlowCategoryPageResp> list() {
        List<FlowCategory> list = flowCategoryService.list(Wraps.<FlowCategory>lbQ().eq(FlowCategory::getStatus, true)
                .orderByDesc(FlowCategory::getId));
        return BeanUtilPlus.toBeans(list, FlowCategoryPageResp.class);
    }

    @PostMapping("/page")
    @Operation(summary = "分页查询")
    public IPage<FlowCategoryPageResp> pageList(@RequestBody FlowCategoryPageReq req) {
        return flowCategoryService.pageList(req);
    }

    @PostMapping("/create")
    @AccessLog(module = "流程分类管理", description = "添加流程分类")
    @Operation(summary = "添加流程分类")
    public void create(@Validated @RequestBody FlowCategorySaveReq req) {
        flowCategoryService.create(req);
    }

    @PutMapping("/{id}/modify")
    @AccessLog(module = "流程分类管理", description = "编辑流程分类")
    @Operation(summary = "编辑流程分类")
    public void modify(@PathVariable String id, @Validated @RequestBody FlowCategorySaveReq req) {
        flowCategoryService.modify(id, req);
    }

    @DeleteMapping("/{id}")
    @AccessLog(module = "流程分类管理", description = "删除流程分类")
    @Operation(summary = "删除流程分类")
    public void remove(@PathVariable Long id) {
        flowCategoryService.removeById(id);
    }

//    @PutMapping("/{id}/disabled")
//    @AccessLog(module = "流程分类管理", description = "停用流程分类")
//    @Operation(summary = "停用流程分类")
//    public void disabled(@PathVariable String id) {
//        this. flowCategoryService.modifyStatus(id, ActiveStatus.DISABLED);
//    }
//
//    @PutMapping("/{id}/enabled")
//    @AccessLog(module = "流程分类管理", description = "启用流程分类")
//    @Operation(summary = "启用流程分类")
//    public void enabled(@PathVariable String id) {
//        this. flowCategoryService.modifyStatus(id, ActiveStatus.ENABLED);
//    }

}