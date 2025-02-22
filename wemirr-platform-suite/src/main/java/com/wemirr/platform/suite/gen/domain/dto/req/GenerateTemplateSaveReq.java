package com.wemirr.platform.suite.gen.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

/**
 * @author xiao1
 * @date 2024-12
 * @description: 生成模板实体类
 */
@Data
public class GenerateTemplateSaveReq {


    /**
     * 模板名称
     */
    @Schema(description = "模板名称")
    @NotBlank(message = "模板名称不能为空")
    private String name;

    /**
     * 文件路径模板
     * wp/src/main/java/${packagePath}/${moduleName}/controller/${ClassName}Controller.java
     */
    @Schema(description = "模板路径")
    @NotBlank(message = "模板路径不能为空")
    private String generatePath; //用于文件生成路径

    /**
     * 模板描述
     */
    @Schema(description = "模板描述")
    //   @NotBlank(message = "模板描述不能为空")
    private String description;

    /**
     * 模板代码
     */
    @Schema(description = "模板代码")
    @NotBlank(message = "模板代码不能为空")
    private String code;

    /**
     * 关联的模板ids
     */
    @Schema(description = "关联的模板ids")
    private List<Long> templateIds;

    //TODO: 自定义模板映射值

}
