package com.wemirr.platform.ai.domain.dto.resp;

import com.wemirr.platform.ai.core.enums.MessageRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * 会话消息响应
 *
 * @author Cascade
 * @since 2025/12/20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "会话消息响应")
public class ConversationMessageResp {

    @Schema(description = "消息ID")
    private Long id;

    @Schema(description = "消息角色：user / assistant")
    private MessageRole role;

    @Schema(description = "用户原始输入内容（未做任何处理）")
    private String userInput;

    @Schema(description = "最终发送给模型的 Prompt（包含系统指令、上下文、模板展开结果）")
    private String finalPrompt;

    @Schema(description = "模型返回的原始输出内容（未裁剪、未渲染）")
    private String modelOutput;

    @Schema(description = "最终对用户展示的内容（安全过滤、格式化后）")
    private String displayContent;

    @Schema(description = "思考链内容")
    private String thinkingContent;

    @Schema(description = "创建时间")
    private Instant createTime;
}
