package com.wemirr.platform.authority.configuration.primary;

import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.security.exception.OAuth2InvalidException;
import com.wemirr.framework.security.server.integration.IntegrationAuthentication;
import com.wemirr.platform.authority.service.VerificationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

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
            throw new OAuth2InvalidException(result.getMessage());
        }
        super.prepare(integrationAuthentication);
    }

    @Override
    public boolean support(IntegrationAuthentication integrationAuthentication) {
        return VERIFICATION_CODE_AUTH_TYPE.equals(integrationAuthentication.getLoginType());
    }
}