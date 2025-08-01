
package com.wemirr.platform.flow.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * 流程类别分页查询Response
 *
 * @author Levin
 */
@Data
@Schema(name = "ProcessCategoryPageResp")
public class ProcessCategoryPageResp {


    @Schema(description = "类别id")
    private String id;

    @Schema(description = "类别编码(唯一)")
    private String code;

    @Schema(description = "类别名称")
    private String name;

    @Schema(description = "类别状态:0-禁用,1-启用。默认0")
    private Integer status;

    @Schema(description = "类别logo")
    private String icon;

    @Schema(description = "类别描述")
    private String description;

    @Schema(description = "创建人姓名")
    private String createdName;

    @Schema(description = "创建时间")
    private Instant createdTime;
}
