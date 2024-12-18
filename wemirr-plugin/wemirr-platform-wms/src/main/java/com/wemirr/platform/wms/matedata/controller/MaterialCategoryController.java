package com.wemirr.platform.wms.matedata.controller;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.google.common.collect.Maps;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.wms.matedata.domain.entity.MaterialCategory;
import com.wemirr.platform.wms.matedata.domain.req.MaterialCategorySaveReq;
import com.wemirr.platform.wms.matedata.domain.resp.MaterialCategoryPageResp;
import com.wemirr.platform.wms.matedata.service.MaterialCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 物料类目 前端控制器
 * </p>
 *
 * @author ddCat
 * @since 2024-06-18
 */
@RestController
@AllArgsConstructor
@RequestMapping("/metadata/material-categories")
@Tag(name = "物料类目", description = "物料类目")
public class MaterialCategoryController {

    private final MaterialCategoryService materialCategoryService;

    @Operation(summary = "查询可用", description = "查询可用数据")
    @PostMapping("/ids")
    public List<MaterialCategoryPageResp> ids(@RequestBody List<Long> ids) {
        return BeanUtilPlus.toBeans(this.materialCategoryService.list(Wraps.<MaterialCategory>lbQ()
                .in(MaterialCategory::getId, ids)), MaterialCategoryPageResp.class);
    }


    @Operation(summary = "类目列表", description = "类目列表")
    @GetMapping("/list")
    public List<MaterialCategoryPageResp> list() {
        return BeanUtilPlus.toBeans(this.materialCategoryService.list(Wraps.<MaterialCategory>lbQ()), MaterialCategoryPageResp.class);
    }

    @GetMapping("/tree")
    @Operation(summary = "列表查询 - [DONE] - [Levin]")
    public List<Tree<Long>> tree() {
        List<MaterialCategory> list = this.materialCategoryService.list();
        final List<TreeNode<Long>> nodes = list.stream().map(item -> {
            final TreeNode<Long> treeNode = new TreeNode<>(item.getId(), item.getParentId(), item.getName(), 0);
            Map<String, Object> extra = Maps.newLinkedHashMap();
            extra.put("label", item.getName());
            extra.put("status", item.getStatus());
            extra.put("remark", item.getRemark());
            treeNode.setExtra(extra);
            return treeNode;
        }).collect(Collectors.toList());
        return TreeUtil.build(nodes, 0L);
    }

    @PostMapping("/create")
    @AccessLog(module = "", description = "物料类目管理 - 新增物料类目")
    @Operation(summary = "新增物料类目 - [DONE] - [Levin]")
    public void create(@Validated @RequestBody MaterialCategorySaveReq req) {
        materialCategoryService.create(req);
    }

    @PutMapping("/{id}/modify")
    @AccessLog(module = "", description = "物料类目管理 - 修改物料类目")
    @Operation(summary = "修改物料类目 - [DONE] - [Levin]")
    public void modify(@PathVariable("id") Long id, @Validated @RequestBody MaterialCategorySaveReq req) {
        materialCategoryService.modify(id, req);
    }

    @DeleteMapping("/{id}")
    @AccessLog(module = "", description = "物料类目管理 - 删除物料类目")
    @Operation(summary = "删除物料类目 - [DONE] - [Levin]")
    public void delete(@PathVariable Long id) {
        materialCategoryService.cascadeDelete(id);
    }

}




