package com.wemirr.framework.security.server;

import cn.hutool.extra.spring.SpringUtil;
import com.google.common.collect.Lists;
import com.wemirr.framework.security.client.RedisOpaqueTokenIntrospector;
import com.wemirr.framework.security.properties.CustomSecurityProperties;
import com.wemirr.framework.security.server.handler.LoginTargetAuthenticationEntryPoint;
import com.wemirr.framework.security.server.integration.IntegrationAuthenticator;
import com.wemirr.framework.security.server.integration.custom.CustomLoginAuthenticationProvider;
import com.wemirr.framework.security.server.store.RedisTokenStore;
import com.wemirr.framework.security.server.support.RedisSecurityContextRepository;
import com.wemirr.framework.security.service.IntegrationUserDetailsServiceImpl;
import com.wemirr.framework.security.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.DelegatingOAuth2TokenGenerator;
import org.springframework.security.oauth2.server.authorization.token.OAuth2RefreshTokenGenerator;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 认证配置
 * {@link EnableMethodSecurity} 开启全局方法认证，启用JSR250注解支持，启用注解 {@link Secured} 支持，
 * 在Spring Security 6.0版本中将@Configuration注解从@EnableWebSecurity, @EnableMethodSecurity, @EnableGlobalMethodSecurity
 * 和 @EnableGlobalAuthentication 中移除，使用这些注解需手动添加 @Configuration 注解
 * {@link EnableWebSecurity} 注解有两个作用:
 * 1. 加载了WebSecurityConfiguration配置类, 配置安全认证策略。
 * 2. 加载了AuthenticationConfiguration, 配置了认证信息。
 *
 * @author Levin
 */
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
@Import({RedisSecurityContextRepository.class, RedisTokenStore.class, CustomSecurityProperties.class})
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class AuthorizationServerConfiguration {


    private final RedisTokenStore<String> redisTokenStore;
    private final CustomSecurityProperties properties;
    private final Collection<IntegrationAuthenticator> integrationAuthenticators;

    /**
     * 配置端点的过滤器链
     *
     * @param http spring security核心配置类
     * @return 过滤器链
     * @throws Exception 抛出
     */
    @Bean
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        // 配置默认的设置，忽略认证端点的csrf校验
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);
        // 当未登录时访问认证端点时重定向至login页面
        http.exceptionHandling((exceptions) -> exceptions.defaultAuthenticationEntryPointFor(new LoginTargetAuthenticationEntryPoint(properties.getLoginFormUrl()), new MediaTypeRequestMatcher(MediaType.TEXT_HTML)))
                .oauth2ResourceServer((resourceServer) -> resourceServer.jwt(Customizer.withDefaults()));
        if (properties.getType() == CustomSecurityProperties.LoadType.redis) {
            final RedisSecurityContextRepository securityContextRepository = SpringUtil.getBean(RedisSecurityContextRepository.class);
            // 使用redis存储、读取登录的认证信息
            http.securityContext(context -> context.securityContextRepository(securityContextRepository));
        }
        if (properties.isDevice()) {
            SecurityApply.applyDeviceSecurity(http);
        }

        // 自定义登录策略
        if (properties.isCustom()) {
            return SecurityApply.applyCustomSecurity(http, integrationAuthenticators);
        }
        return http.build();
    }

    @Bean
    private OpaqueTokenIntrospector opaqueTokenIntrospector() {
        return new RedisOpaqueTokenIntrospector();
    }

    /**
     * 配置认证相关的过滤器链(资源服务，客户端配置)
     *
     * @param http spring security核心配置类
     * @return 过滤器链
     * @throws Exception 抛出
     */
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // 禁用 csrf 与 cors
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);
        List<String> urls = Lists.newArrayList();
        urls.addAll(properties.getServerIgnore().getResourceUrls());
        urls.addAll(properties.getDefaultIgnoreUrls());
        AntPathRequestMatcher[] requestMatchers = urls.stream().map(AntPathRequestMatcher::new).toList().toArray(new AntPathRequestMatcher[]{});
        http.authorizeHttpRequests((authorize) -> authorize.requestMatchers(requestMatchers).permitAll().anyRequest().authenticated());

        if (properties.getType() == CustomSecurityProperties.LoadType.redis) {
            final RedisSecurityContextRepository securityContextRepository = SpringUtil.getBean(RedisSecurityContextRepository.class);
            // 使用redis存储、读取登录的认证信息
            http.securityContext(context -> context.securityContextRepository(securityContextRepository));
        }
        // form 登录策略
        SecurityApply.applyFormLoginSecurity(http, properties);
        // 添加BearerTokenAuthenticationFilter，将认证服务当做一个资源服务，解析请求头中的token
        http.oauth2ResourceServer((resourceServer) -> resourceServer.opaqueToken(opaqueTokenConfigurer -> opaqueTokenConfigurer.introspector(opaqueTokenIntrospector()))
                .accessDeniedHandler(SecurityUtils::exceptionHandler).authenticationEntryPoint(SecurityUtils::exceptionHandler));
        // 当未登录时访问认证端点时重定向至login页面 [兼容前后端分离与不分离配置]
        http.exceptionHandling((exceptions) -> exceptions.defaultAuthenticationEntryPointFor(new LoginTargetAuthenticationEntryPoint(properties.getLoginFormUrl()), new MediaTypeRequestMatcher(MediaType.APPLICATION_JSON)));
        return http.build();
    }


    /**
     * 配置密码解析器，使用BCrypt的方式对密码进行加密和验证
     *
     * @return BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(List<IntegrationAuthenticator> authenticators) {
        return new IntegrationUserDetailsServiceImpl(authenticators);
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService userDetailsService,
                                                               PasswordEncoder passwordEncoder) {
        return new CustomLoginAuthenticationProvider(userDetailsService, passwordEncoder);
    }

    /*  *//**
     * 自定义jwt，将权限信息放至jwt中
     *
     * @return OAuth2TokenCustomizer的实例
     *//*
    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> oAuth2TokenCustomizer() {
        return new FederatedIdentityIdTokenCustomizer();
    }

    *//**
     * 配置jwk源，使用非对称加密，公开用于检索匹配指定选择器的JWK的方法
     *
     * @return JWKSource
     *//*
    @Bean
    @SneakyThrows
    public JWKSource<SecurityContext> jwkSource() {
        // 先从redis获取
        String jwkSetCache = redisTokenStore.get(SecurityConstants.RedisConstants.AUTHORIZATION_JWS_PREFIX_KEY);
        if (ObjectUtils.isEmpty(jwkSetCache)) {
            KeyPair keyPair = generateRsaKey();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            RSAKey rsaKey = new RSAKey.Builder(publicKey).privateKey(privateKey).keyID(UUID.randomUUID().toString()).build();
            // 生成jws
            JWKSet jwkSet = new JWKSet(rsaKey);
            // 转为json字符串
            final String jwkSetString = jwkSet.toString(false);
            // 存入redis
            redisTokenStore.set(SecurityConstants.RedisConstants.AUTHORIZATION_JWS_PREFIX_KEY, jwkSetString);
            return new ImmutableJWKSet<>(jwkSet);
        }
        // 解析存储的jws
        JWKSet jwkSet = JWKSet.parse(jwkSetCache);
        return new ImmutableJWKSet<>(jwkSet);
    }

    *//**
     * 生成rsa密钥对，提供给jwk
     *
     * @return 密钥对
     *//*
    private static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

    *//**
     * 自定义jwt解析器，设置解析出来的权限信息的前缀与在jwt中的key
     *
     * @return jwt解析器 JwtAuthenticationConverter
     *//*
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        // 设置解析权限信息的前缀，设置为空是去掉前缀
        grantedAuthoritiesConverter.setAuthorityPrefix("");
        // 设置权限信息在jwt claims中的key
        grantedAuthoritiesConverter.setAuthoritiesClaimName(SecurityConstants.AUTHORITIES_KEY);
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    *//**
     * 配置jwt解析器
     *
     * @param jwkSource jwk源
     * @return JwtDecoder
     *//*
    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }
*/

    /**
     * 将AuthenticationManager注入ioc中，其它需要使用地方可以直接从ioc中获取
     *
     * @param authenticationConfiguration 导出认证配置
     * @return AuthenticationManager 认证管理器
     */
    @Bean
    @SneakyThrows
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public OAuth2TokenGenerator<OAuth2Token> oAuth2TokenGenerator() {
        CustomOAuth2AccessTokenGenerator accessTokenGenerator = new CustomOAuth2AccessTokenGenerator();
        return new DelegatingOAuth2TokenGenerator(accessTokenGenerator, new OAuth2RefreshTokenGenerator());
    }


    /**
     * 添加认证服务器配置，设置jwt签发者、默认端点请求地址等
     *
     * @return AuthorizationServerSettings
     */
    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder()
                // 设置token签发地址(http(s)://{ip}:{port}/context-path, http(s)://domain.com/context-path)
                // 如果需要通过ip访问这里就是ip，如果是有域名映射就填域名，通过什么方式访问该服务这里就填什么
                .issuer("http://127.0.0.1:5001")
                .build();
    }

}