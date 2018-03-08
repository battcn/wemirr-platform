package com.battcn.system.provider.mapper;

import com.battcn.framework.mybatis.mapper.BaseMapper;
import com.battcn.system.pojo.po.RoleOperate;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
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
public interface RoleOperateMapper extends BaseMapper<RoleOperate> {

    /**
     * 根据角色ID查询 角色操作信息
     *
     * @param roleId 角色ID
     * @return 查询结果
     */
    @Select("SELECT * FROM	t_sys_role_operate WHERE role_id = #{roleId} ")
    List<RoleOperate> listRoleOperateByRoleId(Integer roleId);

    /**
     * 批量保存 角色操作信息
     *
     * @param ops 数据集
     * @return 操作结果
     */
    int batchInsertRoleOperate(List<RoleOperate> ops);

    /**
     * 根据角色ID删除 角色操作信息
     *
     * @param roleId 角色ID
     */
    @Delete("DELETE FROM t_sys_role_operate WHERE role_id = #{roleId}")
    void deleteRoleOperateByRoleId(Integer roleId);


}