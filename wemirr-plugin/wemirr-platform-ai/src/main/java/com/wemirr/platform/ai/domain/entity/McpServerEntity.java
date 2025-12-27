package com.wemirr.platform.ai.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.framework.db.mybatisplus.handler.type.StringListTypeHandler;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

/**
 * MCP服务器配置
 *
 * @author xJh
 * @since 2025/12/07
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ai_mcp_server")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "MCP服务器配置")
public class McpServerEntity extends SuperEntity<Long> {

    @Schema(description = "服务器名称")
    private String name;

    @Schema(description = "连接类型 (STDIO/SSE)")
    private String type;

    @Schema(description = "STDIO命令")
    private String command;

    @Schema(description = "STDIO参数列表")
    @TableField(typeHandler = StringListTypeHandler.class)
    private List<String> args;

    @Schema(description = "SSE连接URL")
    private String url;

    @Schema(description = "环境变量配置")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Map<String, String> env;

    @Schema(description = "是否启用")
    private Boolean status;

    @Schema(description = "租户ID")
    private String tenantId;
}

