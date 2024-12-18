package com.wemirr.platform.wms.basic.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.wms.basic.domain.entity.Warehouse;
import com.wemirr.platform.wms.basic.domain.req.WarehousePageReq;
import com.wemirr.platform.wms.basic.domain.req.WarehouseSaveReq;
import com.wemirr.platform.wms.basic.domain.resp.WarehousePageResp;
import com.wemirr.platform.wms.basic.service.WarehouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 仓库 前端控制器
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
@RestController
@AllArgsConstructor
@RequestMapping("/warehouses")
@Tag(name = "仓库管理", description = "仓库管理")
public class WarehouseController {

    private final WarehouseService warehouseService;


    @Operation(summary = "查询可用 - [DONE]", description = "查询可用数据")
    @PostMapping("/ids")
    public List<WarehousePageResp> list(@RequestBody List<Long> ids) {
        return BeanUtilPlus.toBeans(this.warehouseService.list(Wraps.<Warehouse>lbQ().in(Warehouse::getId, ids)), WarehousePageResp.class);
    }


    @PostMapping("/page")
    @Operation(summary = "仓库管理 - [DONE] - [Levin]")
    public IPage<WarehousePageResp> pageList(@RequestBody WarehousePageReq req) {
        return this.warehouseService.page(req.buildPage(), Wraps.<Warehouse>lbQ()
                .eq(Warehouse::getCode, req.getCode())
                .like(Warehouse::getName, req.getName())
                .like(Warehouse::getStatus, req.getStatus())
        ).convert(x -> BeanUtil.toBean(x, WarehousePageResp.class));
    }


    @PostMapping("/create")
    @AccessLog(module = "", description = "仓库管理 - 新增仓库")
    @Operation(summary = "新增仓库 - [DONE] - [Levin]")
    public void create(@Validated @RequestBody WarehouseSaveReq req) {
        this.warehouseService.create(req);
    }

    @PutMapping("/{id}/modify")
    @AccessLog(module = "", description = "仓库管理 - 修改仓库")
    @Operation(summary = "修改仓库 - [DONE] - [Levin]")
    public void modify(@PathVariable Long id, @Validated @RequestBody WarehouseSaveReq req) {
        this.warehouseService.modify(id, req);
    }

    @DeleteMapping("/{id}")
    @AccessLog(module = "", description = "仓库管理 - 删除仓库")
    @Operation(summary = "删除仓库 - [DONE] - [Levin]")
    public void delete(@PathVariable Long id) {
        this.warehouseService.removeById(id);
    }

}




