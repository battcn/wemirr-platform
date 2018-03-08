package com.battcn.system.provider.service;


import com.battcn.framework.mybatis.pojo.DataGrid;
import com.battcn.framework.mybatis.service.impl.BaseServiceImpl;
import com.battcn.system.facade.OperateService;
import com.battcn.system.pojo.dto.OperateDto;
import com.battcn.system.pojo.dto.ShiroPermission;
import com.battcn.system.pojo.po.Operate;
import com.battcn.system.provider.mapper.OperateMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * @author Levin
 */
@com.alibaba.dubbo.config.annotation.Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class OperateServiceImpl extends BaseServiceImpl<Operate> implements OperateService {

    private final OperateMapper operateMapper;

    @Autowired
    public OperateServiceImpl(OperateMapper operateMapper) {
        this.operateMapper = operateMapper;
    }

    @Override
    public List<Operate> listOperateByRoleIdAndMenuId(Integer roleId, Integer menuId) {
        return this.operateMapper.listOperateByRoleIdAndMenuId(roleId, menuId);

    }

    @Override
    public PageInfo<OperateDto> listOperateByPage(DataGrid grid) {
        return PageHelper.startPage(grid.getPageNum(), grid.getPageSize()).setOrderBy(grid.getSort() + " " + grid.getOrder())
                .doSelectPageInfo(this.operateMapper::listOperateByPage);
    }

    @Override
    public List<ShiroPermission> listShiroPermissions(Integer roleId) {
        return this.operateMapper.listShiroPermissionByRoleId(roleId);
    }

}
