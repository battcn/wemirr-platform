package com.battcn.platform.system;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.battcn.platform.system.model.LogsEntity;
import com.battcn.platform.system.service.LogsService;

@SpringBootApplication
@RestController
@ImportResource({"classpath:system-consum.xml"})
@ComponentScan("com.battcn.platform.*")
public class ApplicationRun
{
	
	@Resource
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
