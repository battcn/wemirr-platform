package com.battcn.system.provider.mapper;

import com.battcn.framework.mybatis.mapper.BaseMapper;
import com.battcn.system.pojo.dto.OperateDto;
import com.battcn.system.pojo.dto.PermissionDto;
import com.battcn.system.pojo.dto.ShiroPermission;
import com.battcn.system.pojo.po.Operate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
    List<Operate> listOperateByRoleIdAndMenuId(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);

    /**
     * 分页查询 操作列表
     *
     * @return 查询结果
     */
    List<OperateDto> listOperate();

    /**
     * 查询所有权限
     *
     * @return 查询结果
     */
    List<PermissionDto> listPermissions();

    /**
     * 根据 角色ID 查询 权限
     *
     * @param roleId 角色ID
     * @return 查询结果
     */
    List<ShiroPermission> listShiroPermissionByRoleId(@Param("roleId") Integer roleId);

}