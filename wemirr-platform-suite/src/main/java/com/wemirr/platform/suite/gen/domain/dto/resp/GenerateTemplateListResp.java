package com.wemirr.platform.suite.gen.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author xiao1
 * @since 2024-12
 */
@Data
@Schema(description = "模板列表响应实体")
public class GenerateTemplateListResp {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "模板名称")
    private String name;
}