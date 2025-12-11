package com.wemirr.platform.suite.gen.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author xiao1
 * @since 2024-12
 */
@Data
public class GenerateTableDetailResp {
    @Schema(description = "表ID")
    private Long id;

    @Schema(description = "表名称")
    private String name;

    @Schema(description = "表描述")
    private String comment;

    @Schema(description = "实体类名称(首字母大写)")
    private String className;

    @Schema(description = "生成包路径")
    private String packageName;

    @Schema(description = "生成模块名")
    private String moduleName;

    @Schema(description = "作者")
    private String author;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "业务名称(英文)")
    private String businessName;

    @Schema(description = "是否去掉表前缀")
    private Boolean removePrefix;

    @Schema(description = "表前缀")
    private String prefix;

    @Schema(description = "模板分组ID")
    private Long templateGroupId;

}
