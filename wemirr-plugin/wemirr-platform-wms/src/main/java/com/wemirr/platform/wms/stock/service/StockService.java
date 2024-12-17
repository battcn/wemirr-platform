package com.wemirr.platform.wms.stock.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.wms.stock.domain.entity.Stock;
import com.wemirr.platform.wms.stock.domain.req.StockSaveReq;
import com.wemirr.platform.wms.stock.domain.resp.StockPageResp;

import java.util.List;

/**
 * <p>
 * 库存余额 服务类
 * </p>
 *
 * @author ddCat
 * @since 2024-07-02
 */
public interface StockService extends SuperService<Stock> {

    /**
     * 批量保存库存余额，并生成库存余额变动记录
     *
     * @param stocks 库存余额
     */
    void saveStocks(List<StockSaveReq> stocks);

    IPage<StockPageResp> page(Page<Stock> page, Wrapper<Stock> queryWrapper);
}
