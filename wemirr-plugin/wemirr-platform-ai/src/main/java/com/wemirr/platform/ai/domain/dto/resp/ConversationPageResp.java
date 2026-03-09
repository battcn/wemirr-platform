package com.wemirr.platform.ai.domain.dto.resp;

import com.wemirr.platform.ai.core.enums.ConversationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

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

    @Schema(description = "关联的知识库ID列表")
    private List<Long> knowledgeBaseIds;

    @Schema(description = "智能体ID")
    private Long agentId;

    @Schema(description = "对话类型")
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
