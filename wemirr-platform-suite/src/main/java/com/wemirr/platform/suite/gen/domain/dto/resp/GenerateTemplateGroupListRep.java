package com.wemirr.platform.suite.gen.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author xiao1
 * @since 2024-12
 */
@Data
@Schema(description = "模板组列表响应实体")
public class GenerateTemplateGroupListRep {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "模板组名称")
    private String name;
}