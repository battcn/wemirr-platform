package com.wemirr.platform.ai.domain.dto.rep;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author xJh
 * @date 2025/11/4
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
    private String modelName;

    @Schema(description = "智能体描述")
    private String description;

    @Schema(description = "智能体角色预设")
    private String aiSystemMessage;

    @Schema(description = "智能体头像")
    private String avatar;

    @Schema(description = "关联知识库ID")
    private Long knowledgeBaseId;

    @Schema(description = "智能体工具配置（JSON格式）")
    private String tools;

    @Schema(description = "创建人ID")
    private Long createdBy;

    @Schema(description = "创建人名称")
    private String createdName;

    @Schema(description = "创建时间")
    private LocalDateTime createdTime;

    @Schema(description = "最后修改人ID")
    private Long lastModifiedBy;

    @Schema(description = "最后修改人名称")
    private String lastModifiedName;

    @Schema(description = "最后修改时间")
    private LocalDateTime lastModifiedTime;

}
