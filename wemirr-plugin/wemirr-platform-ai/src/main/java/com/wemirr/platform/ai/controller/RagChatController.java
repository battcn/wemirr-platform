package com.wemirr.platform.ai.controller;

import com.wemirr.framework.security.configuration.client.annotation.IgnoreFeignAuthorize;
import com.wemirr.platform.ai.core.enums.ConversationType;
import com.wemirr.platform.ai.domain.dto.req.AskReq;
import com.wemirr.platform.ai.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * RAG对话控制器
 * 提供基于知识库的检索增强生成对话功能
 *
 * @author xJh
 * @since 2025/10/21
 **/
@Slf4j
@RestController
@RequestMapping("/rag")
@RequiredArgsConstructor
@Tag(name = "RAG对话", description = "基于知识库的检索增强生成对话")
public class RagChatController {

    private final ChatService chatService;

    /**
     * RAG流式对话
     * 基于知识库进行检索增强生成
     */
    @IgnoreFeignAuthorize
    @PostMapping(value = "/chat/stream", produces = "text/event-stream")
    @Operation(summary = "RAG流式对话", description = "基于知识库的检索增强生成对话")
    public SseEmitter ragChatStream(@RequestBody AskReq askReq) {
        askReq.setChatType(ConversationType.KNOWLEDGE_BASE);
        return chatService.chatStream(askReq);
    }
}
