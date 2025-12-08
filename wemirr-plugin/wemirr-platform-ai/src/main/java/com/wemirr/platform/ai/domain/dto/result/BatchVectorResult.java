package com.wemirr.platform.ai.domain.dto.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author xJh
 * @since  2025/12/8
 **/
@Data
@Builder
@AllArgsConstructor
public class BatchVectorResult {

    private List<String> vectorIds;

    private Integer tokenUsage;

}
