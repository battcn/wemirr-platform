package com.wemirr.platform.authority.domain.common.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author Levin
 */
@Data
public class DictionarySaveReq {


    @Schema(description = "字典编码(字典编码唯一)")
    @NotBlank(message = "编码不能为空")
    @Length(max = 64, message = "类型长度不能超过64")
    private String code;

    @Schema(description = "名称")
    @NotBlank(message = "名称不能为空")
    @Length(max = 64, message = "名称长度不能超过64")
    private String name;

    @Schema(description = "描述")
    @Length(max = 200, message = "描述长度不能超过200")
    private String description;

    @Schema(description = "状态")
    private Boolean status;


}
