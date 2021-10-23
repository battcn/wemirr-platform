package com.wemirr.platform.tools.controller.dynamic;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.db.mybatis.conditions.Wraps;
import com.wemirr.framework.db.page.PageRequest;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseColumn;
import com.wemirr.platform.tools.domain.req.DynamicReleaseGridColumnReq;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseGridColumnResp;
import com.wemirr.platform.tools.service.DynamicReleaseColumnService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/dynamic_release_grids/{grid_id}/columns")
@RequiredArgsConstructor
@Tag(name = "字段管理", description = "字段管理")
public class DynamicReleaseColumnController {

    private final DynamicReleaseColumnService dynamicReleaseColumnService;

    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping
    public Result<IPage<DynamicReleaseGridColumnResp>> list(PageRequest pageRequest, @PathVariable("grid_id") Long gridId) {
        Page<DynamicReleaseGridColumnResp> page = new Page<>(pageRequest.getCurrent(), pageRequest.getSize());
        final List<DynamicReleaseGridColumnResp> columns = dynamicReleaseColumnService.listByGridId(gridId);
        page.setRecords(columns);
        return Result.success(page);
    }

    @PutMapping
    public Result<ResponseEntity<Void>> edit(@PathVariable("grid_id") Long gridId, @RequestBody DynamicReleaseGridColumnReq req) {
        log.info("[请求参数] - {}", JSON.toJSONString(req));
        this.dynamicReleaseColumnService.edit(gridId, req);
        return Result.success();
    }

    @DeleteMapping("/{key}")
    public Result<ResponseEntity<Void>> del(@PathVariable("grid_id") Long gridId, @PathVariable String key) {
        this.dynamicReleaseColumnService.remove(Wraps.<DynamicReleaseColumn>lbQ()
                .eq(DynamicReleaseColumn::getKey, key)
                .eq(DynamicReleaseColumn::getGridId, gridId));
        return Result.success();
    }

}
