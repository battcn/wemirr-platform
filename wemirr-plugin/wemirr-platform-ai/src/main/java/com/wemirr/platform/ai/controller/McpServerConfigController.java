package com.wemirr.platform.ai.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.platform.ai.domain.dto.rep.McpConnectionTestRep;
import com.wemirr.platform.ai.domain.dto.rep.McpToolInfoRep;
import com.wemirr.platform.ai.domain.dto.req.McpServerConfigPageReq;
import com.wemirr.platform.ai.domain.dto.req.McpServerConfigSaveReq;
import com.wemirr.platform.ai.domain.entity.McpServerConfig;
import com.wemirr.platform.ai.service.McpServerConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * MCP服务配置控制器
 *
 * @author xJh
 * @since 2025/12/07
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/mcp-server")
@RequiredArgsConstructor
@Tag(name = "MCP服务配置", description = "MCP服务配置管理")
public class McpServerConfigController {

    private final McpServerConfigService mcpServerConfigService;

    @GetMapping
    @Operation(summary = "分页查询")
    public IPage<McpServerConfig> pageList(McpServerConfigPageReq req) {
        return mcpServerConfigService.pageList(req);
    }

    @Operation(summary = "新增配置")
    @PostMapping
    public void create(@Validated @RequestBody McpServerConfigSaveReq req) {
        mcpServerConfigService.create(req);
    }

    @Operation(summary = "修改配置")
    @PutMapping("/{id}")
    public void modify(@PathVariable Long id, @Validated @RequestBody McpServerConfigSaveReq req) {
        mcpServerConfigService.modify(id, req);
    }

    @Operation(summary = "删除配置")
    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        mcpServerConfigService.remove(id);
    }
    
    @Operation(summary = "刷新连接")
    @PatchMapping("/{id}/refresh")
    public void refresh(@PathVariable Long id) {
        mcpServerConfigService.refresh(id);
    }

    @Operation(summary = "测试MCP连接", description = "测试指定MCP服务器的连通性，返回连接状态和可用工具数量")
    @PostMapping("/{id}/test-connection")
    public McpConnectionTestRep testConnection(@PathVariable Long id) {
        return mcpServerConfigService.testConnection(id);
    }

    @Operation(summary = "获取MCP工具列表", description = "获取指定MCP服务器提供的所有工具信息")
    @GetMapping("/{id}/tools")
    public List<McpToolInfoRep> getTools(@PathVariable Long id) {
        return mcpServerConfigService.getTools(id);
    }

    @Operation(summary = "切换启用状态", description = "快速启用或禁用MCP服务器")
    @PatchMapping("/{id}/status")
    public void toggleStatus(@PathVariable Long id, @RequestParam Boolean status) {
        mcpServerConfigService.toggleStatus(id, status);
    }
}

