package com.wemirr.platform.wms.matedata.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.wms.matedata.domain.entity.Carrier;
import com.wemirr.platform.wms.matedata.domain.req.CarrierPageReq;
import com.wemirr.platform.wms.matedata.domain.req.CarrierSaveReq;
import com.wemirr.platform.wms.matedata.domain.resp.CarrierPageResp;
import com.wemirr.platform.wms.matedata.service.CarrierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 承运商 前端控制器
 * </p>
 *
 * @author ddCat
 * @since 2024-07-14
 */
@RestController
@AllArgsConstructor
@RequestMapping("/metadata/carriers")
@Tag(name = "承运商管理", description = "承运商管理")
public class CarrierController {

    private final CarrierService carrierService;

    @Operation(summary = "查询可用", description = "查询可用数据")
    @PostMapping("/ids")
    public List<CarrierPageResp> list(@RequestBody List<Long> ids) {
        return BeanUtilPlus.toBeans(this.carrierService.list(Wraps.<Carrier>lbQ().in(Carrier::getId, ids)), CarrierPageResp.class);
    }

    @PostMapping("/page")
    @Operation(summary = "分页查询 - [DONE] - [Levin]")
    public IPage<CarrierPageResp> pageList(@RequestBody CarrierPageReq req) {
        return this.carrierService.page(req.buildPage(), Wraps.<Carrier>lbQ().like(Carrier::getCode, req.getCode())
                        .like(Carrier::getName, req.getName())
                        .eq(Carrier::getType, req.getType())
                        .eq(Carrier::getStatus, req.getStatus())
                        .eq(Carrier::getServiceType, req.getServiceType()))
                .convert(x -> BeanUtil.toBean(x, CarrierPageResp.class));
    }

    @PostMapping("/create")
    @AccessLog(module = "", description = "承运商管理 - 新增承运商")
    @Operation(summary = "新增承运商 - [DONE] - [Levin]")
    public void create(@Validated @RequestBody CarrierSaveReq req) {
        carrierService.create(req);
    }

    @PutMapping("/{id}/modify")
    @AccessLog(module = "", description = "承运商管理 - 修改承运商")
    @Operation(summary = "修改承运商 - [DONE] - [Levin]")
    public void modify(@PathVariable("id") Long id, @Validated @RequestBody CarrierSaveReq req) {
        carrierService.modify(id, req);
    }

    @DeleteMapping("/{id}")
    @AccessLog(module = "", description = "承运商管理 - 删除承运商")
    @Operation(summary = "删除承运商 - [DONE] - [Levin]")
    public void delete(@PathVariable Long id) {
        carrierService.removeById(id);
    }

}
