package com.wemirr.platform.ai.domain.dto.rep;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 实体召回测试响应
 *
 * @author xJh
 * @since 2025/12/18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "实体召回测试响应")
public class EntityRecallResp {

    @Schema(description = "知识库ID")
    private String knowledgeBaseId;

    @Schema(description = "查询关键词")
    private List<String> keywords;

    @Schema(description = "召回的实体列表")
    private List<RecalledEntity> entities;

    @Schema(description = "相关三元组")
    private List<String> triples;

    @Schema(description = "召回实体数量")
    private Integer entityCount;

    @Schema(description = "三元组数量")
    private Integer tripleCount;

    /**
     * 召回的实体
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "召回的实体")
    public static class RecalledEntity {

        @Schema(description = "实体ID")
        private String id;

        @Schema(description = "实体名称")
        private String name;

        @Schema(description = "实体类型")
        private String type;

        @Schema(description = "匹配分数")
        private Double score;
    }
}
