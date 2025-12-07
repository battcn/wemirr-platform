package com.wemirr.platform.ai.domain.dto.rep;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author xJh
 * @since 2025/11/4
 **/
@Data
@Schema(description = "智能体详情响应")
public class ChatAgentDetailRep {

    @Schema(description = "智能体ID")
    private Long id;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "智能体名称")
    private String name;

    @Schema(description = "绑定模型名称")
    private Long chatModelId;

    @Schema(description = "智能体描述")
    private String description;

    @Schema(description = "智能体角色预设")
    private String aiSystemMessage;

    @Schema(description = "智能体头像")
    private String avatar;

    @Schema(description = "关联知识库ID")
    private Long kbId;

    // JSON格式存储工具名称列表，如：["平台菜单查询工具"]
    @Schema(description = "智能体工具配置")
    private String tools;
    
    // JSON格式存储MCP服务器ID列表，如：[1, 2, 3]
    @Schema(description = "智能体MCP服务器配置（存储MCP配置ID列表）")
    private String mcpServerIds;

}
