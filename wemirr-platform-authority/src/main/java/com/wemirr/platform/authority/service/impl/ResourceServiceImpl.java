package com.wemirr.platform.authority.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import com.wemirr.framework.boot.service.impl.SuperServiceImpl;
import com.wemirr.framework.database.datasource.TenantEnvironment;
import com.wemirr.framework.database.mybatis.conditions.Wraps;
import com.wemirr.platform.authority.domain.dto.ResourceQueryDTO;
import com.wemirr.platform.authority.domain.entity.Resource;
import com.wemirr.platform.authority.domain.entity.Role;
import com.wemirr.platform.authority.domain.entity.RoleRes;
import com.wemirr.platform.authority.domain.enums.ResourceType;
import com.wemirr.platform.authority.domain.vo.RolePermissionResp;
import com.wemirr.platform.authority.domain.vo.VueRouter;
import com.wemirr.platform.authority.repository.ResourceMapper;
import com.wemirr.platform.authority.repository.RoleMapper;
import com.wemirr.platform.authority.repository.RoleResMapper;
import com.wemirr.platform.authority.service.ResourceService;
import com.wemirr.platform.authority.service.RoleResService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;


/**
 * <p>
 * 业务实现类
 * 资源
 * </p>
 *
 * @author Levin
 * @since 2019-07-03
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ResourceServiceImpl extends SuperServiceImpl<ResourceMapper, Resource> implements ResourceService {

    private final RoleMapper roleMapper;
    private final RoleResService roleResService;
    private final RoleResMapper roleResMapper;
    private final TenantEnvironment tenantEnvironment;

    @Override
    public List<VueRouter> findVisibleResource(ResourceQueryDTO resource) {
        return baseMapper.findVisibleResource(resource);
    }

    public static final String DEFAULT_PATH = "/system/development/release/tenant_%s";
    public static final String DEFAULT_COMPONENT = "/system/development/build/standard";


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addResource(Resource resource) {
        if (resource.getParentId() == null || resource.getParentId() == 0L) {
            resource.setComponent("layoutHeaderAside");
            resource.setPath(String.format(DEFAULT_PATH, tenantEnvironment.tenantId()));
        }
        if (ResourceType.BUILD_PUBLISH.eq(resource.getType())) {
            resource.setPath(String.format(DEFAULT_PATH, tenantEnvironment.tenantId()) + "/" + resource.getModel());
            resource.setComponent(DEFAULT_COMPONENT);
        }
        baseMapper.insert(resource);
        final List<Role> roles = this.roleMapper.selectList(Wraps.<Role>lbQ()
                .eq(Role::getSuperRole, true)
                .eq(Role::getLocked, false));
        if (CollUtil.isEmpty(roles)) {
            return;
        }
        Long resId = resource.getId();
        // 给管理员角色挂载权限
        final List<RoleRes> resList = roles.stream().map(role -> RoleRes.builder()
                .roleId(role.getId()).resId(resId).build())
                .collect(toList());
        roleResService.saveBatch(resList);
    }

    @Override
    public void editResourceById(Resource resource) {
        if (ResourceType.BUILD_PUBLISH.eq(resource.getType())) {
            resource.setPath(String.format(DEFAULT_PATH, tenantEnvironment.tenantId()) + "/" + resource.getModel());
            resource.setComponent(DEFAULT_COMPONENT);
        }
        this.baseMapper.updateById(resource);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delResource(Long resourceId) {
        baseMapper.deleteById(resourceId);
        // 删除对应的资源权限
        this.roleResMapper.delete(Wraps.<RoleRes>lbQ()
                .eq(RoleRes::getResId, resourceId));
    }
}
