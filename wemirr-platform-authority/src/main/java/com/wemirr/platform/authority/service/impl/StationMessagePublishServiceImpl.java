package com.wemirr.platform.authority.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.wemirr.framework.commons.StringUtils;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatis.SuperServiceImpl;
import com.wemirr.framework.db.mybatis.conditions.Wraps;
import com.wemirr.framework.websocket.WebSocketManager;
import com.wemirr.platform.authority.domain.entity.baseinfo.Role;
import com.wemirr.platform.authority.domain.entity.baseinfo.User;
import com.wemirr.platform.authority.domain.entity.baseinfo.UserRole;
import com.wemirr.platform.authority.domain.entity.message.StationMessage;
import com.wemirr.platform.authority.domain.entity.message.StationMessagePublish;
import com.wemirr.platform.authority.domain.enums.ReceiverType;
import com.wemirr.platform.authority.domain.vo.CommonDataResp;
import com.wemirr.platform.authority.repository.*;
import com.wemirr.platform.authority.service.StationMessagePublishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author Levin
 */
@Service
@RequiredArgsConstructor
public class StationMessagePublishServiceImpl extends SuperServiceImpl<StationMessagePublishMapper, StationMessagePublish> implements StationMessagePublishService {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final StationMessageMapper stationMessageMapper;
    private final UserRoleMapper userRoleMapper;
    private final WebSocketManager webSocketManager;


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


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void publish(Long id) {
        final StationMessagePublish messagePublish = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("需要发布的消息不存在"));
        final List<Long> receiver = Optional.of(Arrays.stream(messagePublish.getReceiver().split(",")).mapToLong(Long::parseLong).boxed().collect(toList()))
                .orElseThrow(() -> CheckedException.badRequest("接受者不能为空"));
        StationMessagePublish record = new StationMessagePublish();
        record.setId(id);
        record.setStatus(true);
        this.baseMapper.updateById(record);
        final ReceiverType type = messagePublish.getType();
        if (ReceiverType.USER.eq(type)) {
            publish(messagePublish, receiver);
        } else if (ReceiverType.ROLE.eq(type)) {
            final List<UserRole> userRoles = this.userRoleMapper.selectList(Wraps.<UserRole>lbQ().in(UserRole::getRoleId, receiver));
            if (CollectionUtil.isEmpty(userRoles)) {
                return;
            }
            publish(messagePublish, userRoles.stream().mapToLong(UserRole::getUserId).boxed().collect(Collectors.toList()));
        }
    }

    void publish(StationMessagePublish messagePublish, List<Long> userIdList) {
        for (Long userId : userIdList) {
            StationMessage message = new StationMessage();
            message.setTitle(messagePublish.getTitle());
            message.setMark(false);
            message.setContent(messagePublish.getContent());
            message.setDescription(messagePublish.getDescription());
            message.setLevel(messagePublish.getLevel());
            message.setReceiveId(userId);
            message.setCreatedTime(LocalDateTime.now());
            this.stationMessageMapper.insert(message);
            this.webSocketManager.sendMessage(String.valueOf(userId), JSON.toJSONString(message));
        }
    }
}
