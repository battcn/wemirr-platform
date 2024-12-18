package com.wemirr.platform.wms.basic.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.wms.basic.domain.entity.Dock;
import com.wemirr.platform.wms.basic.domain.req.DockPageReq;
import com.wemirr.platform.wms.basic.domain.req.DockSaveReq;
import com.wemirr.platform.wms.basic.domain.resp.DockPageResp;
import com.wemirr.platform.wms.basic.service.DockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 月台管理 前端控制器
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
@RestController
@AllArgsConstructor
@RequestMapping("/docks")
@Tag(name = "月台管理", description = "月台管理")
public class DockController {

    private final DockService dockService;

    @Operation(summary = "查询可用", description = "查询可用数据")
    @PostMapping("/ids")
    public List<Dock> list(@RequestBody List<Long> ids) {
        return BeanUtilPlus.toBeans(this.dockService.list(Wraps.<Dock>lbQ().in(Dock::getId, ids)), Dock.class);
    }

    @PostMapping("/page")
    @Operation(summary = "分页查询 - [DONE] - [Levin]")
    public IPage<DockPageResp> pageList(@RequestBody DockPageReq req) {
        return this.dockService.page(req.buildPage(), Wraps.<Dock>lbQ()
                        .like(Dock::getCode, req.getCode())
                        .like(Dock::getName, req.getName())
                        .eq(Dock::getStatus, req.getStatus()))
                .convert(x -> BeanUtil.toBean(x, DockPageResp.class));
    }

    @PostMapping("/create")
    @AccessLog(module = "", description = "容器规格管理 - 新增月台")
    @Operation(summary = "新增月台 - [DONE] - [Levin]")
    public void create(@Validated @RequestBody DockSaveReq req) {
        dockService.create(req);
    }

    @PutMapping("/{id}/modify")
    @AccessLog(module = "", description = "容器规格管理 - 修改月台")
    @Operation(summary = "修改月台 - [DONE] - [Levin]")
    public void modify(@PathVariable("id") Long id, @Validated @RequestBody DockSaveReq req) {
        dockService.modify(id, req);
    }

    @DeleteMapping("/{id}")
    @AccessLog(module = "", description = "容器规格管理 - 删除月台")
    @Operation(summary = "删除月台 - [DONE] - [Levin]")
    public void delete(@PathVariable Long id) {
        dockService.removeById(id);

    }

}




