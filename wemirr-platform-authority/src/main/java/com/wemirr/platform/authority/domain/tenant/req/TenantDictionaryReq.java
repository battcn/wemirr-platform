package com.wemirr.platform.authority.domain.tenant.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


/**
 * 业务字典
 *
 * @author Levin
 */
@Data
@Schema(name = "TenantDictionaryReq")
public class TenantDictionaryReq {

    @Schema(description = "编码类型(一颗树仅仅有一个统一的编码)")
    @NotBlank(message = "编码不能为空")
    @Length(max = 64, message = "类型长度不能超过 {max}")
    private String code;

    @Schema(description = "名称")
    @NotBlank(message = "名称不能为空")
    @Length(max = 64, message = "名称长度不能超过 {max}")
    private String name;

    @Schema(description = "描述")
    @Length(max = 200, message = "描述长度不能超过 {max}")
    private String description;

    @Schema(description = "状态")
    private Boolean status;

}
