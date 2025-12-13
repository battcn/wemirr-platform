package com.wemirr.platform.iam.base.domain.dto.req;

import cloud.tianai.captcha.validator.common.model.dto.ImageCaptchaTrack;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Horen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "滑块验证码校验请求体")
public class CaptchaReq {

    @NotBlank(message = "验证码ID不能为空")
    @Schema(description = "验证码的ID")
    private String id;

    @NotNull(message = "滑块数据不能为空")
    @Schema(description = "滑块数据")
    private ImageCaptchaTrack captchaTrack;

    @NotBlank(message = "身份信息不能为空")
    @Schema(description = "身份信息")
    private String principal;
}
