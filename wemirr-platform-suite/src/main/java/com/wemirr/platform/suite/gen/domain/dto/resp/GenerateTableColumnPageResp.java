package com.wemirr.platform.suite.gen.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * @author xiao1
 * @since 2024-12
 */
@Data
public class GenerateTableColumnPageResp {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "表名称")
    private String tableName;

    @Schema(description = "字段名称")
    private String name;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "字段类型")
    private String type;

    @Schema(description = "字段注释")
    private String comment;

    @Schema(description = "属性类型")
    private String propertyType;

    @Schema(description = "属性名")
    private String propertyName;

    @Schema(description = "属性包")
    private String propertyPackage;

    @Schema(description = "是否主键")
    private Boolean pk;

    @Schema(description = "是否自增")
    private Boolean increment;

    @Schema(description = "是否必填")
    private Boolean required;

    @Schema(description = "是否为新增字段")
    private Boolean inserted;

    @Schema(description = "是否可编辑")
    private Boolean edit;

    @Schema(description = "是否列表展示")
    private Boolean list;

    @Schema(description = "是否查询字段")
    private Boolean search;

    @Schema(description = "查询条件")
    private String searchCondition;

    @Schema(description = "是否需要生成")
    private Boolean generate;


    @Schema(description = "创建时间")
    private Instant createTime;
}
