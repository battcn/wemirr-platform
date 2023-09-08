package com.wemirr.platform.tools.domain.req;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DynamicReleaseDragExtendReq {

    @NotBlank(message = "请求方法不能为空")
    private String method;
    @NotBlank(message = "请求地址不能为空")
    private String url;
    @NotBlank(message = "秘钥不能为空")
    private String secret;
    private List<String> opt;
    private Boolean locked;

}
