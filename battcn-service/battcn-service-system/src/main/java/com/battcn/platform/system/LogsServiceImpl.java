package com.battcn.platform.system;


import com.battcn.platform.face.system.LogsService;
import com.battcn.platform.system.mapper.LogsMapper;
import com.battcn.platform.entity.system.LogsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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
