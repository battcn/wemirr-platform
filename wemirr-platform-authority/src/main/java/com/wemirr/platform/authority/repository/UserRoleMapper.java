package com.wemirr.platform.authority.repository;

import com.wemirr.framework.db.configuration.dynamic.ann.DynamicDS;
import com.wemirr.framework.db.mybatis.SuperMapper;
import com.wemirr.platform.authority.domain.entity.baseinfo.UserRole;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 角色分配
 * 账号角色绑定
 * </p>
 *
 * @author Levin
 * @since 2019-07-03
 */
@DynamicDS
@Repository
public interface UserRoleMapper extends SuperMapper<UserRole> {


}
