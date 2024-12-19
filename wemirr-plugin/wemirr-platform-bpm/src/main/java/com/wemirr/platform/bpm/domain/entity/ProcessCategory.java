
package com.wemirr.platform.bpm.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 流程类别(ProcessCategory)
 *
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@TableName("wp_process_category")
public class ProcessCategory extends SuperEntity<Long> {

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "类别编码(唯一)")
    private String code;

    @Schema(description = "类别名称")
    private String name;

    @Schema(description = "类别状态:0-禁用,1-启用;默认1")
    private Integer status;

    @Schema(description = "类别描述")
    private String description;

    @Schema(description = "ICON")
    private String icon;
}
