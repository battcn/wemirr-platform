package com.battcn.platform.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.battcn.platform.facade.system.LogsService;
import com.battcn.platform.model.system.LogsEntity;

@SpringBootApplication
@RestController
@ImportResource({"classpath:system-consum.xml"})
@ComponentScan("com.battcn.platform.system.*")
public class ApplicationRun
{
	
	@Autowired
	private LogsService logsService;
	
    @RequestMapping("/")
    public List<LogsEntity> home()
    {
        return this.logsService.queryLogsForList();
    }

    public static void main(String[] args)
    {
        SpringApplication.run(ApplicationRun.class, args);
    }
}
