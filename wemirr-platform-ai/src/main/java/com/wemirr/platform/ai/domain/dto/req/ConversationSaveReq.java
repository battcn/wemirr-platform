package com.wemirr.platform.ai.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author xJh
 * @since 2025/10/30
 **/
@Data
@Schema(description = "会话保存")
public class ConversationSaveReq {

    @NotBlank(message = "会话名称不能为空")
    @Schema(description = "会话名称")
    private String title;

    //绑定的智能体ID
    @Schema(description = "绑定的智能体ID")
    private Long agentId;

    @NotNull(message = "会话类型不能为空")
    @Schema(description = "会话类型")
    private Integer type;
}
