package com.wemirr.platform.authority.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.wemirr.framework.commons.entity.Entity;
import com.wemirr.framework.db.mybatisplus.intercept.data.DataPermission;
import com.wemirr.framework.db.mybatisplus.intercept.data.DataScopeService;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.security.domain.UserInfoDetails;
import com.wemirr.framework.security.utils.SecurityUtils;
import com.wemirr.platform.authority.domain.entity.baseinfo.Org;
import com.wemirr.platform.authority.domain.entity.baseinfo.Role;
import com.wemirr.platform.authority.domain.entity.baseinfo.RoleOrg;
import com.wemirr.platform.authority.domain.entity.baseinfo.User;
import com.wemirr.platform.authority.repository.OrgMapper;
import com.wemirr.platform.authority.repository.RoleMapper;
import com.wemirr.platform.authority.repository.RoleOrgMapper;
import com.wemirr.platform.authority.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.wemirr.framework.db.mybatisplus.intercept.data.DataScopeType.*;

/**
 * @author levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DataScopeServiceImpl implements DataScopeService {

    private final RoleMapper roleMapper;
    private final RoleOrgMapper roleOrgMapper;
    private final UserMapper userMapper;
    private final OrgMapper orgMapper;

    @Override
    @Cached(expire = 30, timeUnit = TimeUnit.MINUTES, cacheType = CacheType.LOCAL)
    public DataPermission getDataScopeById(Long userId) {
        // 考虑添加缓存实现,减少DB IO访问次数
        DataPermission scope = DataPermission.builder().userId(userId).build();
        List<Long> orgIds = new ArrayList<>();
        // 计划后续在登录的时候从上下文提取这样性能更高
        List<Role> list = roleMapper.findRoleByUserId(userId);
        if (CollectionUtils.isEmpty(list)) {
            return scope;
        }
        // 找到 dsType 最大的角色， dsType越大，角色拥有的权限最大
        Role role = list.stream().max(Comparator.comparingInt((item) -> item.getScopeType().getType())).get();
        scope.setScopeType(role.getScopeType());
        if (role.getScopeType() == CUSTOMIZE) {
            List<Long> roleOrgIds = roleOrgMapper.selectList(Wraps.<RoleOrg>lbQ().select(RoleOrg::getOrgId)
                    .eq(RoleOrg::getRoleId, role.getId())).stream().map(RoleOrg::getOrgId).distinct().toList();
            orgIds.addAll(roleOrgIds);
        } else if (role.getScopeType() == THIS_LEVEL) {
            orgIds.add(getUserOrgId(userId));
        } else if (role.getScopeType() == THIS_LEVEL_CHILDREN) {
            orgIds.addAll(findChildren(getUserOrgId(userId)));
        }
        scope.setOrgIds(orgIds);
        return scope;
    }

    private Long getUserOrgId(Long userId) {
        final UserInfoDetails details = SecurityUtils.getAuthInfo();
        if (ObjUtil.equal(userId, details.getUserId())) {
            //说明是当前登录人,就不查询数据库了,直接用token 里面自带的
            return details.getOrgId();
        }
        final User user = this.userMapper.selectById(userId);
        if (user == null) {
            return null;
        }
        return user.getOrgId();
    }

    public List<Long> findChildren(Long id) {
        List<Long> children = new LinkedList<>();
        // 遍历每一层
        Collection<Long> parentIds = Collections.singleton(id);
        for (int i = 0; i < Short.MAX_VALUE; i++) { // 避免 bug 场景下，存在死循环
            // 查询当前层，所有的子集
            List<Org> orgList = orgMapper.selectList(Wraps.<Org>lbQ().eq(Org::getParentId, parentIds));
            // 没有子集,结束遍历
            if (CollUtil.isEmpty(orgList)) {
                break;
            }
            parentIds = orgList.stream().map(Entity::getId).toList();
        }
        children.add(id);
        return children.stream().distinct().toList();
    }


}
