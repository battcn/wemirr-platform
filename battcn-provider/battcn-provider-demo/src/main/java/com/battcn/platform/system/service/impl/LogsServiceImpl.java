package com.battcn.platform.system.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.battcn.platform.system.mapper.LogsMapper;
import com.battcn.platform.system.model.LogsEntity;
import com.battcn.platform.system.service.LogsService;

@Service("logsService")
public class LogsServiceImpl implements LogsService
{
    @Resource
    LogsMapper logsMapper;

    @Override
    public List<LogsEntity> queryLogsForList()
    {
        return this.logsMapper.selectAll();
    }
    
    
    
}
