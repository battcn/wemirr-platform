package com.wemirr.platform.authority.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.wemirr.framework.commons.entity.Entity;
import com.wemirr.framework.commons.security.DataPermission;
import com.wemirr.framework.commons.security.DataResourceType;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.authority.domain.baseinfo.entity.DataPermissionResource;
import com.wemirr.platform.authority.domain.baseinfo.entity.Org;
import com.wemirr.platform.authority.domain.baseinfo.entity.Role;
import com.wemirr.platform.authority.domain.baseinfo.entity.User;
import com.wemirr.platform.authority.repository.baseinfo.DataPermissionResourceMapper;
import com.wemirr.platform.authority.repository.baseinfo.OrgMapper;
import com.wemirr.platform.authority.repository.baseinfo.RoleMapper;
import com.wemirr.platform.authority.repository.baseinfo.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

import static com.wemirr.framework.commons.security.DataScopeType.*;

/**
 * @author levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DataScopeService {

    private final RoleMapper roleMapper;
    private final DataPermissionResourceMapper dataPermissionResourceMapper;
    private final UserMapper userMapper;
    private final OrgMapper orgMapper;

    /**
     * 开发者可以根据自己企业需求动态扩展数据权限（默认就支撑多维度数据权限，此处以用户维护演示）
     *
     * @param userId 用户ID
     * @param orgId  用户当前机构
     * @return 数据权限
     */
    public DataPermission getDataPermissionById(Long userId, Long orgId) {
        List<Role> list = roleMapper.findRoleByUserId(userId);
        if (CollectionUtils.isEmpty(list)) {
            return DataPermission.builder().build();
        }
        // 找到 dsType 最大的角色， dsType越大，角色拥有的权限最大
        Role role = list.stream().max(Comparator.comparingInt((item) -> item.getScopeType().getType())).get();
        DataPermission permission = DataPermission.builder().scopeType(role.getScopeType()).build();
        List<Long> userIdList = null;
        if (role.getScopeType() == CUSTOMIZE) {
            List<Long> orgIdList = dataPermissionResourceMapper.selectList(Wraps.<DataPermissionResource>lbQ().select(DataPermissionResource::getDataId)
                            .eq(DataPermissionResource::getOwnerId, role.getId())
                            .eq(DataPermissionResource::getOwnerType, DataResourceType.ROLE)
                            .eq(DataPermissionResource::getDataType, DataResourceType.ORG))
                    .stream().map(DataPermissionResource::getDataId).distinct().toList();
            userIdList = this.userMapper.selectList(Wraps.<User>lbQ().select(User::getId).in(User::getOrgId, orgIdList))
                    .stream().map(Entity::getId).toList();
        } else if (role.getScopeType() == THIS_LEVEL) {
            userIdList = this.userMapper.selectList(Wraps.<User>lbQ().select(User::getId).eq(User::getOrgId, orgId))
                    .stream().map(Entity::getId).toList();
        } else if (role.getScopeType() == THIS_LEVEL_CHILDREN) {
            final List<Long> orgIdList = findChildren(orgId);
            userIdList = this.userMapper.selectList(Wraps.<User>lbQ().select(User::getId).in(User::getOrgId, orgIdList))
                    .stream().map(Entity::getId).toList();
        }
        if (userIdList != null) {
            permission.getDataPermissionMap().put(DataResourceType.USER, Collections.singletonList(userIdList));
        }
        // 如果你还有其他维度可以自行扩展
        return permission;
    }

    public List<Long> findChildren(Long id) {
        List<Long> children = new LinkedList<>();
        // 遍历每一层
        Collection<Long> parentIds = Collections.singleton(id);
        // 避免 bug 场景下，存在死循环
        for (int i = 0; i < Short.MAX_VALUE; i++) {
            // 查询当前层，所有的子集
            List<Org> orgList = orgMapper.selectList(Wraps.<Org>lbQ().select(Org::getId).eq(Org::getParentId, parentIds));
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
