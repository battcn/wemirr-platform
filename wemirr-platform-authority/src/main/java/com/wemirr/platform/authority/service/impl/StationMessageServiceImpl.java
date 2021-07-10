package com.wemirr.platform.authority.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.wemirr.framework.boot.service.impl.SuperServiceImpl;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.times.LocalDateTimeUtils;
import com.wemirr.framework.database.mybatis.conditions.Wraps;
import com.wemirr.framework.websocket.WebSocketManager;
import com.wemirr.platform.authority.domain.dto.StationMessageReq;
import com.wemirr.platform.authority.domain.entity.message.StationMessage;
import com.wemirr.platform.authority.domain.entity.baseinfo.UserRole;
import com.wemirr.platform.authority.domain.enums.ReceiverType;
import com.wemirr.platform.authority.repository.StationMessageMapper;
import com.wemirr.platform.authority.repository.UserRoleMapper;
import com.wemirr.platform.authority.service.StationMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StationMessageServiceImpl extends SuperServiceImpl<StationMessageMapper, StationMessage> implements StationMessageService {

    private final UserRoleMapper userRoleMapper;
    private final WebSocketManager webSocketManager;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pushMessage(StationMessageReq req) {
        final List<Long> receiver = Optional.ofNullable(req.getReceiver())
                .orElseThrow(() -> CheckedException.badRequest("接受者不能为空"));
        final ReceiverType type = req.getReceiverType();
        if (ReceiverType.USER.eq(type)) {
            publish(req, receiver);
        } else if (ReceiverType.ROLE.eq(type)) {
            final List<UserRole> userRoles = this.userRoleMapper.selectList(Wraps.<UserRole>lbQ().in(UserRole::getRoleId, receiver));
            if (CollectionUtil.isEmpty(userRoles)) {
                return;
            }
            publish(req, userRoles.stream()
                    .mapToLong(UserRole::getUserId).boxed()
                    .collect(Collectors.toList()));
        }
    }

    void publish(StationMessageReq req, List<Long> userIdList) {
        for (Long userId : userIdList) {
            StationMessage message = new StationMessage();
            message.setTitle(req.getType());
            message.setMark(false);
            message.setContent(req.getContent());
            message.setTitle(req.getTitle());
            message.setReceiveId(userId);
            this.baseMapper.insert(message);
            JSONObject object = new JSONObject();
            object.put("id", message.getId());
            object.put("title", message.getTitle());
            object.put("content", message.getContent());
            object.put("level", message.getLevel());
            object.put("createdTime", LocalDateTimeUtils.now());
            this.webSocketManager.sendMessage(String.valueOf(userId), object.toJSONString());
        }
    }

}
