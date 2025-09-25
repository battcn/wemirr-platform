package com.wemirr.platform.tms.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * @author Levin
 */
@Data
@Schema(name = "PartPurchasePageResp")
public class PartPurchasePageResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "采购编号")
    private String purchaseNo;

    @Schema(description = "零件名称")
    private String partName;

    @Schema(description = "型号")
    private String model;

    @Schema(description = "数量")
    private Integer quantity;

    @Schema(description = "数量单位")
    private String quantityUnit;

    @Schema(description = "可用数量")
    private Integer availableQuantity;

    @Schema(description = "单价")
    private Integer unitPrice;

    @Schema(description = "小计价格")
    private Integer subtotalPrice;

    @Schema(description = "是否入库")
    private Boolean inStock;

    @Schema(description = "创建人")
    private String createName;

    @Schema(description = "创建时间")
    private Instant createTime;


}
