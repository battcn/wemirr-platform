package com.wemirr.platform.authority.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.wemirr.framework.boot.service.impl.SuperServiceImpl;
import com.wemirr.framework.commons.StringUtils;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.database.mybatis.conditions.Wraps;
import com.wemirr.platform.authority.domain.entity.baseinfo.Role;
import com.wemirr.platform.authority.domain.entity.message.StationMessagePublish;
import com.wemirr.platform.authority.domain.entity.baseinfo.User;
import com.wemirr.platform.authority.domain.enums.ReceiverType;
import com.wemirr.platform.authority.domain.vo.CommonDataResp;
import com.wemirr.platform.authority.repository.RoleMapper;
import com.wemirr.platform.authority.repository.StationMessagePublishMapper;
import com.wemirr.platform.authority.repository.UserMapper;
import com.wemirr.platform.authority.service.StationMessagePublishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Levin
 */
@Service
@RequiredArgsConstructor
public class StationMessagePublishServiceImpl extends SuperServiceImpl<StationMessagePublishMapper, StationMessagePublish> implements StationMessagePublishService {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @Override
    public List<CommonDataResp> queryReceiverByType(ReceiverType type, String search) {
        if (type == null) {
            throw CheckedException.notFound("类型不能为空");
        }
        if (type == ReceiverType.USER) {
            final List<User> users = userMapper.selectList(Wraps.<User>lbQ().eq(User::getStatus, 1)
                    .and(StringUtils.isNotBlank(search), wrapper -> wrapper.like(User::getNickName, search).or().like(User::getUsername, search)));
            if (CollectionUtil.isEmpty(users)) {
                return null;
            }
            return users.stream().map(user -> CommonDataResp.builder().id(user.getId()).name(user.getNickName()).build()).collect(toList());
        }
        final List<Role> roles = roleMapper.selectList(Wraps.<Role>lbQ().eq(Role::getLocked, false)
                .like(Role::getName, search).or().like(Role::getCode, search));
        if (CollectionUtil.isEmpty(roles)) {
            return null;
        }
        return roles.stream().map(role -> CommonDataResp.builder().id(role.getId()).name(role.getName()).build()).collect(toList());
    }
}
