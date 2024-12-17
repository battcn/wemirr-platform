package com.wemirr.platform.tms.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Levin
 */
@Data
@Schema(name = "PartPurchaseSaveReq")
public class PartPurchaseSaveReq {

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


}
