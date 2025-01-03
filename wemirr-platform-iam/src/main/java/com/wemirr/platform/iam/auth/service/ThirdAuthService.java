package com.wemirr.platform.iam.auth.service;

import com.wemirr.platform.iam.system.domain.dto.resp.ThirdAuthResp;
import com.wemirr.platform.iam.system.domain.enums.ThirdAuthType;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;

/**
 * @author Levin
 */
public interface ThirdAuthService {

    /**
     * 三方授权平台
     *
     * @return 授权平台
     */
    ThirdAuthType platform();


    /**
     * AuthRequest
     *
     * @return AuthRequest
     */
    AuthRequest authRequest();

    /**
     * 授权信息
     *
     * @return 授权信息
     */
    ThirdAuthResp authorize();

    /**
     * 回调
     *
     * @param user user
     */
    void callback(AuthUser user);

}
