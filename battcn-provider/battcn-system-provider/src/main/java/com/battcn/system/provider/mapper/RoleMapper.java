package com.battcn.system.provider.mapper;

import com.battcn.framework.mybatis.mapper.BaseMapper;
import com.battcn.system.pojo.dto.RoleOperateDto;
import com.battcn.system.pojo.po.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Levin
 */
@Mapper
@Repository
public interface RoleMapper extends BaseMapper<Role> {


    /**
     * 根据角色ID查询角色操作项
     *
     * @param roleId 角色ID
     * @return 查询结果
     */
    List<RoleOperateDto> listRoleOperateById(@Param("roleId") Integer roleId);

}