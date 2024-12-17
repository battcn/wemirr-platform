package com.wemirr.platform.wms.inbound.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <p>
 * 收货计划明细行
 * </p>
 *
 * @author ddCat
 * @since 2024-06-24
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wms_receiving_plan_item")
@Schema(name = "ReceivingPlanItem", description = "收货计划明细行")
@EqualsAndHashCode(callSuper = true)
public class ReceivingPlanItem extends SuperEntity<Long> {

    @Schema(description = "收货计划Id")
    private Long receivingPlanId;

    @Schema(description = "收货计划行号")
    private Integer planItemNum;

    @Schema(description = "到货通知单行号")
    private String receivingNoticeItemNum;

    @Schema(description ="物料ID")
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
