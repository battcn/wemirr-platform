package com.wemirr.platform.suite.gen.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 生成表配置信息
 *
 * @author xiao1
 * @since 2024-12
 */
@Data
public class GenerateTableSaveReq {

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
     * 实体类名称(首字母大写)
     */
    @NotBlank(message = "实体类名称不能为空")
    private String className;

    /**
     * 生成包路径
     * com.wemirr.platform 对应的packagePath = com/wemirr/platform
     */
    @NotBlank(message = "生成包路径不能为空")
    private String packageName;

    /**
     * 生成模块名
     * 前端请求路径 '/${moduleName}/${businessName}/list'
     */
    @NotBlank(message = "生成模块名不能为空")
    private String moduleName;

    /**
     * 作者
     */
    @NotBlank(message = "作者不能为空")
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
    @NotBlank(message = "业务名称不能为空")
    private String businessName;
    /**
     * 是否去掉前缀 => 去掉前缀后生成: sys_user => 实体 User , 类名：User、UserController、UserService
     */
    @Schema(description = "是否去掉前缀")
    @NotNull(message = "是否去掉前缀不能为空")
    private Boolean removePrefix;
    /**
     * 前缀 eg: sys_user => 前缀 sys_
     */
    @Schema(description = "前缀")
    private String prefix;
    /**
     * 关联模板组
     */
    @Schema(description = "关联模板组")
    private Long templateGroupId;

}
