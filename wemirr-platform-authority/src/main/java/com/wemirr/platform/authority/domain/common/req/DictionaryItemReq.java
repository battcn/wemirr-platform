package com.wemirr.platform.authority.domain.common.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author Levin
 */
@Data
public class DictionaryItemReq {

    @Schema(description = "名称")
    @NotBlank(message = "名称不能为空")
    @Length(max = 64, message = "名称长度不能超过{max}")
    private String label;

    @Schema(description = "值")
    @NotBlank(message = "值不能为空")
    @Length(max = 64, message = "值的长度不能超过{max}")
    private String value;

    @Schema(description = "状态")
    private Boolean status;

    @Schema(description = "排序")
    private Integer sequence;

    @Schema(description = "描述")
    @Length(max = 100, message = "值的长度不能超过{max}")
    private String description;
}
