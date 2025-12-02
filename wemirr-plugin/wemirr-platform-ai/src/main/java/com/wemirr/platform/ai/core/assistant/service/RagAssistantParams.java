package com.wemirr.platform.ai.core.assistant.service;

import com.wemirr.platform.ai.core.enums.ChunkType;
import com.wemirr.platform.ai.domain.entity.ModelConfig;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RagAssistantParams {

    private Long kbId;

    private ModelConfig textModelConfig;

    private ModelConfig embeddingModelConfig;

    @Builder.Default
    private Integer maxMessages = 10;

    @Builder.Default
    private Integer maxResults = 2;

    @Builder.Default
    private Double minScore = 0.8;

    @Builder.Default
    private ChunkType filterChunkType = ChunkType.ANSWER;
}


