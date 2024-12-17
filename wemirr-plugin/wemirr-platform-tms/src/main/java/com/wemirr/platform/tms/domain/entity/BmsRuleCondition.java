package com.wemirr.platform.tms.domain.entity;

import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * bms_rule_condition
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class BmsRuleCondition extends SuperEntity<Long> {

    @Schema(description = "规则id")
    private Long ruleId;

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

    @Schema(description = "是否启用 0=未锁定 1=锁定(逻辑删除用)")
    private Boolean locked;

}