package com.wemirr.platform.ai.domain.dto.rep;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author xJh
 * @since 2025/11/4
 **/
@Data
@Schema(description = "智能体分页响应")
public class ChatAgentPageRep {

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

    @Schema(description = "智能体头像")
    private String avatar;

    @Schema(description = "关联知识库ID")
    private Long knowledgeBaseId;

    @Schema(description = "创建时间")
    private LocalDateTime createdTime;

    @Schema(description = "更新时间")
    private LocalDateTime updatedTime;
}
