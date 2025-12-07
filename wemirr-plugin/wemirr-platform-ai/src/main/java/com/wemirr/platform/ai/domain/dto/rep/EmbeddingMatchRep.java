package com.wemirr.platform.ai.domain.dto.rep;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * @author xiao1
 * @since  2025-12
 */
@Data
@Builder
@AllArgsConstructor
public class EmbeddingMatchRep {

    private String content;

    private Double score;

    private Map<String,Object> metadata;

    private String searchType;

}
