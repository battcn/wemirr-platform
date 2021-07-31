package com.wemirr.platform.authority.domain.entity.baseinfo;

import com.baomidou.mybatisplus.annotation.IdType;
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

    @TableId(type = IdType.INPUT)
    private String clientId;
    private String clientSecret;
    private String clientName;
    private String scope;
    private String authorizedGrantTypes;
    private String webServerRedirectUri;
    private Integer accessTokenValidity;
    private Boolean status;
    /**
     * 应用类型（0=综合应用,1=服务应用,2=PC网页,3=手机网页,4=小程序）
     */
    private Integer type;


    private String resourceIds;
    private String authorities;
    private Integer refreshTokenValidity;
    private String additionalInformation;
    @TableField("autoapprove")
    private String autoApprove;

}
