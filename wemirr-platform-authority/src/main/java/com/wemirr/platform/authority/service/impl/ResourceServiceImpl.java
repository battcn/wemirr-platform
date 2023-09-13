package com.wemirr.platform.authority.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.google.common.collect.Lists;
import com.wemirr.framework.commons.entity.Entity;
import com.wemirr.framework.db.TenantEnvironment;
import com.wemirr.framework.db.mybatis.SuperServiceImpl;
import com.wemirr.framework.db.mybatis.conditions.Wraps;
import com.wemirr.platform.authority.domain.entity.baseinfo.Resource;
import com.wemirr.platform.authority.domain.entity.baseinfo.Role;
import com.wemirr.platform.authority.domain.entity.baseinfo.RoleRes;
import com.wemirr.platform.authority.domain.enums.ResourceType;
import com.wemirr.platform.authority.domain.req.ResourceQueryReq;
import com.wemirr.platform.authority.domain.resp.VueRouter;
import com.wemirr.platform.authority.repository.ResourceMapper;
import com.wemirr.platform.authority.repository.RoleMapper;
import com.wemirr.platform.authority.repository.RoleResMapper;
import com.wemirr.platform.authority.service.ResourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private final RoleResMapper roleResMapper;
    private final TenantEnvironment tenantEnvironment;

    @Override
    public List<VueRouter> findVisibleResource(ResourceQueryReq resource) {
        return baseMapper.findVisibleResource(resource);
    }

    public static final String DEFAULT_PATH = "/system/development/release/tenant_%s";
    public static final String DEFAULT_COMPONENT = "/system/development/build/standard";

    private static final String SPEL = "/";

    @Override
    @DSTransactional
    public void addResource(Resource resource) {
        if (ResourceType.BUILD_PUBLISH.eq(resource.getType())) {
            resource.setPath(String.format(DEFAULT_PATH, tenantEnvironment.tenantId()) + SPEL + resource.getModel());
            resource.setComponent(DEFAULT_COMPONENT);
        }
        if (ResourceType.MENU == resource.getType()) {
            if (!StringUtils.startsWith(resource.getPath(), SPEL)) {
                resource.setPath(SPEL + resource.getPath());
            }
        }
        final String treePath = this.baseMapper.getTreePathByParentId(resource.getParentId());
        if (StringUtils.isNotBlank(treePath)) {
            resource.setTreePath(treePath);
        }
        this.baseMapper.insert(resource);
        final List<Role> roles = this.roleMapper.selectList(Wraps.<Role>lbQ().eq(Role::getSuperRole, true)
                .eq(Role::getLocked, false));
        if (CollUtil.isEmpty(roles)) {
            return;
        }
        // 给管理员角色挂载权限
        for (Role role : roles) {
            RoleRes bean = new RoleRes();
            bean.setResId(resource.getId());
            bean.setRoleId(role.getId());
            roleResMapper.insert(bean);
        }
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
    @DSTransactional
    public void delResource(Long resourceId) {
        List<Long> resourceIds = Lists.newArrayList(resourceId);
        final List<Resource> children = this.baseMapper.findChildrenById(resourceId);
        if (CollectionUtil.isNotEmpty(children)) {
            resourceIds.addAll(children.stream().map(Entity::getId).collect(toList()));
        }
        // 删除菜单和按钮
        this.baseMapper.deleteBatchIds(resourceIds);
        // 删除对应的资源权限
        this.roleResMapper.delete(Wraps.<RoleRes>lbQ().in(RoleRes::getResId, resourceIds));
    }
}
