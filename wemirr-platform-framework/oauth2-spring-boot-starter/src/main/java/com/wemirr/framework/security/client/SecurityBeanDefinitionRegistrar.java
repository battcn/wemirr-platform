package com.wemirr.framework.security.client;

import com.wemirr.framework.security.client.annotation.EnableOauth2ClientResourceServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 安全认证注册器（暂无实际用处）
 *
 * @author Levin
 * @since 2019-03-30
 */
@Slf4j
public class SecurityBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    private static final String PREFER_TOKEN_INFO = "preferTokenInfo";
    /***
     * 资源服务器默认bean名称
     */
    private static final String RESOURCE_SERVER_CONFIGURER = "resourceServerConfigurerAdapter";

    /**
     * 根据注解值动态注入资源服务器的相关属性
     *
     * @param metadata 注解信息
     * @param registry 注册器
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        if (registry.isBeanNameInUse(RESOURCE_SERVER_CONFIGURER)) {
            log.warn("本地存在资源服务器配置，覆盖默认配置:" + RESOURCE_SERVER_CONFIGURER);
            return;
        }
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(EnableOauth2ClientResourceServer.class.getName()));
        if (annotationAttributes == null) {
            return;
        }
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        final boolean preferTokenInfo = annotationAttributes.getBoolean(PREFER_TOKEN_INFO);
        if (preferTokenInfo) {
            beanDefinition.setBeanClass(LoadBalancedTokenInfoResourceServerConfigurerAdapter.class);
        } else {
            beanDefinition.setBeanClass(LoadBalancedResourceServerConfigurerAdapter.class);
        }
        registry.registerBeanDefinition(RESOURCE_SERVER_CONFIGURER, beanDefinition);

    }
}
