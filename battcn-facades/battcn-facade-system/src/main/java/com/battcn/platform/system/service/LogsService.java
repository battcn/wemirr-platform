package com.battcn.platform.system.service;

import java.util.List;

import com.battcn.platform.system.model.LogsEntity;

/**
 * 
 * @author 唐亚峰
 * @desc 日志查询
 *
 */
public interface LogsService
{
    List<LogsEntity> queryLogsForList();
}
