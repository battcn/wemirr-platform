package com.battcn.system.provider.mapper;

import com.battcn.framework.mybatis.mapper.BaseMapper;
import com.battcn.system.pojo.dto.OperateDto;
import com.battcn.system.pojo.dto.PermissionDto;
import com.battcn.system.pojo.dto.ShiroPermission;
import com.battcn.system.pojo.po.Operate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Levin
 * @version 2.5.1
 * @since 2018-01-10
 */
@Mapper
@Repository
public interface OperateMapper extends BaseMapper<Operate> {

    /**
     * 根据角色ID和菜单ID查询操作列表
     *
     * @param roleId 角色ID
     * @param menuId 菜单ID
     * @return 查询结果
     */
    @Select("SELECT t2.* FROM t_sys_role_operate t1 JOIN t_sys_operate t2 ON t1.operate_id = t2.id AND t1.role_id = #{roleId} AND t2.menu_id = #{menuId}")
    List<Operate> listOperateByRoleIdAndMenuId(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);

    /**
     * 分页查询 操作列表
     *
     * @return 查询结果
     */
    @Select("SELECT t.*,m.name AS menuName FROM t_sys_operate t JOIN t_sys_menu m ON m.id = t.menu_id")
    List<OperateDto> listOperateByPage();

    /**
     * 查询所有权限
     *
     * @return 查询结果
     */
    @Select("SELECT t1.id,t2.id AS menuId,t1.name,t2.name AS menuName,t2.pater_id,t1.op FROM t_sys_operate t1 JOIN t_sys_menu t2 ON t1.menu_id = t2.id ")
    List<PermissionDto> listPermissions();

    /**
     * 根据 角色ID 查询 权限
     *
     * @param roleId 角色ID
     * @return 查询结果
     */
    List<ShiroPermission> listShiroPermissionByRoleId(@Param("roleId") Integer roleId);

}