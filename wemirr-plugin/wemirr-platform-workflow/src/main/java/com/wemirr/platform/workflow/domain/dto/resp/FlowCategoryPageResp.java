package com.wemirr.platform.workflow.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * @author battcn
 * @since 2025/8/15
 **/
@Data
public class FlowCategoryPageResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "ICON")
    private String icon;

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "分类状态")
    private Boolean status;

    @Schema(description = "分类描述")
    private String description;

    @Schema(description = "创建人ID")
    private String createBy;

    @Schema(description = "创建人名称")
    private String createName;

    @Schema(description = "创建时间")
    private Instant createTime;


}
