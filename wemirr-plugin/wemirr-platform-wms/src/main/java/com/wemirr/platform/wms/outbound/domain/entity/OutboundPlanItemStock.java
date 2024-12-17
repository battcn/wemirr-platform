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
 * 出库行项库存余额明细行
 * </p>
 *
 * @author ddCat
 * @since 2024-08-06
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wms_outbound_plan_item_stock")
@Schema(name = "OutboundPlanItemStock", description = "出库行项库存余额明细行")
@EqualsAndHashCode(callSuper = true)
public class OutboundPlanItemStock extends SuperEntity<Long> {

    @Schema(description = "出库单行项ID")
    private Long planItemId;

    @Schema(description = "库存余额ID")
    private Long stockId;

    @Schema(description = "出库明细行数量")
    private BigDecimal qty;

    @Schema(description = "出库明细行单位")
    private String unit;

}
