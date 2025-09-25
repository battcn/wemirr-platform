package com.wemirr.platform.wms.inbound.domain.resp;

import com.wemirr.platform.wms.inbound.domain.enums.InventoryReceiptStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;
import java.util.List;

/**
 * 入库单明细
 *
 * @author ddCat
 * @since 2024-06-27
 */
@Data
@Schema(name = "InventoryReceiptDetailResp", description = "入库单明细")
public class InventoryReceiptDetailResp {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "入库单编号")
    private String receiptNum;

    @Schema(description = "仓库ID")
    private Long warehouseId;

    @Schema(description = "供应商ID")
    private Long supplierId;

    @Schema(description = "关联的收货计划ID")
    private Long planId;

    @Schema(description = "收货计划编号")
    private String planNum;

    @Schema(description = "状态")
    private InventoryReceiptStatus status;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建人")
    private String createName;

    @Schema(description = "创建时间")
    private Instant createTime;

    @Schema(description = "入库单明细")
    private List<InventoryReceiptItemPageResp> items;

}
