package com.wemirr.platform.ai.domain.dto.rep;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * @author xJh
 * @since 2025/10/30
 **/
@Data
@Schema(description = "会话详情响应")
public class ConversationDetailRep {
    @Schema(description = "ID")
    private String id;

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

    @Schema(description = "创建人ID")
    private Long createBy;

    @Schema(description = "创建人名称")
    private String createName;

    @Schema(description = "创建时间")
    private Instant createTime;

    @Schema(description = "最后修改人ID")
    private Long lastModifiedBy;

    @Schema(description = "最后修改人名称")
    private String lastModifiedName;

    @Schema(description = "最后修改时间")
    private Instant lastModifiedTime;
}
