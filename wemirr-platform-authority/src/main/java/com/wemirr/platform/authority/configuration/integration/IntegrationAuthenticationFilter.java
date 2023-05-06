package com.wemirr.platform.authority.configuration.integration;

import com.alibaba.fastjson.JSON;
import com.wemirr.framework.commons.entity.Result;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


/**
 * 扩展多登陆类型
 *
 * @author Levin
 */
@Component
public class IntegrationAuthenticationFilter extends GenericFilterBean implements ApplicationContextAware {
    /**
     * 默认登录URL
     */
    private static final String OAUTH_TOKEN_URL = "/oauth/token";
    private static final String CLIENT_ID = "client_id";
    private static final String AUTH_TYPE = "auth_type";
    private static final String TENANT_CODE = "tenant_code";

    private ApplicationContext applicationContext;

    private RequestMatcher requestMatcher;
    private Collection<IntegrationAuthenticator> authenticators;

    public IntegrationAuthenticationFilter() {
        this.requestMatcher = new OrRequestMatcher(
                new AntPathRequestMatcher(OAUTH_TOKEN_URL, HttpMethod.GET.name()),
                new AntPathRequestMatcher(OAUTH_TOKEN_URL, HttpMethod.POST.name())
        );
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (requestMatcher.matches(request)) {
            //设置集成登录信息
            IntegrationAuthentication integrationAuthentication = new IntegrationAuthentication();
            integrationAuthentication.setAuthType(request.getParameter(AUTH_TYPE));
            integrationAuthentication.setTenantCode(request.getParameter(TENANT_CODE));
            integrationAuthentication.setClientId(request.getParameter(CLIENT_ID));
            integrationAuthentication.setAuthParameters(request.getParameterMap());
            IntegrationAuthenticationContext.set(integrationAuthentication);
            try {
                // 预处理
                this.prepare(integrationAuthentication);
                // 过滤
                filterChain.doFilter(request, response);
                // 后置处理
                this.complete(integrationAuthentication);
            } catch (Exception e) {
                logger.warn("[异常信息] - [" + e.getMessage() + "] - [异常回写]");
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                IOUtils.write(JSON.toJSONBytes(Result.fail(e.getMessage())), response.getOutputStream());
            } finally {
                IntegrationAuthenticationContext.clear();
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    /**
     * 进行预处理
     */
    private void prepare(IntegrationAuthentication integrationAuthentication) {
        //延迟加载认证器
        if (this.authenticators == null) {
            synchronized (this) {
                Map<String, IntegrationAuthenticator> integrationAuthenticatorMap = applicationContext.getBeansOfType(IntegrationAuthenticator.class);
                if (!CollectionUtils.isEmpty(integrationAuthenticatorMap)) {
                    this.authenticators = integrationAuthenticatorMap.values();
                }
            }
        }
        if (this.authenticators == null) {
            this.authenticators = new ArrayList<>();
        }
        for (IntegrationAuthenticator authenticator : authenticators) {
            boolean support = authenticator.support(integrationAuthentication);
            if (support) {
                authenticator.prepare(integrationAuthentication);
            }
        }
    }

    /**
     * 后置处理
     */
    private void complete(IntegrationAuthentication integrationAuthentication) {
        for (IntegrationAuthenticator authenticator : authenticators) {
            if (authenticator.support(integrationAuthentication)) {
                authenticator.complete(integrationAuthentication);
            }
        }
        // 记录登陆日志
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
