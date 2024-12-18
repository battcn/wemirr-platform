package com.wemirr.platform.wms.outbound.domain.resp;

import com.wemirr.platform.wms.inbound.domain.enums.StockLevelStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 出库行项库存余额明细行分页查询响应数据
 *
 * @author ddCat
 * @since 2024-08-06
 */
@Data
@Schema(name = "OutboundPlanItemStockDetailPageResp", description = "出库行项库存余额明细行分页查询响应数据")
public class OutboundPlanItemStockPageResp {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "出库单行项ID")
    private Long planItemId;

    @Schema(description = "库存余额ID")
    private Long stockId;

    @Schema(description = "出库明细行数量")
    private BigDecimal qty;

    @Schema(description = "出库明细行单位")
    private String unit;

    @Schema(description = "物料编号")
    private String materialCode;

    @Schema(description = "物料名称")
    private String materialName;

    @Schema(description = "批次号")
    private String batchNum;

    @Schema(description = "生产日期")
    private LocalDate productionDate;

    @Schema(description = "失效日期")
    private LocalDate expiryDate;

    @Schema(description = "特殊属性")
    private String attribute;

    @Schema(description = "物料存货等级(良品、待检品、不良品、返工品、报废品、退货品)")
    private StockLevelStatus level;

    @Schema(description = "单价")
    private BigDecimal unitPrice;

}
