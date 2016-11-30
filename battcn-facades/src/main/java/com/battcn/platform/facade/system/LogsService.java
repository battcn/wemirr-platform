package com.battcn.platform.facade.system;

import com.battcn.platform.model.system.LogsEntity;

import java.util.List;

/**
 * Created by Administrator on 2016-11-22.
 */
public interface LogsService
{
    List<LogsEntity> queryLogsForList();
}
