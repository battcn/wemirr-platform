package com.battcn.system.facade;

import com.battcn.framework.mybatis.service.BaseService;
import com.battcn.system.pojo.po.Role;

import java.util.List;

/**
 * @author Levin
 */
public interface RoleService extends BaseService<Role> {

    /**
     * 查询所有角色,根据ID排序
     *
     * @return 查询结果
     */
    List<Role> listRole();

    /**
     * 批量删除
     *
     * @param ids ID集
     */
    void deleteRoleAndOperate(Integer[] ids);

}
