package com.wemirr.platform.wms.matedata.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.wms.matedata.domain.entity.Supplier;
import com.wemirr.platform.wms.matedata.domain.req.SupplierPageReq;
import com.wemirr.platform.wms.matedata.domain.req.SupplierSaveReq;
import com.wemirr.platform.wms.matedata.domain.resp.SupplierPageResp;
import com.wemirr.platform.wms.matedata.service.SupplierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 供应商 前端控制器
 * </p>
 *
 * @author ddCat
 * @since 2024-07-01
 */
@RestController
@AllArgsConstructor
@RequestMapping("/metadata/suppliers")
@Tag(name = "供应商", description = "供应商")
public class SupplierController {

    private final SupplierService supplierService;

    @Operation(summary = "查询可用", description = "查询可用数据")
    @PostMapping("/ids")
    public List<SupplierPageResp> list(@RequestBody List<Long> ids) {
        return BeanUtilPlus.toBeans(this.supplierService.list(Wraps.<Supplier>lbQ().in(Supplier::getId, ids)), SupplierPageResp.class);
    }


    @PostMapping("/page")
    @Operation(summary = "分页查询 - [DONE] - [Levin]")
    public IPage<SupplierPageResp> pageList(SupplierPageReq req) {
        return this.supplierService.page(req.buildPage(), Wraps.<Supplier>lbQ()
                .like(Supplier::getCode, req.getCode())
                .like(Supplier::getName, req.getName())
                .like(Supplier::getContactName, req.getContactName())
                .like(Supplier::getCreditCode, req.getCreditCode())
        ).convert(x -> BeanUtil.toBean(x, SupplierPageResp.class));
    }

    @PostMapping("/create")
    @AccessLog(module = "", description = "供应商管理 - 新增供应商")
    @Operation(summary = "新增供应商 - [DONE] - [Levin]")
    public void create(@Validated @RequestBody SupplierSaveReq req) {
        supplierService.create(req);
    }

    @PutMapping("/{id}/modify")
    @AccessLog(module = "", description = "供应商管理 - 修改供应商")
    @Operation(summary = "修改供应商 - [DONE] - [Levin]")
    public void modify(@PathVariable("id") Long id, @Validated @RequestBody SupplierSaveReq req) {
        supplierService.modify(id, req);
    }

    @DeleteMapping("/{id}")
    @AccessLog(module = "", description = "供应商管理 - 删除供应商")
    @Operation(summary = "删除供应商 - [DONE] - [Levin]")
    public void delete(@PathVariable Long id) {
        supplierService.removeById(id);
    }

}




