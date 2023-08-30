package com.wemirr.platform.authority.configuration;

import com.wemirr.framework.security.client.entity.UserInfoDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author Levin
 */
public class TenantAuthenticationKeyGenerator extends DefaultAuthenticationKeyGenerator {

    private static final String TENANT_CODE = "tenant_code";

    private static final String CLIENT_ID = "client_id";

    private static final String SCOPE = "scope";

    private static final String USERNAME = "username";

    @Override
    public String extractKey(OAuth2Authentication authentication) {
        Map<String, String> values = new LinkedHashMap<String, String>();
        OAuth2Request authorizationRequest = authentication.getOAuth2Request();
        if (!authentication.isClientOnly()) {
            values.put(USERNAME, authentication.getName());
        }
        Authentication userAuthentication = authentication.getUserAuthentication();
        if (userAuthentication != null) {
            final UserInfoDetails userInfoDetails = (UserInfoDetails) userAuthentication.getPrincipal();
            if (userInfoDetails != null) {
                values.put(TENANT_CODE, userInfoDetails.getTenantCode());
            }
        }
        values.put(CLIENT_ID, authorizationRequest.getClientId());
        if (authorizationRequest.getScope() != null) {
            values.put(SCOPE, OAuth2Utils.formatParameterList(new TreeSet<>(authorizationRequest.getScope())));
        }
        return generateKey(values);
    }

}