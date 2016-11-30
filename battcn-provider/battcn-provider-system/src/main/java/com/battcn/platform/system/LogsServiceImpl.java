package com.battcn.platform.system;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.battcn.platform.face.system.LogsService;
import com.battcn.platform.models.system.LogsEntity;
import com.battcn.platform.system.mapper.LogsMapper;

@Service("logsService")
public class LogsServiceImpl implements LogsService
{
    @Autowired
    LogsMapper logsMapper;

    @Override
    public List<LogsEntity> queryLogsForList()
    {
        return this.logsMapper.selectAll();
    }
}
