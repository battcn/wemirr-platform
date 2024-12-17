package com.wemirr.platform.tms.domain.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * @author Levin
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "BmsRulePageReq" )
public class BmsRulePageReq extends PageRequest {

    @Schema(description = "规则名称")
    private String ruleName;

    @Schema(description = "项目id")
    private Long projectId;

    @Schema(description = "费用科目id")
    private Integer feeType;

    @Schema(description = "结算表达式")
    private String expression;

    @Schema(description = "类型:1-应收,0-应付")
    private Integer paymentType;

    @Schema(description = "付款方公司ID")
    private Long payerId;

    @Schema(description = "收款方公司ID")
    private Long payeeId;

    @Schema(description = "收款司机ID")
    private Long payeeDriverId;

    @Schema(description = "有效期开始")
    private LocalDate startDate;

    @Schema(description = "有效期截止")
    private LocalDate endDate;

    @Schema(description = "是否启用 0=未锁定 1=锁定(逻辑删除用)")
    private Boolean locked;

}