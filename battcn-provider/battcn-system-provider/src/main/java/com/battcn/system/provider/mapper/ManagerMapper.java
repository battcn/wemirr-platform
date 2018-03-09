package com.battcn.system.provider.mapper;

import com.battcn.framework.mybatis.mapper.BaseMapper;
import com.battcn.system.pojo.dto.ManagerDto;
import com.battcn.system.pojo.po.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Levin
 */
@Mapper
@Repository
public interface ManagerMapper extends BaseMapper<Manager> {

    /**
     * 根据条件查询角色信息
     *
     * @param name    名称
     * @param account 账号
     * @return
     */
    List<ManagerDto> listManagerByName(@Param("name") String name, @Param("account") String account);

    /**
     * 根据账号查询角色信息
     *
     * @param account 账号
     * @return 角色信息
     */
    @Select("SELECT t1.*,t2.name roleName,t2.code roleCode FROM t_sys_manager t1 JOIN t_sys_role t2 ON t2.id = t1.role_id WHERE account = #{account}")
    ManagerDto selectManagerByAccount(@Param("account") String account);

}