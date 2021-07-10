package com.wemirr.platform.authority.domain.entity.baseinfo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Levin
 */
@Data
@TableName("sys_oauth_client_details")
public class OAuthClientDetails {

    @TableId
    private String clientId;
    private String clientSecret;
    private String clientName;
    private String resourceIds;
    private String scope;
    private String authorizedGrantTypes;
    private String webServerRedirectUri;
    private String authorities;
    private Integer accessTokenValidity;
    private Integer refreshTokenValidity;
    private String additionalInformation;
    @TableField("autoapprove")
    private String autoApprove;
}
