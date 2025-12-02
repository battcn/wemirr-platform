package com.wemirr.platform.ai.controller;

import com.wemirr.framework.security.configuration.client.annotation.IgnoreAuthorize;
import com.wemirr.platform.ai.domain.dto.req.AskReq;
import com.wemirr.platform.ai.service.ChatService;
import com.wemirr.platform.ai.utils.SseChatHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @author xJh
 * @since 2025/10/11
 **/
@RestController
@RequestMapping("/cov")
@RequiredArgsConstructor
@Tag(name = "普通聊天测试",description = "普通聊天测试接口")
public class ChatController {

    private final SseChatHelper sseChatHelper;

    private final ChatService chatService;

    /**
     * 流式对话记忆
     */
    @IgnoreAuthorize
    @PostMapping(value = "/chat/stream", produces = "text/event-stream")
    @Operation(summary = "流式对话记忆")
    public SseEmitter chatTestStream(@RequestBody AskReq askReq) {
        return chatService.chatStream(askReq);

    }
}
