package com.wemirr.platform.authority.controller.common;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.wemirr.framework.db.dynamic.TenantDynamicDataSourceHandler;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.db.properties.DatabaseProperties;
import com.wemirr.framework.db.properties.MultiTenantType;
import com.wemirr.framework.websocket.BaseWebSocketEndpoint;
import com.wemirr.platform.authority.domain.common.entity.SiteMessage;
import com.wemirr.platform.authority.domain.tenant.entity.Tenant;
import com.wemirr.platform.authority.repository.tenant.TenantMapper;
import com.wemirr.platform.authority.service.SiteMessageService;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;


/**
 * 聊天室
 *
 * @author Levin
 * @since 2020/11/11
 */
@Slf4j
@Component
@ServerEndpoint(value = "/message/{tenantCode}/{identifier}")
public class SiteMessageEndpoint extends BaseWebSocketEndpoint {

    @OnOpen
    public void openSession(@PathParam("tenantCode") String tenantCode, @PathParam(IDENTIFIER) String userId, Session session) {
        connect(userId, session);
        List<SiteMessage> messages = null;
        final DatabaseProperties properties = SpringUtil.getBean(DatabaseProperties.class);
        final SiteMessageService service = SpringUtil.getBean(SiteMessageService.class);
        if (properties.getMultiTenant().getType() == MultiTenantType.DATASOURCE) {
            log.info("WebSocket 租户编码 - {}", tenantCode);
            final DataSource dataSource = SpringUtil.getBean(DataSource.class);
            DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
            log.info("所有的数据源信息 - {}", ds.getDataSources());
            DynamicDataSourceContextHolder.poll();
            final TenantMapper tenantMapper = SpringUtil.getBean(TenantMapper.class);
            final Long count = tenantMapper.selectCount(Wraps.<Tenant>lbQ().eq(Tenant::getCode, tenantCode));
            if (count != null && count != 0) {
                final TenantDynamicDataSourceHandler dataSourceProcess = SpringUtil.getBean(TenantDynamicDataSourceHandler.class);
                final String dsKey = dataSourceProcess.buildDb(tenantCode);
                log.debug("设置当前线程数据源 - {}", dsKey);
                DynamicDataSourceContextHolder.push(dsKey);
                messages = service.list(Wraps.<SiteMessage>lbQ().eq(SiteMessage::getMark, false)
                        .eq(SiteMessage::getReceiveId, userId).orderByAsc(SiteMessage::getId));
                DynamicDataSourceContextHolder.poll();
                log.debug("清空当前线程数据源...");
            }
        } else {
            messages = service.list(Wraps.<SiteMessage>lbQ().eq(SiteMessage::getMark, false)
                    .eq(SiteMessage::getReceiveId, userId).orderByAsc(SiteMessage::getId));
        }
        if (CollectionUtil.isEmpty(messages)) {
            return;
        }
        messages.forEach(message -> senderMessage(userId, JSON.toJSONString(message)));
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