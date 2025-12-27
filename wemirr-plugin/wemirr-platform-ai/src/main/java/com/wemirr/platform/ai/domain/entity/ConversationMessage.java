package com.wemirr.platform.ai.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 会话消息记录
 *
 * @author xiao1
 * @since 2025-10
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("ai_conversation_message")
@Schema(description = "会话消息记录")
public class ConversationMessage extends SuperEntity<Long> {

    @Schema(description = "会话ID")
    private Long conversationId;

    @Schema(description = "父消息ID，用于构建消息树")
    private Long parentMessageId;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "消息角色：user / assistant / system")
    private String role;

    @Schema(description = "原始消息内容")
    private String originalContent;

    @Schema(description = "处理后的提示词内容")
    private String processedContent;

    @Schema(description = "最终展示内容")
    @TableField("rendered_content")
    private String renderedContent;

    @Schema(description = "模型提供商")
    @TableField("model_provider")
    private String modelProvider;

    @Schema(description = "模型名称")
    @TableField("model_name")
    private String modelName;

    @Schema(description = "输入 Token 数")
    @TableField("prompt_tokens")
    private Integer promptTokens;

    @Schema(description = "输出 Token 数")
    private Integer completionTokens;

    @Schema(description = "总消耗 Token 数")
    private Integer totalTokens;

    @Schema(description = "响应延迟（毫秒）")
    private Long responseLatencyMs;

    @Schema(description = "思考内容")
    private String thinkingContent;

    @Schema(description = "用户反馈：1-点赞 2-点踩 0-无反馈")
    private Integer userFeedback;

    @Schema(description = "反馈备注")
    private String feedbackRemark;

    @Schema(description = "元数据（JSON格式）")
    private String metadata;

    @Schema(description = "消息序号")
    private Integer sequenceNum;
}