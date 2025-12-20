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
 * @author xiao1
 * @since 2025-10
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("ai_conversation_message")
@Schema(description = "会话信息明细记录")
public class ConversationMessage extends SuperEntity<Long> {

    @Schema(description = "会话ID，关联同一轮对话")
    @TableField("conversation_id")
    private Long conversationId;

    @Schema(description = "父消息ID，用于构建消息树（如用户提问 → AI回复）")
    @TableField("parent_message_id")
    private Long parentMessageId;

    @Schema(description = "用户ID")
    @TableField("user_id")
    private Long userId;

    @Schema(description = "租户ID")
    @TableField("tenant_id")
    private Long tenantId;

    @Schema(description = "消息角色：user / assistant / system")
    @TableField("role")
    private String role;

    @Schema(description = "原始消息内容（用户输入或AI原始输出）")
    @TableField("raw_content")
    private String rawContent;

    @Schema(description = "处理后的输入（如拼接知识库、指令注入等），供LLM使用")
    @TableField("prompt_content")
    private String promptContent;

    @Schema(description = "最终展示给用户的内容（经合规、脱敏、格式化处理）")
    @TableField("display_content")
    private String displayContent;

    @Schema(description = "模型提供商，如 openai, deepseek, qwen")
    @TableField("model_provider")
    private String modelProvider;

    @Schema(description = "模型名称，如 gpt-4, deepseek-chat-r1")
    @TableField("model_name")
    private String modelName;

    @Schema(description = "输入Token数")
    @TableField("prompt_tokens")
    private Integer promptTokens;

    @Schema(description = "输出Token数")
    @TableField("completion_tokens")
    private Integer completionTokens;

    @Schema(description = "总消耗Token数")
    @TableField("total_tokens")
    private Integer totalTokens;

    @Schema(description = "响应延迟（毫秒）")
    @TableField("response_latency_ms")
    private Long responseLatencyMs;

    @Schema(description = "思考内容（如Agent的Plan、CoT、Tool Call等）")
    @TableField("thinking_content")
    private String thinkingContent;

    @Schema(description = "用户反馈：1-点赞 2-点踩 0-无反馈")
    @TableField("user_feedback")
    private Integer userFeedback;

    @Schema(description = "反馈备注（用户填写的反馈原因）")
    @TableField("feedback_remark")
    private String feedbackRemark;

    @Schema(description = "来源设备/IP/渠道，JSON格式，如 {\"ip\":\"1.1.1.1\",\"device\":\"mobile\"}")
    @TableField("metadata")
    private String metadata;

    @Schema(description = "消息在会话中的顺序号，用于排序")
    @TableField("sequence_num")
    private Integer sequenceNum;
    

}
