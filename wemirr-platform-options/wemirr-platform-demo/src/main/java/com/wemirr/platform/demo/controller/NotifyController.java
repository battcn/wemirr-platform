package com.wemirr.platform.demo.controller;

import com.wemirr.framework.robot.message.push.RobotMessageHandler;
import com.wemirr.framework.security.configuration.client.annotation.IgnoreAuthorize;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 通知测试
 *
 * @author Levin
 */
@Slf4j
@RestController
@RequestMapping("/notify")
@RequiredArgsConstructor
public class NotifyController {

    private final List<RobotMessageHandler> robotMessageHandlerList;
    @GetMapping("/test1")
    @IgnoreAuthorize
    public void list() {
        for (RobotMessageHandler handler : robotMessageHandlerList) {
            handler.notify("测试消息");
        }
    }


}
