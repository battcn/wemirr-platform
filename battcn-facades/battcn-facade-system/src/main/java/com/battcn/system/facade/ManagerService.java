package com.battcn.system.facade;


import com.battcn.framework.mybatis.pojo.DataGrid;
import com.battcn.framework.mybatis.service.BaseService;
import com.battcn.system.pojo.dto.ManagerDto;
import com.battcn.system.pojo.po.Manager;
import com.github.pagehelper.PageInfo;

/**
 * @author Levin
 * @since 2018-01-10
 */
public interface ManagerService extends BaseService<Manager> {

    /**
     * 带分页查询账户信息
     *
     * @param grid    分页信息
     * @param name    名称
     * @param account 账户
     * @return 查询结果
     */
    PageInfo<ManagerDto> listManagerByName(DataGrid grid, String name, String account);

    /**
     * 根据账号查询信息
     *
     * @param account 账号
     * @return 查询结果
     */
    ManagerDto selectManagerByAccount(String account);

}
