package com.wemirr.platform.tms.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RuleSymbolResp implements Serializable {

    @Schema(description = "ID")
    private Integer id;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "符号")
    private String symbol;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "类型：1-数字,2-运算符,3-订单字段,4-判断符")
    private Integer type;

    @Schema(description = "0-隐藏，1-显示")
    private Integer isShow;
}
