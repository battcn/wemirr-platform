package com.wemirr.framework.i18n.core;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.context.HierarchicalMessageSource;
import org.springframework.context.MessageSource;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * 解决静态资源文件 + 动态资源不兼容问题
 *
 * @author Levin
 */
public class MessageSourceHierarchicalChanger {

    @Resource(name = AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)
    private MessageSource messageSource;

    @Resource
    private DynamicMessageSource dynamicMessageSource;

    @PostConstruct
    public void changeMessageSourceParent() {
        // 优先走 messageSource，从资源文件中查找
        if (messageSource instanceof HierarchicalMessageSource hierarchicalMessageSource) {
            MessageSource parentMessageSource = hierarchicalMessageSource.getParentMessageSource();
            dynamicMessageSource.setParentMessageSource(parentMessageSource);
            hierarchicalMessageSource.setParentMessageSource(dynamicMessageSource);
        } else {
            dynamicMessageSource.setParentMessageSource(messageSource);
        }
    }

}
