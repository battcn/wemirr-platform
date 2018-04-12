package com.battcn.system.facade;

import com.battcn.framework.mybatis.pojo.DataGrid;
import com.battcn.framework.mybatis.service.BaseService;
import com.battcn.system.pojo.dto.OperateDto;
import com.battcn.system.pojo.dto.ShiroPermission;
import com.battcn.system.pojo.po.Operate;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author Levin
 * @version 2.5.1
 * @since 2018-01-10
 */
public interface OperateService extends BaseService<Operate> {

    /**
     * 根据角色ID和菜单ID查询操作列表
     *
     * @param roleId 角色ID
     * @param menuId 菜单ID
     * @return 查询结果
     */
    List<Operate> listOperateByRoleIdAndMenuId(Integer roleId, Integer menuId);

    /**
     * 分页查询 操作列表
     *
     * @param grid 分页信息
     * @return 查询结果
     */
    PageInfo<OperateDto> listOperateByPage(DataGrid grid);

    /**
     * 根据 角色ID 查询 权限
     *
     * @param roleId 角色ID
     * @return 查询结果
     */
    List<ShiroPermission> listShiroPermissions(Integer roleId);

}
