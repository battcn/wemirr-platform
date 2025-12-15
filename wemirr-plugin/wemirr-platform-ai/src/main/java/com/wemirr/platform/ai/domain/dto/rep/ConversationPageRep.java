package com.wemirr.platform.ai.domain.dto.rep;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * @author xJh
 * @since 2025/10/30
 **/
@Data
@Schema(description = "会话分页响应")
public class ConversationPageRep {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "会话名称")
    private String title;

    @Schema(description = "模型名称")
    private String modelName;

    @Schema(description = "知识库ID")
    private Long knowledgeBaseId;

    @Schema(description = "最后一条消息内容")
    private String lastMessage;

    @Schema(description = "消息数量")
    private Integer messageCount;

    @Schema(description = "是否置顶")
    private Boolean pinned;

    @Schema(description = "创建时间")
    private Instant createTime;

    @Schema(description = "更新时间")
    private Instant updatedTime;

}
