package com.wemirr.platform.tms.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * balance_option
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RuleOptionResp implements Serializable {

    @Schema(description = "ID")
    private Integer id;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "表名")
    private String tableName;

    @Schema(description = "字段名")
    private String colName;

    @Schema(description = "取值类型:0-输入框,1-枚举,2-是否")
    private Integer type;

    @Schema(description = "下拉选择类型")
    private Integer selectType;

    @Schema(description = "是否作为条件可选项")
    private boolean isCondition;

    @Schema(description = "是否作为表达式可选项")
    private boolean isSolution;

    @Schema(description = "可选判断符")
    private List<RuleSymbolResp> symbols;

    @Schema(description = "可选下拉值")
    private List<RuleSymbolResp> options;
}
