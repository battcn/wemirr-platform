package com.wemirr.platform.wms.basic.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.wms.basic.domain.entity.Location;
import com.wemirr.platform.wms.basic.domain.req.LocationPageReq;
import com.wemirr.platform.wms.basic.domain.req.LocationSaveReq;
import com.wemirr.platform.wms.basic.domain.resp.LocationPageResp;
import com.wemirr.platform.wms.basic.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 储位 前端控制器
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/locations")
@Tag(name = "储位管理", description = "储位管理")
public class LocationController {

    private final LocationService locationService;

    @Operation(summary = "查询可用", description = "查询可用数据")
    @PostMapping("/ids")
    public List<LocationPageResp> list(@RequestBody List<Long> ids) {
        return BeanUtilPlus.toBeans(this.locationService.list(Wraps.<Location>lbQ().in(Location::getId, ids)), LocationPageResp.class);
    }

    @PostMapping("/page")
    @Operation(summary = "分页查询 - [DONE] - [Levin]")
    public IPage<LocationPageResp> pageList(@RequestBody LocationPageReq req) {
        return this.locationService.page(req.buildPage(), Wraps.<Location>lbQ()
                        .like(Location::getCode, req.getCode())
                        .like(Location::getName, req.getName())
                        .eq(Location::getAisleId, req.getAisleId())
                        .eq(Location::getWarehouseId, req.getWarehouseId())
                        .eq(Location::getStorageAreaId, req.getStorageAreaId())
                        .eq(Location::getSpecId, req.getSpecId()))
                .convert(x -> BeanUtil.toBean(x, LocationPageResp.class));
    }

    @PostMapping("/create")
    @AccessLog(module = "", description = "储位管理 - 新增储位")
    @Operation(summary = "新增储位 - [DONE] - [Levin]")
    public void create(@RequestBody LocationSaveReq req) {
        locationService.create(req);
    }

    @PutMapping("/{id}/modify")
    @AccessLog(module = "", description = "储位管理 - 修改储位")
    @Operation(summary = "修改储位 - [DONE] - [Levin]")
    public void modify(@PathVariable("id") Long id, @RequestBody LocationSaveReq req) {
        locationService.modify(id, req);
    }

    @DeleteMapping("/{id}")
    @AccessLog(module = "", description = "储位管理 - 删除储位")
    @Operation(summary = "删除储位 - [DONE] - [Levin]")
    public void delete(@PathVariable Long id) {
        locationService.removeById(id);
    }

}




