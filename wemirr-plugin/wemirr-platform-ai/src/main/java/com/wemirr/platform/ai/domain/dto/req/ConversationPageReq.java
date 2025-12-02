package com.wemirr.platform.ai.domain.dto.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author xJh
 * @since 2025/10/30
 **/
@Data
@Schema(description = "会话分页查询")
public class ConversationPageReq extends PageRequest {

    @Schema(description = "会话名称")
    private String title;

    @Schema(description = "模型名称")
    private String modelName;

    @Schema(description = "知识库ID")
    private Long knowledgeBaseId;

    //对话类型
    @Schema(description = "对话类型")
    private Integer type;
}
