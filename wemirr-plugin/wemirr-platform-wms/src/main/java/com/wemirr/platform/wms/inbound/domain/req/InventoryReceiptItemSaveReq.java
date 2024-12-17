package com.wemirr.platform.wms.inbound.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 入库单明细行保存请求数据
 *
 * @author ddCat
 * @since 2024-06-27
 */
@Data
@Schema(name = "InventoryReceiptItemSaveReq", description = "入库单明细行保存请求数据")
public class InventoryReceiptItemSaveReq {

    private Long id;

    @Schema(description = "入库单ID")
    private Long receiptId;

    @Schema(description = "关联的收货计划行项ID")
    private Long planItemId;

    @Schema(description = "物料ID")
    private Long materialId;

    @Schema(description = "物料编号")
    private String materialCode;

    @Schema(description = "物料名称")
    private String materialName;

    @Schema(description = "储位ID")
    private Long locationId;

    @Schema(description = "批次号")
    private String batchNum;

    @Schema(description = "收货数量")
    private BigDecimal receivingQty;

    @Schema(description = "收货单位")
    private String receivingUnit;

    @Schema(description = "入库时间")
    private String receiptTime;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "失效日期")
    private LocalDate expiryDate;

    @Schema(description = "生产日期")
    private LocalDate productionDate;
}
