package com.wemirr.platform.demo.controller;

import com.wemirr.framework.robot.message.push.RobotMessageHandler;
import com.wemirr.framework.security.configuration.client.annotation.IgnoreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/robot_notify")
@RequiredArgsConstructor
@Tag(name = "机器人消息", description = "机器人消息")
public class RobotNotifyController {

    private final List<RobotMessageHandler> robotMessageHandlerList;

    @GetMapping("/test1")
    @IgnoreAuthorize
    @Operation(summary = "测试消息发送")
    public void list(String message) {
        for (RobotMessageHandler handler : robotMessageHandlerList) {
            handler.notify(message);
        }
    }


}
