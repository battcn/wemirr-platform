package com.wemirr.platform.wms.basic.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.wms.basic.domain.entity.LocationSpec;
import com.wemirr.platform.wms.basic.domain.req.LocationSpecPageReq;
import com.wemirr.platform.wms.basic.domain.req.LocationSpecSaveReq;
import com.wemirr.platform.wms.basic.domain.resp.LocationSpecPageResp;
import com.wemirr.platform.wms.basic.service.LocationSpecService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 储位规格 前端控制器
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
@RestController
@AllArgsConstructor
@RequestMapping("/location-specs")
@Tag(name = "储位规格", description = "储位规格")
public class LocationSpecController {

    private final LocationSpecService locationSpecService;

    @Operation(summary = "查询可用 - [DONE]", description = "查询可用数据")
    @PostMapping("/ids")
    public List<LocationSpecPageResp> list(@RequestBody List<Long> ids) {
        return BeanUtilPlus.toBeans(this.locationSpecService.list(Wraps.<LocationSpec>lbQ().in(LocationSpec::getId, ids)), LocationSpecPageResp.class);
    }

    @PostMapping("/page")
    @Operation(summary = "分页查询 - [DONE] - [Levin]")
    public IPage<LocationSpecPageResp> pageList(@RequestBody LocationSpecPageReq req) {
        return locationSpecService.page(req.buildPage(), Wraps.<LocationSpec>lbQ()
                .eq(LocationSpec::getCode, req.getSpecCode())
                .eq(LocationSpec::getName, req.getSpecName())
                .eq(LocationSpec::getStatus, req.getStatus())
        ).convert(x -> BeanUtil.toBean(x, LocationSpecPageResp.class));
    }

    @PostMapping("/create")
    @AccessLog(module = "", description = "储位规格管理 - 新增储位规格")
    @Operation(summary = "新增储位规格 - [DONE] - [Levin]")
    public void create(@RequestBody LocationSpecSaveReq req) {
        locationSpecService.create(req);
    }

    @PutMapping("/{id}/modify")
    @AccessLog(module = "", description = "储位规格管理 - 修改储位规格")
    @Operation(summary = "修改储位规格 - [DONE] - [Levin]")
    public void modify(@PathVariable("id") Long id, @RequestBody LocationSpecSaveReq req) {
        locationSpecService.modify(id, req);
    }

    @DeleteMapping("/{id}")
    @AccessLog(module = "", description = "储位规格管理 - 删除储位规格")
    @Operation(summary = "删除储位规格 - [DONE] - [Levin]")
    public void delete(@PathVariable Long id) {
        locationSpecService.removeById(id);
    }

}




