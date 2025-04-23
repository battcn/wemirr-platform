package com.wemirr.platform.suite.gen.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author xiao1
 * @since 2024-12
 */
@Data
public class GenerateTableColumnSaveReq {

    @Schema(description = "ID")
    private Long id;

    /**
     * 表名称 [ alipan 是用表名称进行关联的 ]
     */
    @NotBlank(message = "表名称不能为空")
    private String tableName;

    /**
     * 字段名称
     */
    @NotBlank(message = "字段名称不能为空")
    private String name;

    /**
     * 排序
     */
    @NotBlank(message = "排序不能为空")
    private Integer sort;

    /**
     * 字段类型
     */
    @NotBlank(message = "字段类型不能为空")
    private String type;

    /**
     * 注释
     */
    private String comment;
    /**
     * 属性类型
     */
    private String propertyType;
    /**
     * 属性名
     */
    private String propertyName;
    /**
     * 属性包
     */
    private String propertyPackage;

    /**
     * 是否主键
     */
    @NotNull(message = "是否主键不能为空")
    private Boolean pk;

    /**
     * 是否自增
     */
    @NotNull(message = "是否自增不能为空")
    private Boolean increment;

    /**
     * 是否必填
     */
    @NotNull(message = "是否必填不能为空")
    private Boolean required;

    /**
     * 是否为新增
     */
    @NotNull(message = "是否为新增不能为空")
    private Boolean inserted;

    /**
     * 是否编辑
     */
    @NotNull(message = "是否编辑不能为空")
    private Boolean edit;

    /**
     * 是否列表展示
     */
    @NotNull(message = "是否列表展示不能为空")
    private Boolean list;

    /**
     * 是否查询
     */
    @NotNull(message = "是否查询不能为空")
    private Boolean search;

    /**
     * 查询条件
     */
   // @NotBlank(message = "查询条件不能为空")
    private String searchCondition;
    /**
     * 是否需要生成;默认设置1：是
     * 【因为本系统模板继承superEntity，所以superEntity的字段无需生成】
     */
    @NotNull(message = "是否需要生成不能为空")
    private Boolean generate;
}
