package com.wemirr.platform.wms.outbound.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * <p>
 * 出库单行项
 * </p>
 *
 * @author ddCat
 * @since 2024-07-11
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wms_outbound_plan_item")
@Schema(name = "OutboundPlanItem", description = "出库单行项")
@EqualsAndHashCode(callSuper = true)
public class OutboundPlanItem extends SuperEntity<Long> {

    @Schema(description = "出库单ID")
    private Long planId;

    @Schema(description = "出库单行号")
    private Integer planItemNum;

    @Schema(description = "物料ID")
    private Long materialId;

    @Schema(description = "计划出库数量")
    private BigDecimal qty;

    @Schema(description = "出库单位")
    private String unit;

}
