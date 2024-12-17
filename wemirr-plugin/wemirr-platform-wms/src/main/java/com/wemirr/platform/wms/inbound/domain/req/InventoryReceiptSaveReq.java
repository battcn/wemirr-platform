package com.wemirr.platform.wms.inbound.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 入库单保存请求数据
 *
 * @author ddCat
 * @since 2024-06-27
 */
@Data
@Schema(name = "InventoryReceiptSaveReq", description = "入库单保存请求数据")
public class InventoryReceiptSaveReq {

    @Schema(description = "关联的收货计划ID")
    private Long planId;

    @Schema(description = "关联的收货计划编号")
    private String planNum;

    @Schema(description = "仓库ID")
    private Long warehouseId;

    @Schema(description = "供应商ID")
    private Long supplierId;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "入库单明细行保存请求数据")
    private List<InventoryReceiptItemSaveReq> items;
}
