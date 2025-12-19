package com.wemirr.platform.ai.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * MCP服务配置
 *
 * @author xJh
 * @since 2025/12/07
 * @description:
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ai_mcp_server_config")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "MCP服务配置")
public class McpServerConfig extends SuperEntity<Long> {

    @Schema(description = "服务名称")
    private String name;

    @Schema(description = "连接类型 (STDIO/SSE)")
    private String type;

    @Schema(description = "STDIO命令")
    private String command;

    @Schema(description = "STDIO参数列表(JSON)")
    private String args;

    @Schema(description = "SSE URL")
    private String url;

    @Schema(description = "环境变量(JSON)")
    private String env;

    @Schema(description = "状态 (true:启用 false:禁用)")
    private Boolean status;

    @Schema(description = "租户ID")
    private String tenantId;
}

