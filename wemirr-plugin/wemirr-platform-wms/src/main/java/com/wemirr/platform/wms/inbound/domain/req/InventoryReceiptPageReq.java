package com.wemirr.platform.wms.inbound.domain.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 入库单分页查询请求数据
 * @author ddCat
 * @since 2024-06-27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "InventoryReceiptPageReq", description = "入库单分页查询请求数据")
public class InventoryReceiptPageReq extends PageRequest {

    @Schema(description = "入库单编号")
    private String receiptNum;

    @Schema(description = "仓库ID")
    private Long warehouseId;

    @Schema(description = "供应商ID")
    private Long supplierId;

    @Schema(description = "关联的收货计划ID")
    private Long planId;

    @Schema(description = "状态")
    private String status;

}
