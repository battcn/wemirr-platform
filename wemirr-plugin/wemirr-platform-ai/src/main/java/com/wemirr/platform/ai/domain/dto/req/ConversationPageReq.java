package com.wemirr.platform.ai.domain.dto.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xJh
 * @since 2025/10/30
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "ConversationPageReq", description = "会话分页查询")
public class ConversationPageReq extends PageRequest {

    @Schema(description = "会话名称")
    private String title;

    @Schema(description = "模型名称")
    private String modelName;

    @Schema(description = "知识库ID")
    private Long kbId;

    @Schema(description = "对话类型")
    private Integer type;

    @Schema(description = "智能体ID")
    private Long agentId;
}
