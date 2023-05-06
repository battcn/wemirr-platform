package com.wemirr.platform.authority.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

/**
 * @author Levin
 */
@Data
public class DictionaryDTO {

    /**
     * 编码
     * 一颗树仅仅有一个统一的编码
     */
    @Schema(description = "编码类型(一颗树仅仅有一个统一的编码)")
    @NotBlank(message = "编码不能为空")
    @Length(max = 64, message = "类型长度不能超过64")
    private String code;

    /**
     * 名称
     */
    @Schema(description = "名称")
    @NotBlank(message = "名称不能为空")
    @Length(max = 64, message = "名称长度不能超过64")
    private String name;

    /**
     * 描述
     */
    @Schema(description = "描述")
    @Length(max = 200, message = "描述长度不能超过200")
    private String description;

    /**
     * 状态
     */
    @Schema(description = "状态")
    private Boolean status;

    @Schema(description = "排序")
    private Integer sequence;


}
