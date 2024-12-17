package com.wemirr.platform.wms.inbound.mapper;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.wms.inbound.domain.dto.ReceiptQtyDTO;
import com.wemirr.platform.wms.inbound.domain.entity.InventoryReceiptItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 入库单明细行 Mapper 接口
 * </p>
 *
 * @author ddCat
 * @since 2024-06-27
 */
@Repository
public interface InventoryReceiptItemMapper extends SuperMapper<InventoryReceiptItem> {

    /**
     * 根据收货计划关联入库单查询数量
     * @param list 入库明细行ids
     * @return 结果
     */
    List<ReceiptQtyDTO> selectReceiptQty(@Param("list") List<Long> list);


    /**
     * 根据收货计划关联入库单查询数量
     * @param planId 收货计划ID
     * @return 结果
     */
    List<ReceiptQtyDTO> selectReceiptQtyByPlanId(Long planId);

}
