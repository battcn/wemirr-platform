package com.wemirr.platform.wms.matedata.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.wms.matedata.domain.entity.Unit;
import com.wemirr.platform.wms.matedata.domain.req.UnitPageReq;
import com.wemirr.platform.wms.matedata.domain.req.UnitSaveReq;
import com.wemirr.platform.wms.matedata.domain.resp.UnitPageResp;
import com.wemirr.platform.wms.matedata.service.UnitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 基本计量单位 前端控制器
 * </p>
 *
 * @author ddCat
 * @since 2024-06-24
 */
@RestController
@AllArgsConstructor
@RequestMapping("/metadata/units")
@Tag(name = "计量单位", description = "计量单位")
public class UnitController {

    private final UnitService unitService;

    @Operation(summary = "查询可用", description = "查询可用数据")
    @PostMapping("/ids")
    public List<UnitPageResp> list(@RequestBody List<Long> ids) {
        return BeanUtilPlus.toBeans(this.unitService.list(Wraps.<Unit>lbQ().in(Unit::getId, ids)), UnitPageResp.class);
    }

    @PostMapping("/page")
    @Operation(summary = "分页查询 - [DONE] - [Levin]")
    public IPage<UnitPageResp> pageList(@RequestBody UnitPageReq req) {
        return this.unitService.page(req.buildPage(), Wraps.<Unit>lbQ().like(Unit::getName, req.getName()).like(Unit::getNameEn, req.getNameEn())
                .like(Unit::getSymbol, req.getSymbol()).eq(Unit::getStatus, req.getStatus())
        ).convert(x -> BeanUtil.toBean(x, UnitPageResp.class));
    }

    @PostMapping("/create")
    @AccessLog(module = "", description = "基本计量单位管理 - 新增基本计量单位")
    @Operation(summary = "新增基本计量单位 - [DONE] - [Levin]")
    public void create(@Validated @RequestBody UnitSaveReq req) {
        unitService.create(req);
    }

    @PutMapping("/{id}/modify")
    @AccessLog(module = "", description = "基本计量单位管理 - 修改基本计量单位")
    @Operation(summary = "修改基本计量单位 - [DONE] - [Levin]")
    public void modify(@PathVariable("id") Long id, @Validated @RequestBody UnitSaveReq req) {
        unitService.modify(id, req);
    }

    @DeleteMapping("/{id}")
    @AccessLog(module = "", description = "基本计量单位管理 - 删除基本计量单位")
    @Operation(summary = "删除基本计量单位 - [DONE] - [Levin]")
    public void removeById(@PathVariable Long id) {
        unitService.removeById(id);
    }

}




