package com.wemirr.platform.wms.stock.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.wms.stock.domain.entity.Stock;
import com.wemirr.platform.wms.stock.domain.req.StockPageReq;
import com.wemirr.platform.wms.stock.domain.resp.StockPageResp;
import com.wemirr.platform.wms.stock.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 库存余额 前端控制器
 * </p>
 *
 * @author ddCat
 * @since 2024-07-02
 */
@RestController
@AllArgsConstructor
@RequestMapping("/stock/stocks")
@Tag(name = "库存余额", description = "库存余额")
public class StockController {

    private final StockService stockService;

    @Operation(summary = "查询可用", description = "查询可用数据")
    @PostMapping("/ids")
    public List<StockPageResp> list(@RequestBody List<Long> ids) {
        return BeanUtilPlus.toBeans(this.stockService.list(Wraps.<Stock>lbQ().in(Stock::getId, ids)), StockPageResp.class);
    }

    @PostMapping("/page")
    @Operation(summary = "分页查询 - [DONE] - [Levin]")
    public IPage<StockPageResp> pageList(@RequestBody StockPageReq req) {
        return this.stockService.page(req.buildPage(), Wraps.<Stock>lbQ()
                .eq(Stock::getBatchNum, req.getBatchNum())
                .eq(Stock::getWarehouseId, req.getWarehouseId())
                .eq(Stock::getLocationId, req.getLocationId())
                .eq(Stock::getLevel, req.getLevel())
                .eq(Stock::getProductionDate, req.getProductionDate())
                .eq(Stock::getExpiryDate, req.getExpiryDate())
        );
    }

}
