package com.wemirr.platform.wms.inbound.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 收货计划行保存请求对象
 *
 * @author ddCat
 * @since 2024-06-24
 */
@Data
@Schema(name = "ReceivingPlanItemSaveReq", description = "收货计划行保存请求对象")
public class ReceivingPlanItemSaveReq {

    @Schema(description = "收货计划行ID")
    private Long id;

    @Schema(description = "收货计划Id")
    private Long receivingPlanId;

    @Schema(description = "收货计划行号")
    private String planItemNum;

    @Schema(description = "到货通知单行号")
    private String receivingNoticeItemNum;

    @Schema(description = "物料ID")
    private Long materialId;

    @Schema(description = "物料编号")
    private String materialCode;

    @Schema(description = "物料名称")
    private String materialName;

    @Schema(description = "生产日期")
    private LocalDate productionDate;

    @Schema(description = "失效日期")
    private LocalDate expiryDate;

    @Schema(description = "批次号")
    private String batchNum;

    @Schema(description = "单位")
    private String unit;

    @Schema(description = "数量")
    private BigDecimal qty;

    @Schema(description = "单位价格")
    private BigDecimal unitPrice;
}
