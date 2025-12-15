package com.wemirr.platform.tms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.entity.Dict;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.tms.domain.entity.Driver;
import com.wemirr.platform.tms.domain.req.DriverApprovalReq;
import com.wemirr.platform.tms.domain.req.DriverPageReq;
import com.wemirr.platform.tms.domain.req.DriverSaveReq;
import com.wemirr.platform.tms.domain.resp.DriverPageResp;
import com.wemirr.platform.tms.service.DriverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Levin
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/drivers")
@Tag(name = "司机管理", description = "司机管理")
public class DriverController {

    private final DriverService driverService;

    @GetMapping("/dict-list")
    @Operation(summary = "司机列表 - [Levin] - [DONE]")
    public List<Dict<?>> dictList(Boolean enabled) {
        final List<Driver> list = this.driverService.list(Wraps.<Driver>lbQ().eq(Driver::getEnabled, enabled));
        return list.stream().map(x -> Dict.builder().value(x.getId()).label(x.getRealName()).build()).collect(Collectors.toList());
    }


    @PostMapping("/page")
    @Operation(summary = "分页查询 - [Levin] - [DONE]")
    public IPage<DriverPageResp> pageList(@RequestBody DriverPageReq req) {
        return this.driverService.pageList(req);
    }

    @PostMapping("/create")
    @Operation(summary = "删除司机 - [Levin] - [DONE]")
    public void create(@RequestBody DriverSaveReq req) {
        this.driverService.create(req);
    }

    @PutMapping("/{id}")
    @Operation(summary = "编辑司机 - [Levin] - [DONE]")
    public void edit(@PathVariable Long id, @RequestBody DriverSaveReq req) {
        this.driverService.edit(id, req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除司机 - [Levin] - [DONE]")
    public void remove(@PathVariable Long id) {
        this.driverService.removeById(id);
    }

    @PutMapping("/{id}/approval")
    @Operation(summary = "司机审核 - [Levin] - [DONE]")
    public void approval(@PathVariable Long id, @RequestBody DriverApprovalReq req) {
        this.driverService.approval(id, req);
    }

    @PutMapping("/{id}/enabled/{enabled}")
    @Operation(summary = "启用/禁用 - [Levin] - [DONE]")
    public void enabled(@PathVariable Long id, @PathVariable Boolean enabled) {
        this.driverService.enabled(id, enabled);
    }


}
