package com.wemirr.platform.ai.controller;

import com.wemirr.framework.security.configuration.client.annotation.IgnoreFeignAuthorize;
import com.wemirr.platform.ai.domain.dto.req.AskReq;
import com.wemirr.platform.ai.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * 普通对话控制器
 * <p>
 * 提供基础的 AI 对话能力，支持流式输出和会话记忆
 *
 * @author Levin
 * @since 2025-10
 */
@Validated
@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
@Tag(name = "普通对话", description = "基础 AI 对话接口")
public class ChatController {

    private final ChatService chatService;

    /**
     * 流式对话
     * <p>
     * 支持会话记忆的流式 AI 对话
     */
    @IgnoreFeignAuthorize
    @PostMapping(value = "/stream", produces = "text/event-stream")
    @Operation(summary = "流式对话", description = "支持会话记忆的流式 AI 对话")
    public SseEmitter stream(@Validated @RequestBody AskReq req) {
        return chatService.chatStream(req);
    }
}
