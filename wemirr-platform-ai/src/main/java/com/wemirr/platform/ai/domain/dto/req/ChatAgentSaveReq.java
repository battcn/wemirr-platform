package com.wemirr.platform.ai.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author xJh
 * @date 2025/11/4
 **/
@Data
@Schema(description = "智能体保存请求")
public class ChatAgentSaveReq {

    @NotBlank(message = "智能体名称不能为空")
    @Length(max = 100, message = "智能体名称长度不能超过{max}")
    @Schema(description = "智能体名称")
    private String name;

    @NotBlank(message = "模型名称不能为空")
    @Length(max = 100, message = "模型名称长度不能超过{max}")
    @Schema(description = "绑定模型名称")
    private String modelName;

    @Length(max = 500, message = "智能体描述长度不能超过{max}")
    @Schema(description = "智能体描述")
    private String description;

    @Length(max = 2000, message = "智能体角色预设长度不能超过{max}")
    @Schema(description = "智能体角色预设")
    private String aiSystemMessage;

    @Schema(description = "智能体头像url")
    private String avatar;

    @Schema(description = "关联知识库ID")
    private Long knowledgeBaseId;

    @Length(max = 2000, message = "智能体工具配置长度不能超过{max}")
    @Schema(description = "智能体工具配置（JSON格式）")
    private String tools;
}
