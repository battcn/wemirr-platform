package com.wemirr.platform.tools.controller.dynamic;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.db.mybatis.conditions.Wraps;
import com.wemirr.framework.db.page.PageRequest;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseGrid;
import com.wemirr.platform.tools.domain.req.DynamicReleaseGridReq;
import com.wemirr.platform.tools.service.DynamicReleaseGridService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/dynamic_release_grids")
@RequiredArgsConstructor
@Tag(name = "数据源管理", description = "数据源管理")
public class DynamicReleaseGridController {

    private final DynamicReleaseGridService dynamicReleaseGridService;

    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping
    public Result<Page<DynamicReleaseGrid>> page(PageRequest pageRequest, String model, String tableName) {
        final Page<DynamicReleaseGrid> page = dynamicReleaseGridService.page(pageRequest.buildPage(),
                Wraps.<DynamicReleaseGrid>lbQ()
                        .like(DynamicReleaseGrid::getTableName, tableName)
                        .like(DynamicReleaseGrid::getModel, model));
        return Result.success(page);
    }

    @Operation(summary = "添加数据源")
    @PostMapping
    public Result<ResponseEntity<Void>> add(@Validated @RequestBody DynamicReleaseGridReq req) {
        dynamicReleaseGridService.save(BeanUtil.toBean(req, DynamicReleaseGrid.class));
        return Result.success();
    }

    @Operation(summary = "编辑数据源")
    @PutMapping("/{id}")
    public Result<ResponseEntity<Void>> edit(@PathVariable Long id, @Validated @RequestBody DynamicReleaseGridReq req) {
        final DynamicReleaseGrid request = BeanUtil.toBean(req, DynamicReleaseGrid.class);
        request.setId(id);
        dynamicReleaseGridService.updateById(request);
        return Result.success();
    }

    @Operation(summary = "删除数据源")
    @DeleteMapping("/{id}")
    public Result<ResponseEntity<Void>> remove(@PathVariable Long id) {
        dynamicReleaseGridService.removeById(id);
        return Result.success();
    }
}
