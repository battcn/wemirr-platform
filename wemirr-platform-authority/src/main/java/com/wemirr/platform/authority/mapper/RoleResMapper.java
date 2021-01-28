package com.wemirr.platform.authority.mapper;

import com.wemirr.framework.boot.SuperMapper;
import com.wemirr.platform.authority.domain.entity.RoleRes;
import com.wemirr.platform.authority.domain.vo.RoleResMenuMapperVo;
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
@Repository
public interface RoleResMapper extends SuperMapper<RoleRes> {


    /**
     * 根据角色ID查询角色资源菜单信息
     *
     * @param roleId 角色ID
     * @return 查询结果
     */
    List<RoleResMenuMapperVo> selectRoleResByRoleId(@Param("roleId") Long roleId);

}
