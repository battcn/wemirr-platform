package com.wemirr.platform.ai.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * 向量匹配结果响应
 *
 * @author xiao1
 * @since 2025-12
 */
@Data
@Builder
@AllArgsConstructor
@Schema(description = "向量匹配结果")
public class EmbeddingMatchResp {

    @Schema(description = "匹配内容")
    private String content;

    @Schema(description = "相似度分数")
    private Double score;

    @Schema(description = "元数据")
    private Map<String, Object> metadata;

    @Schema(description = "搜索类型")
    private String searchType;
}
