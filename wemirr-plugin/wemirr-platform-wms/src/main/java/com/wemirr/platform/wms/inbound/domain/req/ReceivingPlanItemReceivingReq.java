package com.wemirr.platform.wms.inbound.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Levin
 */
@Data
public class ReceivingPlanItemReceivingReq {

    @NotNull(message = "计划明细ID不能为空")
    @Schema(description = "收货计划行ID")
    private Long itemId;

    @NotNull(message = "收货数量不能为空")
    @Schema(description = "数量")
    private BigDecimal qty;
}
