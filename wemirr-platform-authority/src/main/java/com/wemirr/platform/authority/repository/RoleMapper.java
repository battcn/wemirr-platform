package com.wemirr.platform.authority.repository;

import com.wemirr.framework.boot.SuperMapper;
import com.wemirr.framework.database.configuration.dynamic.ann.DynamicDS;
import com.wemirr.framework.database.mybatis.auth.DataScope;
import com.wemirr.platform.authority.domain.entity.baseinfo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Levin
 */
@DynamicDS
@Repository
public interface RoleMapper extends SuperMapper<Role> {


    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 查询结果
     */
    List<Role> findRoleByUserId(Long userId);

    /**
     * 根据范围查询角色
     *
     * @param scope scope
     * @return 查询结果
     */
    List<Role> list(DataScope scope);
}
