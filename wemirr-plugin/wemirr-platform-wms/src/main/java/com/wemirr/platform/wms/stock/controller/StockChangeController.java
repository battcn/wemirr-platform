package com.wemirr.platform.wms.stock.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.wms.stock.domain.entity.StockChange;
import com.wemirr.platform.wms.stock.domain.req.StockChangePageReq;
import com.wemirr.platform.wms.stock.domain.resp.StockChangePageResp;
import com.wemirr.platform.wms.stock.service.StockChangeService;
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
 * 库存余额变动 前端控制器
 * </p>
 *
 * @author ddCat
 * @since 2024-07-08
 */
@RestController
@AllArgsConstructor
@RequestMapping("/stock/stock-changes")
@Tag(name = "库存调整", description = "库存余额变动")
public class StockChangeController {

    private final StockChangeService stockChangeService;

    @Operation(summary = "查询可用", description = "查询可用数据")
    @PostMapping("/ids")
    public List<StockChangePageResp> list(@RequestBody List<Long> ids) {
        return BeanUtilPlus.toBeans(this.stockChangeService.list(Wraps.<StockChange>lbQ().in(StockChange::getId, ids)), StockChangePageResp.class);
    }

    @PostMapping("/page")
    @Operation(summary = "分页查询 - [DONE] - [Levin]")
    public IPage<StockChangePageResp> pageList(@RequestBody StockChangePageReq req) {
        return this.stockChangeService.page(req.buildPage(), Wraps.<StockChange>lbQ()
                .eq(StockChange::getStockId, req.getStockId())
                .eq(StockChange::getWarehouseId, req.getWarehouseId())
                .eq(StockChange::getLocationId, req.getLocationId())
                .eq(StockChange::getBatchNum, req.getBatchNum())
                .eq(StockChange::getChangeType, req.getChangeType())
                .eq(StockChange::getLevel, req.getLevel())
                .eq(StockChange::getProductionDate, req.getProductionDate())
                .eq(StockChange::getExpiryDate, req.getExpiryDate())
        ).convert(x -> BeanUtil.toBean(x, StockChangePageResp.class));
    }

}
