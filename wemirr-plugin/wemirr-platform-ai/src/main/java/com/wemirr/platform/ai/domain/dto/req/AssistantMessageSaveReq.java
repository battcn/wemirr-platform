package com.wemirr.platform.ai.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AI助手消息保存请求
 *
 * @author xJh
 * @since 2025/12/28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "AI助手消息保存请求")
public class AssistantMessageSaveReq {

    @NotNull(message = "会话ID不能为空")
    @Schema(description = "会话ID")
    private Long conversationId;

    @NotNull(message = "用户ID不能为空")
    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "原始内容")
    private String rawContent;

    @Schema(description = "显示内容")
    private String displayContent;

    @Schema(description = "提示词内容")
    private String promptContent;

    @Schema(description = "模型名称")
    private String modelName;

    @Schema(description = "模型提供商")
    private String modelProvider;

    @Schema(description = "提示词Token数")
    private Integer promptTokens;

    @Schema(description = "完成Token数")
    private Integer completionTokens;

    @Schema(description = "响应延迟(毫秒)")
    private Long responseLatencyMs;

    @Schema(description = "思考内容")
    private String thinkingContent;

    @Schema(description = "父消息ID")
    private Long parentMessageId;
}
