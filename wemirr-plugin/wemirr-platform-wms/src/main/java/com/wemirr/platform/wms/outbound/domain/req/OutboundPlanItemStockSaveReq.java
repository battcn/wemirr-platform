package com.wemirr.platform.wms.outbound.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;


/**
 * 出库行项库存余额明细行保存请求数据
 *
 * @author ddCat
 * @since 2024-08-06
 */
@Data
@Schema(name = "OutboundPlanItemStockSaveReq", description = "出库行项库存余额明细行保存请求数据")
public class OutboundPlanItemStockSaveReq {

    @Schema(description = "出库单行项ID")
    private Long planItemId;

    @Schema(description = "库存余额ID")
    private Long stockId;

    @Schema(description = "出库明细行数量")
    private BigDecimal qty;

    @Schema(description = "出库明细行单位")
    private String unit;

}
