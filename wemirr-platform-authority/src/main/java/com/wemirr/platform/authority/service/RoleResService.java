package com.wemirr.platform.authority.service;

import com.wemirr.framework.db.mybatis.SuperService;
import com.wemirr.platform.authority.domain.entity.baseinfo.RoleRes;
import com.wemirr.platform.authority.domain.req.RoleResSaveReq;
import com.wemirr.platform.authority.domain.req.UserRoleSaveReq;
import com.wemirr.platform.authority.domain.resp.RoleResResp;

/**
 * <p>
 * 业务接口
 * 角色的资源
 * </p>
 *
 * @author Levin
 * @since 2019-07-03
 */
public interface RoleResService extends SuperService<RoleRes> {

    /**
     * 给用户分配角色
     *
     * @param userRole userRole
     * @return 保存结果
     */
    boolean saveUserRole(UserRoleSaveReq userRole);

    /**
     * 给角色重新分配 权限（资源/菜单）
     *
     * @param roleResSaveReq
     * @return
     */
    void saveRoleAuthority(RoleResSaveReq roleResSaveReq);

    /**
     * 根据角色id查询资源
     *
     * @param id id
     * @return 查询结果
     */
    RoleResResp findAuthorityIdByRoleId(Long id);
}
