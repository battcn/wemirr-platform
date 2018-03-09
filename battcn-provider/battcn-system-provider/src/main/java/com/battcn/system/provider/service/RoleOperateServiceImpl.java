package com.battcn.system.provider.service;


import com.battcn.framework.mybatis.service.impl.BaseServiceImpl;
import com.battcn.system.facade.RoleOperateService;
import com.battcn.system.pojo.po.RoleOperate;
import com.battcn.system.provider.mapper.RoleOperateMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Levin
 */
@com.alibaba.dubbo.config.annotation.Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class RoleOperateServiceImpl extends BaseServiceImpl<RoleOperate> implements RoleOperateService {

    private final RoleOperateMapper roleOperateMapper;

    @Autowired
    public RoleOperateServiceImpl(RoleOperateMapper roleOperateMapper) {
        this.roleOperateMapper = roleOperateMapper;
    }

    @Override
    public List<RoleOperate> listRoleOperateByRoleId(Integer roleId) {

        return this.roleOperateMapper.listRoleOperateByRoleId(roleId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchInsertRoleOperate(Integer[] operateIds, Integer roleId) {
        this.roleOperateMapper.delete(new RoleOperate(roleId));
        List<RoleOperate> ops = Lists.newArrayList(operateIds).stream().map(id -> new RoleOperate(roleId, id)).collect(toList());
        int result = this.roleOperateMapper.batchInsertRoleOperate(ops);
    }

}
