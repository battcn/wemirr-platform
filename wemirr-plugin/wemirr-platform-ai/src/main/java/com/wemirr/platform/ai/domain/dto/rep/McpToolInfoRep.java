package com.wemirr.platform.ai.domain.dto.rep;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MCP工具信息
 *
 * @author xJh
 * @since 2025/12/07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "MCP工具信息")
public class McpToolInfoRep {

    @Schema(description = "工具名称")
    private String name;

    @Schema(description = "工具描述")
    private String description;

}

