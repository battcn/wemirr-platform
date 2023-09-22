package com.wemirr.platform.authority.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.authority.domain.entity.baseinfo.RoleRes;
import com.wemirr.platform.authority.domain.entity.baseinfo.UserRole;
import com.wemirr.platform.authority.domain.req.RoleResSaveReq;
import com.wemirr.platform.authority.domain.req.UserRoleSaveReq;
import com.wemirr.platform.authority.domain.resp.RoleResMenuMapperResp;
import com.wemirr.platform.authority.domain.resp.RoleResResp;
import com.wemirr.platform.authority.repository.RoleResMapper;
import com.wemirr.platform.authority.service.RoleResService;
import com.wemirr.platform.authority.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * <p>
 * 业务实现类
 * 角色的资源
 * </p>
 *
 * @author Levin
 * @since 2019-07-03
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RoleResServiceImpl extends SuperServiceImpl<RoleResMapper, RoleRes> implements RoleResService {

    private final UserRoleService userRoleService;

    @Override
    public RoleResResp findAuthorityIdByRoleId(Long roleId) {
        final List<RoleResMenuMapperResp> list = this.baseMapper.selectRoleResByRoleId(roleId);
        List<Long> menuIdList = list.stream().filter(xx -> xx.getType() == 1 || xx.getType() == 5)
                .mapToLong(RoleResMenuMapperResp::getId).boxed().distinct().collect(Collectors.toList());
        List<Long> resourceIdList = list.stream().filter(xx -> xx.getType() == 2)
                .mapToLong(RoleResMenuMapperResp::getId).boxed().distinct().collect(Collectors.toList());
        return RoleResResp.builder()
                .menuIdList(menuIdList)
                .resourceIdList(resourceIdList)
                .build();
    }


    @Override
    public boolean saveUserRole(UserRoleSaveReq userRole) {
        userRoleService.remove(Wraps.<UserRole>lbQ().eq(UserRole::getRoleId, userRole.getRoleId()));
        List<UserRole> list = userRole.getUserIdList()
                .stream()
                .map((userId) -> UserRole.builder()
                        .userId(userId)
                        .roleId(userRole.getRoleId())
                        .build())
                .collect(Collectors.toList());
        userRoleService.saveBatch(list);
        return true;
    }

    @Override
    @DSTransactional
    public void saveRoleAuthority(RoleResSaveReq dto) {
        //删除角色和资源的关联
        super.remove(Wraps.<RoleRes>lbQ().eq(RoleRes::getRoleId, dto.getRoleId()));
        resHandler(dto, dto.getRoleId());
    }

    private void resHandler(RoleResSaveReq data, Long roleId) {
        final Set<Long> set = data.getResIds();
        if (CollectionUtil.isEmpty(set)) {
            return;
        }
        final List<RoleRes> roleRes = set.stream().filter(Objects::nonNull)
                .map(resId -> RoleRes.builder().resId(resId).roleId(roleId).build())
                .collect(toList());
        super.insertBatch(roleRes);
    }
}
