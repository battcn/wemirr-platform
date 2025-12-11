package com.wemirr.platform.suite.gen.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author xiao1
 * @since 2024-12
 */
@Data
@Schema(description = "模板详情响应实体")
public class GenerateTemplateDetailResp {


    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "模板名称")
    private String name;

    @Schema(description = "文件生成路径模板")
    private String generatePath;

    @Schema(description = "模板描述")
    private String description;

    @Schema(description = "模板代码内容")
    private String code;
}
