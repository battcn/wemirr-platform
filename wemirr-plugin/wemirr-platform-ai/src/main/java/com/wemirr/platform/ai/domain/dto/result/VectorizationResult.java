package com.wemirr.platform.ai.domain.dto.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author xJh
 * @since  2025/12/8
 **/
@Data
@Builder
@AllArgsConstructor
public class VectorizationResult {

    private String taskId;

    private Integer totalTokenUsage;
}
