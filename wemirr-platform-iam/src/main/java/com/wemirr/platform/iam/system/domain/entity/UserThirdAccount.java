package com.wemirr.platform.iam.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.platform.iam.system.domain.enums.ThirdAuthType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

/**
 * @author Levin
 */
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Schema(description = "第三方授权账户实体类")
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_user_third_account")
public class UserThirdAccount extends SuperEntity<Long> {

    @Schema(description = "平台用户 ID")
    private Long userId;

    @Schema(description = "平台类型")
    private ThirdAuthType type;

    @Schema(description = "第三方平台用户唯一标识")
    private String accountId;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "头像 URL")
    private String avatar;

    @Schema(description = "访问令牌")
    private String accessToken;

    @Schema(description = "刷新令牌")
    private String refreshToken;

    @Schema(description = "令牌过期时间")
    private Instant tokenExpireTime;

}
