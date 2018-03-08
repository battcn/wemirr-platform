package com.battcn.framework.core;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/**
 * 获取Spring容器里面的Bean
 *
 * @author Levin
 * @since 2018/03/07
 */
public class ApplicationContextUtils {

    private static ApplicationContext applicationContext = null;

    public static void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtils.applicationContext = applicationContext;
    }

    /**
     * 根据Bean的ID和类型获取返回的Bean <br/>
     *
     * @return T 返回类型 <br/>
     */
    public static <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return applicationContext.getBean(name, requiredType);
    }

    /**
     * 根据Bean的ID获取返回的Bean <br/>
     *
     * @return T 返回类型 <br/>
     */
    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

}
