package com.wemirr.platform.tms.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Levin
 */
@Data
@Schema(name = "BmsRuleConditionSaveReq" )
public class BmsRuleConditionSaveReq {

    @Schema(description = "字段id")
    private Integer optionId;

    @Schema(description = "判断符号id")
    private Integer judgmentId;

    @Schema(description = "默认取值")
    private String detail;

    @Schema(description = "判断下限")
    private String checkMin;

    @Schema(description = "判断上限")
    private String checkMax;

    @Schema(description = "结算表达式")
    private String expression;

}