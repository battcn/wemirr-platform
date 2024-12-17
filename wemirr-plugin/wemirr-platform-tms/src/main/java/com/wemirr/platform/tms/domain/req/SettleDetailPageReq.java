package com.wemirr.platform.tms.domain.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * settle_page
 * @author Levin
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "SettleDetailPageReq")
public class SettleDetailPageReq extends PageRequest {

    @Schema(description = "订单ID")
    private Long orderId;

    @Schema(description = "项目ID")
    private Long projectId;

    @Schema(description = "类型:1-应收,0-应付")
    private Integer paymentType;

    @Schema(description = "付款方公司ID")
    private Long payerId;

    @Schema(description = "付款方公司")
    private String payerName;

    @Schema(description = "收款方公司ID")
    private Long payeeId;

    @Schema(description = "收款方公司")
    private String payeeName;

    @Schema(description = "收款司机ID")
    private Long payeeDriverId;

    @Schema(description = "收款司机")
    private String payeeDriverName;

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

}