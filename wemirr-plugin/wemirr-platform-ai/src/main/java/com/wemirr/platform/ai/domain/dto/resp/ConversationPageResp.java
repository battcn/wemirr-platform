package com.wemirr.platform.ai.domain.dto.resp;

import com.wemirr.platform.ai.core.enums.ConversationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author xJh
 * @since 2025/10/30
 **/
@Data
@Schema(description = "会话分页响应")
public class ConversationPageResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "会话名称")
    private String title;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "关联的知识库ids")
    private Long knowledgeBaseIds;

    @Schema(description = "智能体ID")
    private Long agentId;

    @Schema(description = "对话类型：1-普通对话 2-通用智能体对话 3-平台智能体 4-知识库对话 5-图片生成")
    private ConversationType type;

    @Schema(description = "最后一条消息内容")
    private String lastMessage;

    @Schema(description = "消息数量")
    private Integer messageCount;

    @Schema(description = "是否置顶")
    private Boolean pinned;

    @Schema(description = "租户ID")
    private String tenantId;

}
