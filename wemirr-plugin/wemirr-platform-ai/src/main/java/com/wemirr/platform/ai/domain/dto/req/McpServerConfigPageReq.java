package com.wemirr.platform.ai.domain.dto.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "MCP服务配置分页请求")
public class McpServerConfigPageReq extends PageRequest {

    @Schema(description = "服务名称")
    private String name;

    @Schema(description = "状态")
    private Boolean status;
}

