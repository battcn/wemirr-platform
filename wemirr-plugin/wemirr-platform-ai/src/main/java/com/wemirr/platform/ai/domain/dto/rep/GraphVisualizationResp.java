package com.wemirr.platform.ai.domain.dto.rep;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * 图谱可视化数据响应
 * 用于前端图谱可视化展示
 *
 * @author xJh
 * @since 2025/12/18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "图谱可视化数据响应")
public class GraphVisualizationResp {

    @Schema(description = "知识库ID")
    private String knowledgeBaseId;

    @Schema(description = "节点列表")
    private List<GraphNode> nodes;

    @Schema(description = "关系列表")
    private List<GraphEdge> edges;

    @Schema(description = "节点总数")
    private Integer nodeCount;

    @Schema(description = "关系总数")
    private Integer edgeCount;

    /**
     * 图谱节点
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "图谱节点")
    public static class GraphNode {

        @Schema(description = "节点ID")
        private String id;

        @Schema(description = "节点标签（类型）")
        private String label;

        @Schema(description = "节点名称（显示文本）")
        private String name;

        @Schema(description = "节点类型列表")
        private List<String> labels;

        @Schema(description = "节点属性")
        private Map<String, Object> properties;
    }

    /**
     * 图谱边（关系）
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "图谱关系")
    public static class GraphEdge {

        @Schema(description = "关系ID")
        private String id;

        @Schema(description = "源节点ID")
        private String source;

        @Schema(description = "目标节点ID")
        private String target;

        @Schema(description = "关系类型")
        private String type;

        @Schema(description = "关系标签（显示文本）")
        private String label;

        @Schema(description = "关系属性")
        private Map<String, Object> properties;
    }
}
