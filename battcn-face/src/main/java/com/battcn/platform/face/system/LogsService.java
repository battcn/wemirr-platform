package com.battcn.platform.face.system;

import com.battcn.platform.entity.system.LogsEntity;

import java.util.List;

/**
 * Created by Administrator on 2016-11-22.
 */
public interface LogsService
{
    List<LogsEntity> queryLogsForList();
}
