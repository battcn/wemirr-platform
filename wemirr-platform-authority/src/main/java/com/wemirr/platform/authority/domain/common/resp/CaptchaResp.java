package com.wemirr.platform.authority.domain.common.resp;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取验证码返回
 *
 * @author Levin
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CaptchaResp {

    /**
     * 验证码id
     */
    private String captchaId;

    /**
     * 验证码的值
     */
    @JsonIgnore
    private String code;

    /**
     * 图片验证码的base64值
     */
    private String imageData;

}
