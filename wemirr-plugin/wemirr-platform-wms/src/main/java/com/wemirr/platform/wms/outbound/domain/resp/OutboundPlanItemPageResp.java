package com.wemirr.platform.wms.outbound.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

/**
 * 出库单行项分页查询响应数据
 *
 * @author ddCat
 * @since 2024-07-20
 */
@Data
@Schema(name = "OutboundPlanPageResp", description = "出库单行项分页查询响应数据")
public class OutboundPlanItemPageResp {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "出库单ID")
    private Long planId;

    @Schema(description = "出库单行号")
    private Integer planItemNum;

    @Schema(description = "物料ID")
    private Long materialId;

    @Schema(description = "物料编号")
    private String materialCode;

    @Schema(description = "物料名称")
    private String materialName;

    @Schema(description = "计划发货数量")
    private BigDecimal qty;

    @Schema(description = "计划发货单位")
    private String unit;

    @Schema(description = "创建人名称")
    private String createName;

    @Schema(description = "创建时间")
    private Instant createTime;

    @Schema(description = "出库单行项库存余额明细行")
    private List<OutboundPlanItemStockPageResp> stocks;

}
