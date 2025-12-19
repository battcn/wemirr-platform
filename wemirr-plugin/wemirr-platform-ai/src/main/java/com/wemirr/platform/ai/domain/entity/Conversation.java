package com.wemirr.platform.ai.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.platform.ai.core.enums.ConversationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author xJh
 * @since 2025/10/11
 **/
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ai_conversation")
@Schema(description = "会话信息")
@EqualsAndHashCode(callSuper = true)
public class Conversation extends SuperEntity<Long> {

    @Schema(description = "会话名称")
    private String title;

    @Schema(title = "对话uuid")
    @TableField("uuid")
    private String uuid;

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
