package com.battcn.platform.framework.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * @ClassName: AppContextUtil
 * @Description: 获取Spring容器里面的Bean
 * @author 唐亚峰
 * @date 2016年8月31日
 */
public class AppContextUtil implements ApplicationContextAware {

	private static ApplicationContext ctx = null;

	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		AppContextUtil.ctx = ctx;
	}

	/**
	 * 
	 * @Description: 根据Bean的ID和类型获取返回的Bean <br/>
	 * @return T 返回类型 <br/>
	 */
	public static <T> T getBean(String name, Class<T> requiredType) throws BeansException {
		return ctx.getBean(name, requiredType);
	}

	/**
	 * @Description: 根据Bean的ID获取返回的Bean <br/>
	 * @return T 返回类型 <br/>
	 */
	public static Object getBean(String name) throws BeansException {
		return ctx.getBean(name);
	}

}
