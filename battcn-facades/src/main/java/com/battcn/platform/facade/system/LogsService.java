package com.battcn.platform.facade.system;

import com.battcn.platform.model.system.LogsEntity;

import java.util.List;

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
