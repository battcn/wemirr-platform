package com.wemirr.platform.suite.gen.domain.dto.rep;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * @author xiao1
 * @date 2024-12
 */
@Data
public class GenerateTemplatePageRep {

    @Schema(description = "ID")
    private Long id;

    /**
     * 模板名称
     */
    @Schema(description = "模板名称")
    private String name;

    /**
     * 文件路径模板
     * wp/src/main/java/${packagePath}/${moduleName}/controller/${ClassName}Controller.java
     */
    @Schema(description = "模板路径")
    private String generatePath; //用于文件生成路径

    /**
     * 模板描述
     */
    @Schema(description = "模板描述")
    private String description;

    @Schema(description = "模板内容")
    private String templateContent;

    @Schema(description = "创建人")
    private String createdName;


    @Schema(description = "创建时间")
    private Instant createdTime;
}
