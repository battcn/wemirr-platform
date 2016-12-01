package com.battcn.platform.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.battcn.platform.facade.system.LogsService;

public class TestQueryMain {
	
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/spring-application.xml");
			LogsService logsService = (LogsService) context.getBean("logsService");
			logsService.queryLogsForList().forEach(d->System.out.println("查询："+d.getMessage()));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
