package com.wemirr.platform.authority.configuration.integration.primary;

import com.wemirr.framework.commons.entity.Result;
import com.wemirr.platform.authority.configuration.integration.IntegrationAuthentication;
import com.wemirr.platform.authority.service.VerificationService;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 集成验证码认证
 *
 * @author Levin
 **/
@Component
public class VerificationCodeIntegrationAuthenticator extends UsernamePasswordAuthenticator {

    private final static String VERIFICATION_CODE_AUTH_TYPE = "vc";

    @Resource
    private VerificationService verificationService;

    @Override
    public void prepare(IntegrationAuthentication integrationAuthentication) {
        String vcToken = integrationAuthentication.getAuthParameter("vc_token");
        String vcCode = integrationAuthentication.getAuthParameter("vc_code");
        //验证验证码
        final Result<Boolean> result = verificationService.valid(vcToken, vcCode);
        if (!result.isSuccessful()) {
            throw new OAuth2Exception(result.getMessage());
        }
        super.prepare(integrationAuthentication);
    }

    @Override
    public boolean support(IntegrationAuthentication integrationAuthentication) {
        return VERIFICATION_CODE_AUTH_TYPE.equals(integrationAuthentication.getAuthType());
    }
}