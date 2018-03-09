package com.battcn.system.facade;


import com.battcn.framework.mybatis.pojo.DataGrid;
import com.battcn.framework.mybatis.service.BaseService;
import com.battcn.system.pojo.po.Log;
import com.github.pagehelper.PageInfo;

/**
 * @author Levin
 */
public interface LogService extends BaseService<Log> {

    /**
     * 根据条件分页查询
     *
     * @param grid     分页对象
     * @param datetime 日期
     * @return 结果集
     */
    PageInfo<Log> listForDataGrid(DataGrid grid, String datetime);

}
