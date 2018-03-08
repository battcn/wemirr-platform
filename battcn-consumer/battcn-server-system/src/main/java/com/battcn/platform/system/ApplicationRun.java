package com.battcn.platform.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@ImportResource({"classpath:system-consum.xml"})
@ComponentScan("com.battcn.platform.*")
public class ApplicationRun
{
	
    @RequestMapping("/")
    public String home()
    {
        return "hello battcn-server-parent";
    }

    public static void main(String[] args)
    {
        SpringApplication.run(ApplicationRun.class, args);
    }
}
