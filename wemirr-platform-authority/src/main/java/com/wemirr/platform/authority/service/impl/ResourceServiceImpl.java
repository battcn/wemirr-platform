package com.wemirr.platform.authority.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.authority.domain.baseinfo.entity.Resource;
import com.wemirr.platform.authority.domain.baseinfo.entity.Role;
import com.wemirr.platform.authority.domain.baseinfo.entity.RoleRes;
import com.wemirr.platform.authority.domain.baseinfo.enums.ResourceType;
import com.wemirr.platform.authority.domain.baseinfo.req.ResourceQueryReq;
import com.wemirr.platform.authority.domain.baseinfo.req.ResourceSaveReq;
import com.wemirr.platform.authority.domain.baseinfo.resp.VueRouter;
import com.wemirr.platform.authority.repository.baseinfo.ResourceMapper;
import com.wemirr.platform.authority.repository.baseinfo.RoleMapper;
import com.wemirr.platform.authority.repository.baseinfo.RoleResMapper;
import com.wemirr.platform.authority.service.ResourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;


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
    private final AuthenticationContext authenticationContext;

    @Override
    public List<VueRouter> findVisibleResource(ResourceQueryReq req) {
        return baseMapper.findVisibleResource(req);
    }

    public static final String DEFAULT_PATH = "/system/development/release/tenant_%s";
    public static final String DEFAULT_COMPONENT = "/system/development/build/standard";

    private static final String SPEL = "/";

    @Override
    @DSTransactional
    public void add(ResourceSaveReq req) {
        final Resource resource = BeanUtil.toBean(req, Resource.class);
        if (ResourceType.BUILD_PUBLISH.eq(resource.getType())) {
            resource.setPath(String.format(DEFAULT_PATH, authenticationContext.tenantId()) + SPEL + resource.getModel());
            resource.setComponent(DEFAULT_COMPONENT);
        }
        if (ResourceType.MENU == resource.getType()) {
            if (!StringUtils.startsWith(resource.getPath(), SPEL)) {
                resource.setPath(SPEL + resource.getPath());
            }
        }
        this.baseMapper.insert(resource);
        final List<Role> roles = this.roleMapper.selectList(Wraps.<Role>lbQ().eq(Role::getSuperRole, true)
                .eq(Role::getLocked, false));
        if (CollUtil.isEmpty(roles)) {
            return;
        }
        // 给管理员角色挂载权限
        final List<RoleRes> roleResList = roles.stream()
                .map(role -> RoleRes.builder().roleId(role.getId()).resId(resource.getId()).build())
                .toList();
        roleResMapper.insertBatchSomeColumn(roleResList);
    }

    @Override
    public void edit(Long id, ResourceSaveReq req) {
        final Resource resource = BeanUtil.toBean(req, Resource.class);
        resource.setId(id);
        if (ResourceType.BUILD_PUBLISH.eq(req.getType())) {
            resource.setPath(String.format(DEFAULT_PATH, authenticationContext.tenantId()) + "/" + resource.getModel());
            resource.setComponent(DEFAULT_COMPONENT);
        }
        this.baseMapper.updateById(resource);
    }

    @Override
    @DSTransactional
    public void delete(Long id) {
        final Long count = this.baseMapper.selectCount(Resource::getParentId, id);
        if (count != null && count > 0) {
            throw CheckedException.badRequest("当前节点存在子节点,请先移除子节点");
        }
        // 删除菜单和按钮
        this.baseMapper.deleteById(id);
        // 删除对应的资源权限
        this.roleResMapper.delete(Wraps.<RoleRes>lbQ().eq(RoleRes::getResId, id));
    }
}
