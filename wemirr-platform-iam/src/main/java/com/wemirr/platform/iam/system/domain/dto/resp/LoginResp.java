package com.wemirr.platform.iam.system.domain.dto.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class LoginResp {

    @Schema(description = "scope")
    @JsonProperty("scope")
    private List<String> scope;

    @Schema(description = "openId")
    @JsonProperty("openId")
    private String openId;

    @Schema(description = "访问令牌")
    @JsonProperty("access_token")
    private String accessToken;

    @Schema(description = "Token 类型")
    @JsonProperty("token_type")
    private String tokenType;

    @Schema(description = "刷新令牌")
    @JsonProperty("refresh_token")
    private String refreshToken;

    @Schema(description = "客户端ID")
    @JsonProperty("client_id")
    private String clientId;

    @Schema(description = "访问令牌失效时间")
    @JsonProperty("expires_in")
    private Long expiresIn;

    @Schema(description = "刷新令牌失效时间")
    @JsonProperty("refresh_expire_in")
    private Long refreshExpireIn;



}
