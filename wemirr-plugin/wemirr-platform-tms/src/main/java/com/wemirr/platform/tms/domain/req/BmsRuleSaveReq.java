package com.wemirr.platform.tms.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Levin
 */
@Data
@Schema(name = "BmsRuleSaveReq" )
public class BmsRuleSaveReq {

    @Schema(description = "规则名称")
    private String ruleName;

    @Schema(description = "项目id")
    private Long projectId;

    @Schema(description = "费用科目id")
    private Integer feeType;

    @Schema(description = "结算表达式")
    private String expression;

    @Schema(description = "费用最小值")
    private BigDecimal min;

    @Schema(description = "费用最大值")
    private BigDecimal max;

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

    @Schema(description = "有效期开始")
    private LocalDate startDate;

    @Schema(description = "有效期截止")
    private LocalDate endDate;

    @Schema(description = "数据来源")
    private Integer sourceId;

    @Schema(description = "是否启用 0=未锁定 1=锁定(逻辑删除用)")
    private Boolean locked;

    @Schema(description = "判断条件")
    private List<BmsRuleConditionSaveReq> conditions;

}