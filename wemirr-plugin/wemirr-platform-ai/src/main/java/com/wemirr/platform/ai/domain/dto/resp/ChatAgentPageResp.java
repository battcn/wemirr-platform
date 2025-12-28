package com.wemirr.platform.ai.domain.dto.resp;

import com.baomidou.mybatisplus.annotation.TableField;
import com.wemirr.framework.db.mybatisplus.handler.type.LongListTypeHandler;
import com.wemirr.framework.db.mybatisplus.handler.type.StringListTypeHandler;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author xJh
 * @since 2025/11/4
 **/
@Data
@Schema(description = "智能体分页响应")
public class ChatAgentPageResp {

    @Schema(description = "智能体ID")
    private Long id;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "智能体名称")
    private String name;

    @Schema(description = "智能体描述")
    private String description;

    @Schema(description = "智能体头像")
    private String avatar;

    @Schema(description = "系统提示词")
    private String systemPrompt;

    @Schema(description = "绑定模型ID")
    private Long modelId;

    @Schema(description = "关联知识库ID")
    private Long kbId;

    @Schema(description = "智能体工具配置")
    @TableField(typeHandler = StringListTypeHandler.class)
    private List<String> tools;

    @Schema(description = "MCP服务器ID列表")
    @TableField(typeHandler = LongListTypeHandler.class)
    private List<Long> mcpServerIds;

    @Schema(description = "租户ID")
    private String tenantId;
}
