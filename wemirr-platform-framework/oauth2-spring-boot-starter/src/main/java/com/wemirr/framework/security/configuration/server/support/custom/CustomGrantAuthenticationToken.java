package com.wemirr.framework.security.configuration.server.support.custom;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

import java.util.*;

/**
 * 自定义短信验证登录Token类
 *
 * @author Levin
 */
public class CustomGrantAuthenticationToken extends AbstractAuthenticationToken {

    @Getter
    private final HttpServletRequest request;
    /**
     * 本次登录申请的scope
     */
    private final Set<String> scopes;

    /**
     * 客户端认证信息
     */
    private final Authentication clientPrincipal;

    /**
     * 当前请求的参数
     */
    private final Map<String, Object> additionalParameters;

    /**
     * 认证方式
     */
    private final AuthorizationGrantType authorizationGrantType;
    
    public CustomGrantAuthenticationToken(HttpServletRequest request,AuthorizationGrantType authorizationGrantType,
                                          Authentication clientPrincipal,
                                          @Nullable Set<String> scopes,
                                          @Nullable Map<String, Object> additionalParameters) {
        super(Collections.emptyList());
        this.request = request;
        this.scopes = Collections.unmodifiableSet(scopes != null ? new HashSet<>(scopes) : Collections.emptySet());
        this.clientPrincipal = clientPrincipal;
        this.additionalParameters = Collections.unmodifiableMap(
                additionalParameters != null ?
                        new HashMap<>(additionalParameters) :
                        Collections.emptyMap());
        this.authorizationGrantType = authorizationGrantType;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return clientPrincipal;
    }
    
    /**
     * 返回请求的scope(s)
     *
     * @return 请求的scope(s)
     */
    public Set<String> getScopes() {
        return this.scopes;
    }
    
    /**
     * 返回请求中的authorization grant type
     *
     * @return authorization grant type
     */
    public AuthorizationGrantType getAuthorizationGrantType() {
        return this.authorizationGrantType;
    }
    
    /**
     * 返回请求中的附加参数
     *
     * @return 附加参数
     */
    public Map<String, Object> getAdditionalParameters() {
        return this.additionalParameters;
    }
    
}