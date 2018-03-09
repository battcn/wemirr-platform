package com.battcn.system.provider.service;

import com.battcn.framework.mybatis.service.impl.BaseServiceImpl;
import com.battcn.system.facade.RoleService;
import com.battcn.system.pojo.po.Role;
import com.battcn.system.provider.mapper.RoleMapper;
import com.battcn.system.provider.mapper.RoleOperateMapper;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    private final RoleMapper roleMapper;
    private final RoleOperateMapper roleOperateMapper;

    @Autowired
    public RoleServiceImpl(RoleMapper roleMapper, RoleOperateMapper roleOperateMapper) {
        this.roleMapper = roleMapper;
        this.roleOperateMapper = roleOperateMapper;
    }

    @Override
    public List<Role> listRole() {
        PageHelper.orderBy("ID ASC");
        return roleMapper.selectAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteRoleAndOperate(Integer[] ids) {
        Lists.newArrayList(ids).forEach(id -> {
            super.deleteById(id);
            this.roleOperateMapper.deleteRoleOperateByRoleId(id);
        });
    }

}
