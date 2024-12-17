package com.wemirr.platform.wms.stock.mapper;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.wms.stock.domain.entity.StockChange;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 库存余额变动 Mapper 接口
 * </p>
 *
 * @author ddCat
 * @since 2024-07-08
 */
@Repository
public interface StockChangeMapper extends SuperMapper<StockChange> {

}
