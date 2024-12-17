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
 * bms_order_receivable
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class BmsOrderReceivable extends SuperEntity<Long> {

    @Schema(description = "订单ID")
    private Long orderId;

    @Schema(description = "付款方公司ID")
    private Long payerId;

    @Schema(description = "付款方公司")
    private String payerName;

    @Schema(description = "收款方公司ID")
    private Long payeeId;

    @Schema(description = "收款方公司")
    private String payeeName;

    @Schema(description = "费用类型ID")
    private Integer feeType;

    @Schema(description = "金额")
    private BigDecimal feeAmount;

    @Schema(description = "币种ID")
    private Integer currencyType;

    @Schema(description = "操作人员ID")
    private Long operatorId;

    @Schema(description = "费用备注")
    private String remark;

    @Schema(description = "计费规则ID")
    private Long ruleId;

    @Schema(description = "订单货物ID")
    private Long skuId;

    @Schema(description = "站点ID")
    private Long stationId;

    @Schema(description = "任务ID")
    private Long taskId;

    @Schema(description = "数据来源")
    private Integer sourceId;

    @Schema(description = "是否启用 0=未锁定 1=锁定(逻辑删除用)")
    private Boolean locked;

}