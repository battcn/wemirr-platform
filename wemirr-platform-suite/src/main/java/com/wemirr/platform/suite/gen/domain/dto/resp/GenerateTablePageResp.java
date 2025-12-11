package com.wemirr.platform.suite.gen.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * @author xiao1
 * @since 2024-12
 */
@Data
@Schema(description = "数据表分页响应实体")
public class GenerateTablePageResp {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "表名称")
    private String name;

    @Schema(description = "表描述")
    private String comment;

    @Schema(description = "实体类名称")
    private String className;

    @Schema(description = "生成包路径")
    private String packageName;

    @Schema(description = "生成模块名")
    private String moduleName;

    @Schema(description = "是否去掉表前缀")
    private Boolean removePrefix;

    @Schema(description = "表前缀")
    private String prefix;

    @Schema(description = "业务名称")
    private String businessName;

    @Schema(description = "作者")
    private String author;

    @Schema(description = "创建时间")
    private Instant createTime;

    @Schema(description = "模板分组ID")
    private Long templateGroupId;
}