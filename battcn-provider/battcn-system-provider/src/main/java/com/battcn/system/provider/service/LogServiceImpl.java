package com.battcn.system.provider.service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.battcn.framework.mybatis.pojo.DataGrid;
import com.battcn.framework.mybatis.service.impl.BaseServiceImpl;
import com.battcn.system.facade.LogService;
import com.battcn.system.pojo.po.Log;
import com.battcn.system.provider.mapper.LogMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author Levin
 */
@Service
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService {

    private final LogMapper logMapper;

    @Autowired
    public LogServiceImpl(LogMapper logMapper) {
        this.logMapper = logMapper;
    }

    @Override
    public PageInfo<Log> listForDataGrid(DataGrid grid, String datetime) {
        Example example = new Example(Log.class);
        String startTime, endTime;
        if (StringUtils.isNotEmpty(datetime)) {
            String[] time = datetime.split(" - ");
            startTime = time[0];
            endTime = time[1];
            example.createCriteria().andBetween("gmtCreate", startTime, endTime);
        }
        return PageHelper.startPage(grid.getPageNum(), grid.getPageSize()).doSelectPageInfo(() -> this.logMapper.selectByExample(example));
    }
}
