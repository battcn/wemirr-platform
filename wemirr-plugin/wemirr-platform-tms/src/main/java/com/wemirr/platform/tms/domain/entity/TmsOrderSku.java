package com.wemirr.platform.tms.domain.entity;

import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * tms_order_sku
 * @author Levin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class TmsOrderSku extends SuperEntity<Long> {

    @Schema(description = "订单ID")
    private Long orderId;

    @Schema(description = "货物规格id")
    private Long skuMasterId;

    @Schema(description = "货号")
    private String itemNo;

    @Schema(description = "箱号")
    private String boxNo;

    @Schema(description = "货物信息")
    private String cargoDesc;

    @Schema(description = "长")
    private BigDecimal al;

    @Schema(description = "宽")
    private BigDecimal aw;

    @Schema(description = "高")
    private BigDecimal ah;

    @Schema(description = "数量")
    private Integer quantity;

    @Schema(description = "毛重")
    private BigDecimal weight;

    @Schema(description = "体积")
    private BigDecimal volume;

    @Schema(description = "单位")
    private Integer qtyUnit;

    @Schema(description = "货物备注")
    private String remark;

    @Schema(description = "确认数量")
    private Integer checkQuantity;

    @Schema(description = "确认重量")
    private BigDecimal checkWeight;

    @Schema(description = "确认体积")
    private BigDecimal checkVolume;

    @Schema(description = "数据来源")
    private Integer sourceId;

}