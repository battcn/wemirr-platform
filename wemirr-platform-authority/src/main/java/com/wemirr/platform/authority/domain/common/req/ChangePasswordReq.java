package com.wemirr.platform.authority.domain.common.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * <p>
 * 实体类
 * 用户
 * </p>
 *
 * @author Levin
 * @since 2019-11-04
 */
@Data
@Schema(name = "ChangePasswordReq", description = "用户")
public class ChangePasswordReq {
    /**
     * 密码
     */
    @Schema(description = "旧密码")
    @NotBlank(message = "旧密码不能为空")
    @Length(max = 64, message = "旧密码长度不能超过 {max}")
    private String originalPassword;
    /**
     * 密码
     */
    @Schema(description = "密码")
    @NotBlank(message = "密码不能为空")
    @Length(max = 64, message = "密码长度不能超过 {max}")
    private String password;

    /**
     * 密码
     */
    @Schema(description = "确认密码")
    @NotBlank(message = "确认密码不能为空")
    @Length(max = 64, message = "确认密码长度不能超过 {max}")
    private String confirmPassword;
}
