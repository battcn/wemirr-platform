package com.wemirr.framework.log.diff.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 字段变更记录，记录单个字段的变更详情
 *
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "字段变更记录")
public class FieldChange {

    /**
     * 字段名称
     */
    @Schema(description = "字段名称")
    private String name;

    /**
     * 字段显示名称
     */
    @Schema(description = "字段显示名称")
    private String label;

    /**
     * 原始值
     */
    @Schema(description = "原始值")
    private Object oldVal;

    /**
     * 新值
     */
    @Schema(description = "新值")
    private Object newVal;

}
