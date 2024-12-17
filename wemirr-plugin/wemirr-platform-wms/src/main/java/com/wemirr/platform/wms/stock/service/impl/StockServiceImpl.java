package com.wemirr.platform.wms.stock.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.platform.wms.basic.domain.entity.Location;
import com.wemirr.platform.wms.basic.mapper.LocationMapper;
import com.wemirr.platform.wms.stock.domain.entity.Stock;
import com.wemirr.platform.wms.stock.domain.entity.StockChange;
import com.wemirr.platform.wms.stock.domain.req.StockSaveReq;
import com.wemirr.platform.wms.stock.domain.resp.StockPageResp;
import com.wemirr.platform.wms.stock.mapper.StockChangeMapper;
import com.wemirr.platform.wms.stock.mapper.StockMapper;
import com.wemirr.platform.wms.stock.service.StockService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 库存余额 服务实现类
 * </p>
 *
 * @author ddCat
 * @since 2024-07-02
 */
@Service
@AllArgsConstructor
public class StockServiceImpl extends SuperServiceImpl<StockMapper, Stock> implements StockService {

    private final StockChangeMapper stockChangeMapper;
    private final LocationMapper locationMapper;

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void saveStocks(List<StockSaveReq> reqs) {
        for (StockSaveReq req : reqs) {
            // 库存余额
            Stock stock = Stock.builder()
                    .batchNum(req.getBatchNum())
                    .expiryDate(req.getExpiryDate())
                    .level(req.getLevel())
                    .locationId(req.getLocationId())
                    .materialId(req.getMaterialId())
                    .productionDate(req.getProductionDate())
                    .availableQty(req.getQty())
                    .qty(req.getQty())
                    .unit(req.getUnit())
                    .unitPrice(req.getUnitPrice())
                    .warehouseId(req.getWarehouseId())
                    .build();
            this.baseMapper.insert(stock);
            // 库存余额变动记录
            StockChange stockChange = StockChange.builder()
                    .stockId(stock.getId())
                    .batchNum(req.getBatchNum())
                    .changeType(req.getChangeType())
                    .changeType(req.getChangeType())
                    .docId(req.getDocId())
                    .docItemId(req.getDocItemId())
                    .expiryDate(req.getExpiryDate())
                    .level(req.getLevel())
                    .warehouseId(req.getWarehouseId())
                    .locationId(req.getLocationId())
                    .materialId(req.getMaterialId())
                    .productionDate(req.getProductionDate())
                    .qty(req.getQty())
                    .unit(req.getUnit())
                    .unitPrice(req.getUnitPrice())
                    .build();
            this.stockChangeMapper.insert(stockChange);
        }
    }

    @Override
    public IPage<StockPageResp> page(Page<Stock> page, Wrapper<Stock> queryWrapper) {
        IPage<StockPageResp> ipage = this.baseMapper.selectPage(page, queryWrapper).convert(x -> BeanUtil.toBean(x, StockPageResp.class));
        List<Long> locationIds = ipage.getRecords().stream().map(StockPageResp::getLocationId).distinct().collect(Collectors.toList());
        Map<Long, Location> locationMap = locationMapper.selectByIds(locationIds).stream().collect(Collectors.toMap(Location::getId, Function.identity()));
        ipage.getRecords().forEach(stock -> {
            Long locationId = stock.getLocationId();
            Location location = locationMap.get(locationId);
            if (location != null) {
                // 设置库区和巷道
                stock.setAreaId(location.getStorageAreaId());
                stock.setAisleId(location.getAisleId());
            }
        });
        return ipage;
    }
}
