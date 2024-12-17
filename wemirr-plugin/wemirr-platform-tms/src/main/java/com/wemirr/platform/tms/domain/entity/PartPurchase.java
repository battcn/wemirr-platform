package com.wemirr.platform.tms.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@TableName(value = "tms_part_purchase")
@Schema(name = "PartPurchase", description = "配件采购")
public class PartPurchase extends SuperEntity<Long> {

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

}
