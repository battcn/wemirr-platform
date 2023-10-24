package com.wemirr.platform.authority.domain.common.req;

import cn.hutool.core.lang.RegexPool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

/**
 * 修改用户基本信息
 *
 * @author Levin
 */
@Data
@Schema(name = "ChangeUserInfoReq", description = "修改用户基本信息")
public class ChangeUserInfoReq {

    @Schema(description = "邮箱")
    @NotBlank(message = "邮箱不能为空")
    @Length(max = 64, message = "邮箱长度不能超过{max}")
    private String email;

    @Schema(description = "昵称")
    @NotEmpty(message = "昵称不能为空")
    @Length(max = 64, message = "昵称长度不能超过{max}")
    private String nickName;


    @Schema(description = "手机号")
    @NotBlank(message = "手机号不能为空")
    @Length(max = 11, message = "手机号长度不能超过{max}")
    @Pattern(regexp = RegexPool.MOBILE, message = "手机号格式错误")
    private String mobile;

    @Schema(description = "生日")
    private LocalDate birthday;

    @Schema(description = "描述")
    private String description;

}
