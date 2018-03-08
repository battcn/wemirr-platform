package com.battcn.managent.consumer.config.shiro;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.battcn.system.facade.OperateService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Shiro 配置
 *
 * @author Levin
 */
@Slf4j
//@Configuration
public class ShiroConfiguration {


    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880")
    private final OperateService operateService;
    private final AuthRealm authRealm;

    @Autowired
    public ShiroConfiguration(OperateService operateService, AuthRealm authRealm) {
        this.operateService = operateService;
        this.authRealm = authRealm;
    }


    @Bean
    public EhCacheManager getEhCacheManager() {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return em;
    }

    @Bean(name = "authRealm")
    public AuthRealm authRealm(EhCacheManager cacheManager) {
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCacheManager(cacheManager);
        return authRealm;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(AuthRealm authRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(authRealm);
        // <!-- 用户授权/认证信息Cache, 采用EhCache 缓存 -->
        defaultWebSecurityManager.setCacheManager(getEhCacheManager());
        return defaultWebSecurityManager;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(
            DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    /**
     * 加载shiroFilter权限控制规则（从数据库读取然后配置）
     */
    private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean) {
        /////////////////////// 下面这些规则配置最好配置到配置文件中 ///////////////////////
        Map<String, String> filterChainDefinitionMap = Maps.newLinkedHashMap();
        filterChainDefinitionMap.put("/resource/**", "anon");
        filterChainDefinitionMap.put("/install", "anon");
        // anon：它对应的过滤器里面是空的,什么都没做
        log.info("##################从数据库读取权限规则，加载到shiroFilter中##################");
        Map<String, String> permissions = Maps.newLinkedHashMap();
        operateService.listShiroPermissions(null).forEach(pms -> {
            log.info("perms[" + pms.getPerms() + "]");
            permissions.put(pms.getPath(), "authc,perms[" + pms.getPerms() + "]");
        });
        filterChainDefinitionMap.putAll(permissions);
        filterChainDefinitionMap.put("/**", "authc");
        log.debug("[拦截链] - [{}]", JSON.toJSONString(filterChainDefinitionMap));
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }


    /**
     * ShiroFilter<br/>
     * 注意这里参数中的 StudentService 和 IScoreDao 只是一个例子，因为我们在这里可以用这样的方式获取到相关访问数据库的对象，
     * 然后读取数据库相关配置，配置到 shiroFilterFactoryBean 的访问规则中。实际项目中，请使用自己的Service来处理业务逻辑。
     *
     * @param securityManager 安全管理器
     * @return ShiroFilterFactoryBean
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的连接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/denied");
        loadShiroFilterChain(shiroFilterFactoryBean);
        return shiroFilterFactoryBean;
    }

}