package com.wemirr.platform.authority.repository.baseinfo;

import com.wemirr.framework.db.dynamic.annotation.TenantDS;
import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.authority.domain.baseinfo.entity.RoleRes;
import com.wemirr.platform.authority.domain.baseinfo.resp.RoleResMenuMapperResp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 角色的资源
 * </p>
 *
 * @author Levin
 * @since 2019-07-03
 */
@TenantDS
@Repository
public interface RoleResMapper extends SuperMapper<RoleRes> {


    /**
     * 根据角色ID查询角色资源菜单信息
     *
     * @param roleId 角色ID
     * @return 查询结果
     */
    List<RoleResMenuMapperResp> selectRoleResByRoleId(@Param("roleId") Long roleId);

}
