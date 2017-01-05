package com.battcn.platform.system.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.battcn.platform.system.model.LogsEntity;
import com.battcn.platform.system.service.LogsService;

@RestController
@RequestMapping("logs")
public class LogsController {
	
	@Autowired 
	LogsService logsService;
	
	@GetMapping("query")
	public List<LogsEntity> query()
	{
		return this.logsService.queryLogsForList();
	}

}
