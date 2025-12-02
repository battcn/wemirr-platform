package com.wemirr.platform.ai.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.platform.ai.domain.dto.rep.ChatAgentDetailRep;
import com.wemirr.platform.ai.domain.dto.rep.ChatAgentPageRep;
import com.wemirr.platform.ai.domain.dto.req.ChatAgentPageReq;
import com.wemirr.platform.ai.domain.dto.req.ChatAgentSaveReq;
import com.wemirr.platform.ai.domain.entity.ChatAgent;
import com.wemirr.platform.ai.service.ChatAgentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author xJh
 * @date 2025/11/4
 **/
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/chat-agent")
@Tag(name = "智能体管理", description = "智能体CRUD管理")
public class ChatAgentController {

    private final ChatAgentService chatAgentService;
    private final AuthenticationContext authenticationContext;

    @Operation(summary = "分页查询智能体")
    @GetMapping("/page")
    public IPage<ChatAgentPageRep> pageList(ChatAgentPageReq req) {
        return chatAgentService.pageList(req);
    }

    @Operation(summary = "获取智能体详情")
    @GetMapping("/{id}/detail")
    public ChatAgentDetailRep detail(@Parameter(description = "智能体ID") @PathVariable Long id) {
        return chatAgentService.detail(id);
    }

    @Operation(summary = "通过智能体id获取会话")
    @GetMapping("/{agentId}/detailByAgentId")
    public ChatAgentDetailRep detailByAgentId(@Parameter(description = "智能体ID") @PathVariable Long agentId) {
        return chatAgentService.detailByAgentId(agentId);
    }


    @Operation(summary = "新增智能体")
    @PostMapping
    public void create(@Validated @RequestBody ChatAgentSaveReq req) {
        chatAgentService.create(req);
    }

    @Operation(summary = "修改智能体")
    @PutMapping("/{id}")
    public void modify(@Parameter(description = "智能体ID") @PathVariable Long id,
                       @Validated @RequestBody ChatAgentSaveReq req) {
        chatAgentService.modify(id, req);
    }

    @Operation(summary = "删除智能体")
    @DeleteMapping("/{id}")
    public void remove(@Parameter(description = "智能体ID") @PathVariable Long id) {
        chatAgentService.remove(id);
    }

    @Operation(summary = "上传头像,返回头像URL")
    @PostMapping("/avatar")
    public String uploadAvatar(@RequestParam("file") MultipartFile file) {
        return chatAgentService.uploadAvatar(file);
    }

    @Operation(summary = "获取当前用户的智能体列表")
    @GetMapping("/my")
    public List<ChatAgent> myAgents() {
        Long userId = authenticationContext.userId();
        return chatAgentService.listByUserId(userId);
    }

    @Operation(summary = "根据模型id获取智能体列表")
    @GetMapping("/by-model/{modelId}")
    public List<ChatAgent> listByModel(@Parameter(description = "模型名称") @PathVariable String modelId) {
        return chatAgentService.listByModelId(modelId);
    }

    @Operation(summary = "检查智能体名称是否存在")
    @GetMapping("/check-name")
    public boolean checkName(@Parameter(description = "智能体名称") @RequestParam String name,
                             @Parameter(description = "排除的智能体ID") @RequestParam(required = false) Long excludeId) {
        Long userId = authenticationContext.userId();
        return chatAgentService.existsByName(name, userId, excludeId);
    }
}