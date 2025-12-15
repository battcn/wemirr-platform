package com.wemirr.platform.tms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.entity.Dict;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.tms.domain.entity.Truck;
import com.wemirr.platform.tms.domain.req.TruckApprovalReq;
import com.wemirr.platform.tms.domain.req.TruckPageReq;
import com.wemirr.platform.tms.domain.req.TruckSaveReq;
import com.wemirr.platform.tms.domain.resp.TruckPageResp;
import com.wemirr.platform.tms.service.TruckService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author levin
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/trucks")
@Tag(name = "车辆管理", description = "车辆管理")
public class TruckController {

    private final TruckService truckService;

    @GetMapping("/dict-list")
    @Operation(summary = "车辆列表 - [Levin] - [DONE]")
    public List<Dict<?>> dictList(Boolean enabled) {
        final List<Truck> list = this.truckService.list(Wraps.<Truck>lbQ().eq(Truck::getEnabled, enabled));
        return list.stream().map(x -> Dict.builder().value(x.getId()).label(x.getPlateNo()).build()).collect(Collectors.toList());
    }

    @PostMapping("/page")
    @Operation(summary = "分页查询 - [Levin] - [DONE]")
    public IPage<TruckPageResp> pageList(@RequestBody TruckPageReq req) {
        return this.truckService.pageList(req);
    }

    @PostMapping("/create")
    @Operation(summary = "删除车辆 - [Levin] - [DONE]")
    public void create(@Validated @RequestBody TruckSaveReq req) {
        this.truckService.create(req);
    }

    @PutMapping("/{id}")
    @Operation(summary = "编辑车辆 - [Levin] - [DONE]")
    public void edit(@PathVariable Long id, @Validated @RequestBody TruckSaveReq req) {
        this.truckService.edit(id, req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除车辆 - [Levin] - [DONE]")
    public void remove(@PathVariable Long id) {
        this.truckService.removeById(id);
    }

    @PutMapping("/{id}/approval")
    @Operation(summary = "车辆审核 - [Levin] - [DONE]")
    public void approval(@PathVariable Long id, @RequestBody TruckApprovalReq req) {
        this.truckService.approval(id, req);
    }

    @PutMapping("/{id}/enabled/{enabled}")
    @Operation(summary = "启用/禁用 - [Levin] - [DONE]")
    public void enabled(@PathVariable Long id, @PathVariable Boolean enabled) {
        this.truckService.enabled(id, enabled);
    }


}
