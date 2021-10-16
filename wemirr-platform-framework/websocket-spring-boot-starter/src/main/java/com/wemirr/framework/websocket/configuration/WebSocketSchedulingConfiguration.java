package com.wemirr.framework.websocket.configuration;

import com.wemirr.framework.websocket.WebSocketManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


/**
 * 只需要配置一下三个选项即可开启心跳监测
 * webSocket.heartCheck.enabled=true
 * webSocket.heartCheck.timeSpan=1000
 * webSocket.heartCheck.errorToleration=30
 *
 * @author Levin
 * 这是一个配置模板
 */
@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "spring.websocket.heart-check", name = "enabled", havingValue = "true")
@EnableConfigurationProperties(WebSocketProperties.class)
public class WebSocketSchedulingConfiguration {

    private final WebSocketProperties properties;
    private final WebSocketManager webSocketManager;
    private final WebSocketHeartBeatChecker webSocketHeartBeatChecker;

    /**
     * 定时检测 WebSocket 的心跳
     */
    @Scheduled(cron = "${spring.websocket.heart-check.trigger}")
    public void webSocketHeartCheckJob() {
        webSocketHeartBeatChecker.check(webSocketManager, properties.getTimeSpan(), properties.getErrorToleration(), (webSocket) -> {
            log.info("[心跳检测] - {}", webSocket.toString());
        });
    }
}
