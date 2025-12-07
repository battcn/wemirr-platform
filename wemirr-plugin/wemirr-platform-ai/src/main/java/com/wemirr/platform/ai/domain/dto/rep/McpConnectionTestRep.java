package com.wemirr.platform.ai.domain.dto.rep;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MCP连接测试结果
 *
 * @author xJh
 * @since 2025/12/07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "MCP连接测试结果")
public class McpConnectionTestRep {

    @Schema(description = "是否连接成功")
    private Boolean success;

    @Schema(description = "错误信息")
    private String errorMessage;

    @Schema(description = "服务器名称")
    private String serverName;


    @Schema(description = "可用工具数量")
    private Integer toolCount;

    @Schema(description = "响应时间(毫秒)")
    private Long responseTime;
}

