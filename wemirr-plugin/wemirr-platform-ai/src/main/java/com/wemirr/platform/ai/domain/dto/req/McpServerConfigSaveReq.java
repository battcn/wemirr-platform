package com.wemirr.platform.ai.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "MCP服务配置保存请求")
public class McpServerConfigSaveReq {

    @NotBlank(message = "服务名称不能为空")
    @Schema(description = "服务名称")
    private String name;

    @NotBlank(message = "连接类型不能为空")
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

    @Schema(description = "状态")
    private Boolean status;
}

