package com.wemirr.platform.wms.outbound.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 出库单行项保存请求数据
 *
 * @author ddCat
 * @since 2024-07-20
 */
@Data
@Schema(name = "OutboundPlanItemSaveReq", description = "出库单行项保存请求数据")
public class OutboundPlanItemSaveReq {

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

    @Schema(description = "出库行项库存余额明细行")
    private List<OutboundPlanItemStockSaveReq> stocks;
}
