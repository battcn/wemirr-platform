package com.battcn.system.provider.mapper;

import com.battcn.system.pojo.po.Menu;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Levin
 */
@Mapper
@Repository
public interface AuthMapper {

    /**
     * 根据角色ID查询可操作的菜单
     *
     * @param roleId 角色ID
     * @return 查询结果
     */
    List<Menu> listMenuByRoleId(@Param("roleId") Integer roleId);
}
