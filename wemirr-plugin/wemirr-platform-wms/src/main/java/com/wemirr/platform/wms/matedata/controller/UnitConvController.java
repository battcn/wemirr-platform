package com.wemirr.platform.wms.matedata.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.wms.matedata.domain.entity.UnitConv;
import com.wemirr.platform.wms.matedata.domain.req.UnitConvPageReq;
import com.wemirr.platform.wms.matedata.domain.req.UnitConvSaveReq;
import com.wemirr.platform.wms.matedata.domain.resp.UnitConvPageResp;
import com.wemirr.platform.wms.matedata.service.UnitConvService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 基本计量单位转换 前端控制器
 * </p>
 *
 * @author ddCat
 * @since 2024-06-24
 */
@RestController
@AllArgsConstructor
@RequestMapping("/metadata/unit-conv")
@Tag(name = "计量单位转换", description = "计量单位转换")
public class UnitConvController {

    private final UnitConvService unitConvService;

    @Operation(summary = "查询可用", description = "查询可用数据")
    @PostMapping("/ids")
    public List<UnitConvPageResp> list(@RequestBody List<Long> ids) {
        return BeanUtilPlus.toBeans(this.unitConvService.list(Wraps.<UnitConv>lbQ().in(UnitConv::getId, ids)), UnitConvPageResp.class);
    }

    @PostMapping("/page")
    @Operation(summary = "分页查询 - [DONE] - [Levin]")
    public IPage<UnitConvPageResp> pageList(@RequestBody UnitConvPageReq req) {
        return this.unitConvService.page(req.buildPage(), Wraps.<UnitConv>lbQ()
                        .eq(UnitConv::getUnitId, req.getUnitId())
                        .eq(UnitConv::getConvUnitId, req.getConvUnitId()))
                .convert(x -> BeanUtil.toBean(x, UnitConvPageResp.class));
    }

    @PostMapping("/create")
    @AccessLog(module = "", description = "基本计量单位转换管理 - 新增基本计量单位转换")
    @Operation(summary = "新增基本计量单位转换 - [DONE] - [Levin]")
    public void create(@Validated @RequestBody UnitConvSaveReq req) {
        unitConvService.create(req);
    }

    @PutMapping("/{id}/modify")
    @AccessLog(module = "", description = "基本计量单位转换管理 - 修改基本计量单位")
    @Operation(summary = "修改基本计量单位转换 - [DONE] - [Levin]")
    public void modify(@PathVariable("id") Long id,@Validated @RequestBody UnitConvSaveReq req) {
        unitConvService.modify(id, req);
    }

    @DeleteMapping("/{id}")
    @AccessLog(module = "", description = "基本计量单位转换管理 - 删除基本计量单位")
    @Operation(summary = "删除基本计量单位转换 - [DONE] - [Levin]")
    public void delete(@PathVariable Long id) {
        unitConvService.removeById(id);
    }

}




