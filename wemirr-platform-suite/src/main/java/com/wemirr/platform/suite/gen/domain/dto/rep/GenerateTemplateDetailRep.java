package com.wemirr.platform.suite.gen.domain.dto.rep;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author xiao1
 * @since 2024-12
 */
@Data
public class GenerateTemplateDetailRep {

    /*
        id
     */
    private Long id;


    /**
     * 模板名称
     */
    @Schema(description = "模板名称")
    private String name;

    /**
     * 文件路径模板
     * 用于文件生成路径
     * wp/src/main/java/${packagePath}/${moduleName}/controller/${ClassName}Controller.java
     */
    @Schema(description = "模板路径")
    private String generatePath;

    /**
     * 模板描述
     */
    @Schema(description = "模板描述")
    private String desc;

    /**
     * 模板代码
     */
    @Schema(description = "模板代码")
    private String code;
}
