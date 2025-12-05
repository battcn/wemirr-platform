package com.wemirr.platform.suite.gen.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

/**
 * 生成模板实体类
 *
 * @author xiao1
 * @since 2024-12
 */
@Data
public class GenerateTemplateGroupSaveReq {


    /**
     * 分组名称
     */
    @Schema(description = "分组名称")
    @NotBlank(message = "分组名称不能为空")
    private String name;


    /**
     * 分组描述
     */
    @Schema(description = "分组描述")
    @NotBlank(message = "分组描述不能为空")
    private String description;

    /**
     * 是否作为默认分组
     */
    @Schema(description = "是否作为默认分组")
    private Boolean isDefault;
    /**
     * 关联的模板ids
     */
    @Schema(description = "关联的模板ids")
    private List<Long> templateIds;

    //TODO: 自定义模板映射值

}
