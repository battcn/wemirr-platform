package com.wemirr.platform.wms.stock.mapper;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.wms.stock.domain.entity.Stock;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 库存余额 Mapper 接口
 * </p>
 *
 * @author ddCat
 * @since 2024-07-02
 */
@Repository
public interface StockMapper extends SuperMapper<Stock> {

}
