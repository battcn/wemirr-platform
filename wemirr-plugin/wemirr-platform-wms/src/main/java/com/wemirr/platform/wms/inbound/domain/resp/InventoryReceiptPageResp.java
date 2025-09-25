package com.wemirr.platform.wms.inbound.domain.resp;

import com.wemirr.platform.wms.inbound.domain.enums.InventoryReceiptStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * 入库单分页查询响应数据
 *
 * @author ddCat
 * @since 2024-06-27
 */
@Data
@Schema(name = "InventoryReceiptPageResp", description = "入库单分页查询响应数据")
public class InventoryReceiptPageResp {

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

}
