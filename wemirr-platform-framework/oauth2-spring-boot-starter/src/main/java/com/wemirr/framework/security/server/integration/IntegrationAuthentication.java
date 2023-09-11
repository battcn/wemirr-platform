package com.wemirr.framework.security.server.integration;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;

import java.util.Map;

/**
 * @author Levin
 * @since 2019-04-03
 **/
@Data
public class IntegrationAuthentication {

    private String loginType;
    private String username;
    private String password;
    private String clientId;
    private String tenantCode;
    private Map<String, String[]> authParameters;

    public String getAuthParameter(String parameter) {
        String[] values = this.authParameters.get(parameter);
        if (values != null && values.length > 0) {
            return values[0];
        }
        return null;
    }

    public IntegrationAuthentication() {

    }

    public IntegrationAuthentication(HttpServletRequest request) {
        this.setLoginType(request.getParameter("login_type"));
        this.setUsername(request.getParameter("username"));
        this.setPassword(request.getParameter("password"));
        this.setClientId(request.getParameter("client_id"));
        this.setTenantCode(request.getParameter("tenant_code"));
        this.setAuthParameters(request.getParameterMap());
    }
}