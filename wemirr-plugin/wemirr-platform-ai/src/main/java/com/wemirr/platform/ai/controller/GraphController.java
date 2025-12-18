package com.wemirr.platform.ai.controller;

import com.wemirr.platform.ai.domain.dto.rep.EntityRecallResp;
import com.wemirr.platform.ai.domain.dto.rep.GraphVisualizationResp;
import com.wemirr.platform.ai.service.GraphService;
import com.wemirr.platform.ai.service.GraphService.GraphResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 图谱化控制器
 * 提供对知识库内容进行图谱化的接口
 *
 * @author xJh
 * @since 2025/12/18
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "图谱化管理")
@RequestMapping("/graph")
public class GraphController {

    private final GraphService graphService;

    @PostMapping("/knowledge-item/{itemId}")
    @Operation(summary = "对知识条目进行图谱化")
    public GraphResult graphizeKnowledgeItem(@PathVariable Long itemId) {
        return graphService.graphizeKnowledgeItem(itemId);
    }

    @PostMapping("/knowledge-items")
    @Operation(summary = "批量对知识条目进行图谱化")
    public List<GraphResult> graphizeKnowledgeItems(@RequestBody List<Long> itemIds) {
        return graphService.graphizeKnowledgeItems(itemIds);
    }

    @DeleteMapping("/knowledge-item/{itemId}")
    @Operation(summary = "删除知识条目的图谱数据")
    public boolean deleteGraphForItem(@PathVariable Long itemId) {
        return graphService.deleteGraphForItem(itemId);
    }

    @DeleteMapping("/knowledge-base/{kbId}")
    @Operation(summary = "删除知识库的所有图谱数据")
    public boolean deleteGraphForKnowledgeBase(@PathVariable Long kbId) {
        return graphService.deleteGraphForKnowledgeBase(kbId);
    }

    @GetMapping("/knowledge-base/{kbId}/statistics")
    @Operation(summary = "获取知识库的图谱统计信息")
    public Map<String, Object> getGraphStatistics(@PathVariable Long kbId) {
        return graphService.getGraphStatistics(kbId);
    }

    @GetMapping("/knowledge-base/{kbId}/schema")
    @Operation(summary = "获取知识库的图谱 Schema")
    public String getGraphSchema(@PathVariable Long kbId) {
        return graphService.getGraphSchema(kbId);
    }

    @GetMapping("/health")
    @Operation(summary = "检查图谱服务健康状态")
    public boolean checkHealth() {
        return graphService.isGraphServiceAvailable();
    }

    @GetMapping("/knowledge-base/{kbId}/visualization")
    @Operation(summary = "获取图谱可视化数据", description = "返回节点和关系数据，用于前端图谱可视化展示")
    public GraphVisualizationResp getVisualizationData(
            @PathVariable Long kbId,
            @RequestParam(defaultValue = "100") int limit) {
        return graphService.getVisualizationData(kbId, limit);
    }

    @PostMapping("/knowledge-base/{kbId}/recall-test")
    @Operation(summary = "实体召回测试", description = "根据关键词测试实体召回效果")
    public EntityRecallResp testEntityRecall(
            @PathVariable Long kbId,
            @RequestBody List<String> keywords) {
        return graphService.testEntityRecall(kbId, keywords);
    }
}
