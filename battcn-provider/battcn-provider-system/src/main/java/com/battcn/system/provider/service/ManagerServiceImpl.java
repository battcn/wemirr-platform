package com.battcn.system.provider.service;

import com.battcn.framework.mybatis.pojo.DataGrid;
import com.battcn.framework.mybatis.service.impl.BaseServiceImpl;
import com.battcn.system.facade.ManagerService;
import com.battcn.system.pojo.dto.ManagerDto;
import com.battcn.system.pojo.po.Manager;
import com.battcn.system.provider.mapper.ManagerMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Levin
 */
@com.alibaba.dubbo.config.annotation.Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class ManagerServiceImpl extends BaseServiceImpl<Manager> implements ManagerService {

    private final ManagerMapper managerMapper;

    @Autowired
    public ManagerServiceImpl(ManagerMapper managerMapper) {
        this.managerMapper = managerMapper;
    }

    @Override
    public PageInfo<ManagerDto> listManagerByName(DataGrid grid, String name, String account) {
        return PageHelper.startPage(grid.getPageNum(), grid.getPageSize()).setOrderBy(grid.getSort() + " " + grid.getOrder())
                .doSelectPageInfo(() -> this.managerMapper.listManagerByName(name, account));
    }

    @Override
    public ManagerDto selectManagerByAccount(String account) {
        return this.managerMapper.selectManagerByAccount(account);
    }


}
