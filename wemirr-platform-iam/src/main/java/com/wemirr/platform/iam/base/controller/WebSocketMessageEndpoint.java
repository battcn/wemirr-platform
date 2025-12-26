/*
 * Copyright (c) 2023 WEMIRR-PLATFORM Authors. All Rights Reserved.
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wemirr.platform.iam.base.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.wemirr.framework.commons.JacksonUtils;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.db.properties.DatabaseProperties;
import com.wemirr.framework.db.properties.MultiTenantType;
import com.wemirr.framework.db.utils.TenantHelper;
import com.wemirr.framework.websocket.BaseWebSocketEndpoint;
import com.wemirr.platform.iam.base.domain.entity.MessageNotify;
import com.wemirr.platform.iam.base.service.MessageNotifyService;
import com.wemirr.platform.iam.tenant.domain.entity.Tenant;
import com.wemirr.platform.iam.tenant.repository.TenantMapper;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

/**
 * WS消息监听（如果存在安全隐患的话,可以考虑加 Token 鉴权之类的 不过这样的话  如果量大会影响性能,不过一般PC系统无所谓了）
 *
 * @author Levin
 * @since 2020/11/11
 */
@Slf4j
@Component
@ServerEndpoint(value = "/message/{tenantCode}/{identifier}")
public class WebSocketMessageEndpoint extends BaseWebSocketEndpoint {

    @OnOpen
    public void openSession(@PathParam("tenantCode") String tenantCode, @PathParam(IDENTIFIER) String userId,
                            Session session) {
        connect(userId, session);
        List<MessageNotify> messages = null;
        List<String> accessToken = session.getRequestParameterMap().get("accessToken");
        log.debug("access-token => {}", accessToken);
        final DatabaseProperties properties = SpringUtil.getBean(DatabaseProperties.class);
        final MessageNotifyService service = SpringUtil.getBean(MessageNotifyService.class);
        if (properties.getMultiTenant().getType() == MultiTenantType.DATASOURCE) {
            log.info("WebSocket 租户编码 - {}", tenantCode);
            final DataSource dataSource = SpringUtil.getBean(DataSource.class);
            DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
            log.info("所有的数据源信息 - {}", ds.getDataSources());
            DynamicDataSourceContextHolder.poll();
            final TenantMapper tenantMapper = SpringUtil.getBean(TenantMapper.class);
            final Long count = tenantMapper.selectCount(Wraps.<Tenant>lbQ().eq(Tenant::getCode, tenantCode));
            if (count != null && count != 0) {
                messages = TenantHelper.executeWithTenantDb(tenantCode, () -> service.list(Wraps.<MessageNotify>lbQ().eq(MessageNotify::getUserId, userId).orderByAsc(MessageNotify::getId)));
            }
        } else {
            messages = service.list(Wraps.<MessageNotify>lbQ().eq(MessageNotify::getUserId, userId).orderByAsc(MessageNotify::getId));
        }
        if (CollectionUtil.isEmpty(messages)) {
            return;
        }
        messages.forEach(message -> senderMessage(userId, JacksonUtils.toJson(message)));
    }

    @OnMessage
    public void onMessage(@PathParam(IDENTIFIER) String userId, Session session, String message) {
        log.info("接收到的消息" + message);
    }

    @OnClose
    public void onClose(@PathParam(IDENTIFIER) String userId, Session session) {
        disconnect(userId);
    }

    @OnError
    public void onError(@PathParam(IDENTIFIER) String userId, Session session, Throwable throwable) {
        log.info("发生异常：, identifier {} ", userId);
        log.error(throwable.getMessage(), throwable);
        disconnect(userId);
    }

}
