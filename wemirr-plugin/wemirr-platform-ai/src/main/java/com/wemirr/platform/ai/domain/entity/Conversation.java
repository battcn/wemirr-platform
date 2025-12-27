package com.wemirr.platform.ai.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.framework.db.mybatisplus.handler.type.LongListTypeHandler;
import com.wemirr.platform.ai.core.enums.ConversationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 会话信息实体
 *
 * @author xJh
 * @since 2025/10/11
 */
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

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "关联的知识库ID列表")
    @TableField(typeHandler = LongListTypeHandler.class)
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