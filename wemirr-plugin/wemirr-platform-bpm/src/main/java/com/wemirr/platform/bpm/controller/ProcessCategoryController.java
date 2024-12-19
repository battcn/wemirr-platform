
package com.wemirr.platform.bpm.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.bpm.domain.entity.ProcessCategory;
import com.wemirr.platform.bpm.domain.req.ProcessCategoryPageReq;
import com.wemirr.platform.bpm.domain.req.ProcessCategoryQueryReq;
import com.wemirr.platform.bpm.domain.req.ProcessCategorySaveReq;
import com.wemirr.platform.bpm.domain.resp.ProcessCategoryPageResp;
import com.wemirr.platform.bpm.domain.resp.ProcessCategoryQueryResp;
import com.wemirr.platform.bpm.service.ProcessCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 流程类别(ProcessCategory)控制层
 *
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "类别管理", description = "流程类别相关")
@RequestMapping(value = "/process_categories")
public class ProcessCategoryController {

    private final ProcessCategoryService processCategoryService;

    @Operation(summary = "分页查询", description = "分页查询流程类别")
    @PostMapping(value = "/page")
    public IPage<ProcessCategoryPageResp> pageList(@RequestBody ProcessCategoryPageReq req) {
        return processCategoryService.page(req.buildPage(), Wraps.<ProcessCategory>lbQ().eq(ProcessCategory::getStatus, req.getStatus())
                        .likeRight(ProcessCategory::getName, req.getName()).likeRight(ProcessCategory::getCode, req.getCode()))
                .convert(bean -> BeanUtil.toBean(bean, ProcessCategoryPageResp.class));
    }

    @Operation(summary = "添加类别", description = "添加流程类别")
    @PostMapping("/create")
    public void create(@RequestBody ProcessCategorySaveReq vo) {
        processCategoryService.create(vo);
    }


    @Operation(summary = "修改类别", description = "修改流程类别")
    @Parameter(in = ParameterIn.PATH, description = "类别ID", name = "id")
    @PutMapping(value = "/{id}")
    public void modify(@PathVariable Long id, @RequestBody ProcessCategorySaveReq vo) {
        processCategoryService.modify(id, vo);
    }

    @Operation(summary = "删除类别", description = "删除流程类别")
    @Parameter(in = ParameterIn.PATH, description = "类别ID", name = "id")
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        processCategoryService.removeById(id);
    }

    @Operation(summary = "流程类别集合", description = "查询有效的类表")
    @GetMapping(value = "/list")
    public List<ProcessCategoryQueryResp> list(ProcessCategoryQueryReq req) {
        return BeanUtilPlus.toBeans(processCategoryService.list(Wraps.<ProcessCategory>lbQ()
                .eq(ProcessCategory::getStatus, req.getStatus())
                .likeRight(ProcessCategory::getName, req.getName())
                .likeRight(ProcessCategory::getCode, req.getCode())), ProcessCategoryQueryResp.class);
    }

}
