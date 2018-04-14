package com.battcn.system.facade;



import com.battcn.framework.mybatis.pojo.DataGrid;
import com.battcn.framework.mybatis.service.BaseService;
import com.battcn.system.pojo.dto.PermissionDto;
import com.battcn.system.pojo.dto.TreeNode;
import com.battcn.system.pojo.po.Menu;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author Levin
 * @since 2018-01-10
 */
public interface MenuService extends BaseService<Menu> {

    /**
     * 分页查询 菜单列表
     *
     * @param grid 分页信息
     * @return 查询结果
     */
    PageInfo<Menu> listMenuForDataGrid(DataGrid grid);

    /**
     * 根据角色ID获取树形结构的菜单数据
     *
     * @param roleId 角色ID
     * @return 查询结果
     */
    List<TreeNode> listTree(Integer roleId);

    /**
     * 查询所有权限信息
     *
     * @return 查询结果
     */
    List<PermissionDto> listPermissions();

    /**
     * 查询所有菜单
     *
     * @return 查询结果
     */
    List<Menu> listMenu();

    /**
     * 根据菜单编号判断 添加/保存 菜单信息
     *
     * @param menu 菜单
     */
    void saveOrUpdate(Menu menu);

}
