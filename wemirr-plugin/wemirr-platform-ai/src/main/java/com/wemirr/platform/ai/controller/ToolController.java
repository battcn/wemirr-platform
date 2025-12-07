package com.wemirr.platform.ai.controller;

import com.wemirr.platform.ai.service.ToolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 智能体工具控制器
 *
 * @author xJh
 * @since 2025/12/06
 */
@Slf4j
@RestController
@RequestMapping("/tools")
@RequiredArgsConstructor
@Tag(name = "智能体工具管理", description = "智能体工具管理")
public class ToolController {

    private final ToolService toolService;

    @GetMapping
    @Operation(summary = "获取所有可用工具", description = "获取系统中所有标注了 @Tool 的 Bean 及其方法信息")
    public List<ToolService.ToolDTO> listTools() {
        return toolService.getTools();
    }
    
    @GetMapping("/refresh")
    @Operation(summary = "刷新工具缓存", description = "手动触发重新扫描工具")
    public void refreshTools() {
        toolService.scanTools();
    }
}


