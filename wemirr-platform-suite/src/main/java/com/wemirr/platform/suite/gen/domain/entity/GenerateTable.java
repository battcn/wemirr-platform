package com.wemirr.platform.suite.gen.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 生成表配置信息
 *
 * @author xiao1
 * @since 2024-12
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("c_generate_table")
public class GenerateTable extends SuperEntity<Long> {

    /**
     * 表名称
     */
    @NotBlank(message = "表名称不能为空")
    private String name;

    /**
     * 表描述
     */
    @NotBlank(message = "表描述不能为空")
    private String comment;


    /**
     * 实体类名  user表 => User
     */
    @NotBlank(message = "实体类名称不能为空")
    private String className;

    /**
     * 包名
     * com.wemirr.platform 对应的packagePath = com/wemirr/platform
     */
    @NotBlank(message = "包名不能为空")
    private String packageName;

    /**
     * 生成模块名
     * 前端请求路径 '/${moduleName}/${businessName}/list'
     */
    @NotBlank(message = "生成模块名不能为空")
    private String moduleName;
    /**
     * 表列信息
     */
    @Valid
    @TableField(exist = false)
    private List<GenerateTableColumn> columns;

    /**
     * 作者
     */
    @Schema(description = "作者")
    private String author;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;
    /*
     * 业务名称 [英文，用于api路径命名] businessName = "user" => users/list, users/add
     */
    @Schema(description = "业务名称")
    private String businessName;
    /**
     * 是否去掉前缀
     */
    @Schema(description = "是否去掉前缀")
    private Boolean removePrefix;
    /**
     * 前缀
     */
    @Schema(description = "前缀")
    private String prefix;
    /**
     * 是否开启swagger配置
     */
    @Schema(description = "是否开启swagger配置")
    private Boolean swagger;

    /**
     * 关联模板组
     */
    @Schema(description = "关联模板组")
    private Long templateGroupId;


    @Schema(description = "租户ID")
    private Long tenantId;
}



