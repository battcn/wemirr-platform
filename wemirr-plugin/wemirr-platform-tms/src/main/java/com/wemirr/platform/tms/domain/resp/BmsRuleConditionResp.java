package com.wemirr.platform.tms.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Levin
 */
@Data
@Schema(name = "BmsRuleConditionResp" )
public class BmsRuleConditionResp {

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

    @Schema(description = "条件表达")
    private String expression;

}