package com.wemirr.platform.suite.gen.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;
import java.util.List;

/**
 * @author xiao1
 * @since 2024-12
 */
@Data
public class GenerateTemplateGroupPageResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "分组名称")
    private String name;

    @Schema(description = "分组描述")
    private String description;

    @Schema(description = "是否作为默认分组")
    private Boolean isDefault;

    @Schema(description = "创建时间")
    private Instant createTime;

    @Schema(description = "模板ID")
    private List<Long> templateIds;
}
