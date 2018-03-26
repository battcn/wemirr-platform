package com.battcn.bookstore.consumer.security.auth.token;


import com.battcn.bookstore.consumer.security.auth.AuthenticationToken;
import com.battcn.bookstore.consumer.security.auth.token.extractor.TokenExtractor;
import com.battcn.bookstore.consumer.security.config.WebSecurityConfig;
import com.battcn.bookstore.consumer.security.model.token.RawAccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 执行Token的验证
 *
 * @author Levin
 * @since 2018-02-02
 */
public class TokenAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final AuthenticationFailureHandler authenticationFailureHandler;
    private final TokenExtractor tokenExtractor;

    @Autowired
    public TokenAuthenticationProcessingFilter(AuthenticationFailureHandler authenticationFailureHandler,
                                               TokenExtractor tokenExtractor, RequestMatcher requestMatcher) {
        super(requestMatcher);
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.tokenExtractor = tokenExtractor;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        String tokenPayload = request.getHeader(WebSecurityConfig.TOKEN_HEADER_PARAM);
        RawAccessToken token = new RawAccessToken(tokenExtractor.extract(tokenPayload));
        return getAuthenticationManager().authenticate(new AuthenticationToken(token));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        authenticationFailureHandler.onAuthenticationFailure(request, response, failed);
    }
}
