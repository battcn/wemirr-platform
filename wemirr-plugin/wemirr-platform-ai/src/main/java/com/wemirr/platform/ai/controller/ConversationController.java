package com.wemirr.platform.ai.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.platform.ai.domain.dto.req.ConversationPageReq;
import com.wemirr.platform.ai.domain.dto.req.ConversationSaveReq;
import com.wemirr.platform.ai.domain.dto.resp.ConversationDetailResp;
import com.wemirr.platform.ai.domain.dto.resp.ConversationMessageResp;
import com.wemirr.platform.ai.domain.dto.resp.ConversationPageResp;
import com.wemirr.platform.ai.domain.entity.Conversation;
import com.wemirr.platform.ai.service.ConversationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xJh
 * @since 2025/10/30
 **/
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/conversations")
@Tag(name = "会话管理", description = "会话管理接口")
public class ConversationController {

    private final ConversationService conversationService;


    @GetMapping("/page")
    @Operation(summary = "分页查询会话")
    public IPage<ConversationPageResp> pageList(ConversationPageReq req) {
        return conversationService.pageList(req);
    }

    @Operation(summary = "获取会话详情")
    @GetMapping("/{id}/detail")
    public ConversationDetailResp detail(@PathVariable Long id) {
        return conversationService.detail(id);
    }


    @GetMapping("/{id}/messages")
    @Operation(summary = "获取普通会话消息列表")
    public List<ConversationMessageResp> turnList(@PathVariable Long id) {
        return conversationService.turnList(id);
    }

    @GetMapping("/messages")
    @Operation(summary = "获取知识库会话消息列表")
    public List<ConversationMessageResp> messageList(Long kbId, Long agentId) {
        return conversationService.messageList(kbId, agentId);
    }

    @PostMapping
    @Operation(summary = "新增会话")
    public Conversation create(@Validated @RequestBody ConversationSaveReq req) {
        return conversationService.create(req);
    }

    @Operation(summary = "修改会话")
    @PutMapping("/{id}")
    public void modify(@PathVariable Long id, @Validated @RequestBody ConversationSaveReq req) {
        conversationService.modify(id, req);
    }

    @Operation(summary = "删除会话")
    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        conversationService.remove(id);
    }

    @Operation(summary = "清空会话消息")
    @DeleteMapping("/{id}/messages")
    public void clearMessages(@PathVariable Long id) {
        conversationService.clearMessages(id);
    }

    @Operation(summary = "置顶会话")
    @PutMapping("/{id}/pin")
    public void pin(@PathVariable Long id) {
        conversationService.pin(id);
    }

    @Operation(summary = "取消置顶会话")
    @PutMapping("/{id}/unpin")
    public void unpin(@PathVariable Long id) {
        conversationService.unpin(id);
    }

}
