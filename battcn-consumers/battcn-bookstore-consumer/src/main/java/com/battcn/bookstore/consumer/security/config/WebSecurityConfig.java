package com.battcn.bookstore.consumer.security.config;


import com.battcn.bookstore.consumer.security.auth.RestAuthenticationEntryPoint;
import com.battcn.bookstore.consumer.security.auth.login.LoginAuthenticationProvider;
import com.battcn.bookstore.consumer.security.auth.login.LoginProcessingFilter;
import com.battcn.bookstore.consumer.security.auth.token.SkipPathRequestMatcher;
import com.battcn.bookstore.consumer.security.auth.token.TokenAuthenticationProcessingFilter;
import com.battcn.bookstore.consumer.security.auth.token.TokenAuthenticationProvider;
import com.battcn.bookstore.consumer.security.auth.token.extractor.TokenExtractor;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Levin
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Token存放的请求头
     */
    public static final String TOKEN_HEADER_PARAM = "X-Authorization";
    /**
     * 需要验证的路径
     */
    private static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/app/**";

    /**
     * 登陆地址
     */
    private static final String FORM_BASED_LOGIN_ENTRY_POINT = "/auth/login";

    /**
     * 刷新Token的地址
     */
    private static final String TOKEN_REFRESH_ENTRY_POINT = "/auth/refresh";

    private final RestAuthenticationEntryPoint authenticationEntryPoint;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;
    private final LoginAuthenticationProvider loginAuthenticationProvider;
    private final TokenAuthenticationProvider tokenAuthenticationProvider;
    private final TokenExtractor tokenExtractor;

    @Autowired
    public WebSecurityConfig(RestAuthenticationEntryPoint authenticationEntryPoint, AuthenticationSuccessHandler authenticationSuccessHandler, AuthenticationFailureHandler authenticationFailureHandler, LoginAuthenticationProvider loginAuthenticationProvider, TokenAuthenticationProvider tokenAuthenticationProvider, TokenExtractor tokenExtractor) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.loginAuthenticationProvider = loginAuthenticationProvider;
        this.tokenAuthenticationProvider = tokenAuthenticationProvider;
        this.tokenExtractor = tokenExtractor;
    }

    /**
     * 构建登陆处理器
     *
     * @return 登陆处理（过滤器）
     */
    @Bean
    LoginProcessingFilter buildLoginProcessingFilter() throws Exception {
        LoginProcessingFilter filter = new LoginProcessingFilter(FORM_BASED_LOGIN_ENTRY_POINT, authenticationSuccessHandler, authenticationFailureHandler);
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    /**
     * 构建 Token 认证过滤器
     *
     * @return Token 认证过滤器
     */
    @Bean
    TokenAuthenticationProcessingFilter buildTokenAuthenticationProcessingFilter() throws Exception {
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(Lists.newArrayList(TOKEN_BASED_AUTH_ENTRY_POINT));
        TokenAuthenticationProcessingFilter filter = new TokenAuthenticationProcessingFilter(authenticationFailureHandler, tokenExtractor, matcher);
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(loginAuthenticationProvider);
        auth.authenticationProvider(tokenAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 因为使用的是JWT，因此这里可以关闭csrf了
        http.csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(this.authenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(FORM_BASED_LOGIN_ENTRY_POINT).permitAll()
                .antMatchers(TOKEN_REFRESH_ENTRY_POINT).permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(TOKEN_BASED_AUTH_ENTRY_POINT).authenticated()
                .and()
                .addFilterBefore(buildLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildTokenAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
