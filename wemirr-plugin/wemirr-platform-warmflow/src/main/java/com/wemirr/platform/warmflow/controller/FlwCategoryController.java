package com.wemirr.platform.warmflow.controller;

import cn.hutool.core.lang.tree.Tree;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.excel.domain.ExcelWriteFile;
import com.wemirr.platform.warmflow.configuration.common.constant.FlowConstant;
import com.wemirr.platform.warmflow.domain.req.warmflow.category.FlowCategoryReq;
import com.wemirr.platform.warmflow.domain.req.warmflow.category.FlowCategorySaveReq;
import com.wemirr.platform.warmflow.domain.resp.warmflow.FlowCategoryResp;
import com.wemirr.platform.warmflow.service.IFlwCategoryService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 流程分类
 *
 * @author may
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/workflow/category")
public class FlwCategoryController {

    private final IFlwCategoryService flwCategoryService;

    /**
     * 查询流程分类列表
     */
//    @SaCheckPermission("workflow:category:list")
    @GetMapping("/list")
    public List<FlowCategoryResp> list(FlowCategoryReq req) {
        return flwCategoryService.queryList(req);
    }

    /**
     * 导出流程分类列表
     */
//    @SaCheckPermission("workflow:category:export")
//    @Log(title = "流程分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(FlowCategoryReq req, HttpServletResponse response) {
        List<FlowCategoryResp> list = flwCategoryService.queryList(req);
        ExcelWriteFile.builder().fileName("流程分类").data(list).build();
//        ExcelUtil.exportExcel(list, "流程分类", FlowCategoryVo.class, response);
    }

    /**
     * 获取流程分类详细信息
     *
     * @param categoryId 主键
     */
    @GetMapping("/{categoryId}")
    public FlowCategoryResp getInfo(@NotNull(message = "主键不能为空") @PathVariable Long categoryId) {
        flwCategoryService.checkCategoryDataScope(categoryId);
        return flwCategoryService.queryById(categoryId);
    }


    /**
     * 新增流程分类
     */
//    @SaCheckPermission("workflow:category:add")
//    @Log(title = "流程分类", businessType = BusinessType.INSERT)
//    @RepeatSubmit()
    @PostMapping()
    public void add(@Validated(SuperEntity.Save.class) @RequestBody FlowCategorySaveReq category) {
        if (!flwCategoryService.checkCategoryNameUnique(category)) {
            throw new CheckedException("新增流程分类'" + category.getCategoryName() + "'失败，流程分类名称已存在");
        }
        flwCategoryService.insert(category);
    }

    /**
     * 修改流程分类
     */
//    @SaCheckPermission("workflow:category:edit")
//    @Log(title = "流程分类", businessType = BusinessType.UPDATE)
//    @RepeatSubmit()
    @PutMapping()
    public void edit(@Validated(SuperEntity.Update.class) @RequestBody FlowCategorySaveReq category) {
        Long categoryId = category.getId();
        flwCategoryService.checkCategoryDataScope(categoryId);
        if (!flwCategoryService.checkCategoryNameUnique(category)) {
            throw new CheckedException("修改流程分类'" + category.getCategoryName() + "'失败，流程分类名称已存在");
        } else if (category.getParentId().equals(categoryId)) {
            throw new CheckedException("修改流程分类'" + category.getCategoryName() + "'失败，上级流程分类不能是自己");
        }
        flwCategoryService.updateByBo(category);
    }

    /**
     * 删除流程分类
     *
     * @param categoryId 主键
     */
//    @SaCheckPermission("workflow:category:remove")
//    @Log(title = "流程分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{categoryId}")
    public void remove(@PathVariable Long categoryId) {
        if (FlowConstant.FLOW_CATEGORY_ID.equals(categoryId)) {
            throw new CheckedException("默认流程分类,不允许删除");
        }
        if (flwCategoryService.hasChildByCategoryId(categoryId)) {
            throw new CheckedException("存在下级流程分类,不允许删除");
        }
        if (flwCategoryService.checkCategoryExistDefinition(categoryId)) {
            throw new CheckedException("流程分类存在流程定义,不允许删除");
        }
        flwCategoryService.deleteWithValidById(categoryId);
    }

    /**
     * 获取流程分类树列表
     *
     * @param req 流程分类
     */
    @GetMapping("/categoryTree")
    public List<Tree<String>> categoryTree(FlowCategoryReq req) {
        return flwCategoryService.selectCategoryTreeList(req);
    }

}
