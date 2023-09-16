package com.wemirr.platform.authority.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;
import java.util.List;

/**
 * @author Levin
 */
@Data
@Schema(name = "RegisteredClientRefResp")
public class RegisteredClientRefResp {

    @Schema(description = "客户端ID")
    private String id;
    @Schema(description = "客户端名称")
    private String clientName;
    @Schema(description = "客户端ID")
    private String clientId;
    @Schema(description = "客户端ID生效时间")
    private Instant clientIdIssuedAt;
    @Schema(description = "客户端秘钥")
    private String clientSecret;
    @Schema(description = "秘钥失效时间")
    private Instant clientSecretExpiresAt;
    @Schema(description = "授权类型")
    private List<String> authorizationGrantTypes;
    @Schema(description = "重定向地址")
    private String redirectUris;
    @Schema(description = "退出后重定向地址")
    private String postLogoutRedirectUris;
    @Schema(description = "授权范围")
    private List<String> scopes;
    @Schema(description = "accessToken 有效时长(2小时) ", example = "120")
    private Long accessTokenTimeToLive;
    @Schema(description = "refreshToken 有效时长(7天)", example = "10080")
    private Long refreshTokenTimeToLive;
    @Schema(description = "授权码 有效时长（5分钟）", example = "5")
    private Long authorizationCodeTimeToLive;
    @Schema(description = "设备码 有效时长（分钟）", example = "5")
    private Long deviceCodeTimeToLive;

}
