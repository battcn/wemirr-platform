package com.wemirr.platform.authority.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author Levin
 */
@Data
public class DictionaryItemDTO {

    /**
     * 编码
     * 一颗树仅仅有一个统一的编码
     */
    @Schema(description = "值")
    @NotBlank(message = "值不能为空")
    @Length(max = 64, message = "值的长度不能超过{max}")
    private String value;

    /**
     * 名称
     */
    @Schema(description = "名称")
    @NotBlank(message = "名称不能为空")
    @Length(max = 64, message = "名称长度不能超过{max}")
    private String label;

    /**
     * 描述
     */
    @Schema(description = "描述")
    @Length(max = 200, message = "描述长度不能超过{max}")
    private String description;

    @Schema(description = "颜色")
    @Length(max = 20, message = "颜色长度不能超过{max}")
    private String color;
    /**
     * 状态
     */
    @Schema(description = "状态")
    private Boolean status;


}
