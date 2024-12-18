package com.wemirr.platform.wms.inbound.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.wms.inbound.domain.entity.InventoryReceipt;
import com.wemirr.platform.wms.inbound.domain.req.InventoryReceiptPageReq;
import com.wemirr.platform.wms.inbound.domain.req.InventoryReceiptSubmitReq;
import com.wemirr.platform.wms.inbound.domain.resp.InventoryReceiptDetailResp;
import com.wemirr.platform.wms.inbound.domain.resp.InventoryReceiptPageResp;
import com.wemirr.platform.wms.inbound.service.InventoryReceiptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 入库单 前端控制器
 * </p>
 *
 * @author ddCat
 * @since 2024-06-24
 */
@RestController
@AllArgsConstructor
@RequestMapping("/inbound/inventory-receipts")
@Tag(name = "入库单", description = "入库单")
public class InventoryReceiptController {

    private final InventoryReceiptService inventoryReceiptService;

    @Operation(summary = "查询可用", description = "查询可用数据")
    @PostMapping("/ids")
    public List<InventoryReceiptPageResp> list(@RequestBody List<Long> ids) {
        return BeanUtilPlus.toBeans(this.inventoryReceiptService.list(Wraps.<InventoryReceipt>lbQ().in(InventoryReceipt::getId, ids)), InventoryReceiptPageResp.class);
    }

    @PostMapping("/page")
    @Operation(summary = "分页查询 - [DONE] - [Levin]")
    public IPage<InventoryReceiptPageResp> pageList(@RequestBody InventoryReceiptPageReq req) {
        return this.inventoryReceiptService.page(req.buildPage(), Wraps.<InventoryReceipt>lbQ()
                .like(InventoryReceipt::getReceiptNum, req.getReceiptNum())
                .eq(InventoryReceipt::getWarehouseId, req.getWarehouseId())
                .eq(InventoryReceipt::getPlanId, req.getPlanId())
                .eq(InventoryReceipt::getSupplierId, req.getSupplierId())
                .eq(InventoryReceipt::getStatus, req.getStatus())
        ).convert(x -> BeanUtil.toBean(x, InventoryReceiptPageResp.class));
    }

    @GetMapping("/{id}/detail")
    @Operation(summary = "详情查询 - [DONE] - [Levin]")
    public InventoryReceiptDetailResp detail(@PathVariable("id") Long id) {
        return inventoryReceiptService.detail(id);
    }

    @PutMapping("/{id}/modify")
    @AccessLog(module = "", description = "入库单管理 - 修改入库单")
    @Operation(summary = "修改入库单 - [DONE] - [Levin]")
    public void modify(@PathVariable("id") Long id, @RequestBody InventoryReceiptSubmitReq req) {
        inventoryReceiptService.submit(id, req);
    }

    @DeleteMapping("/{id}")
    @AccessLog(module = "", description = "入库单管理 - 删除入库单")
    @Operation(summary = "删除入库单 - [DONE] - [Levin]")
    public void delete(@PathVariable Long id) {
        inventoryReceiptService.removeInventoryReceipt(id);
    }

}
