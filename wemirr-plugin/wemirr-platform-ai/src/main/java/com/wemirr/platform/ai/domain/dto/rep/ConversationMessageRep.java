package com.wemirr.platform.ai.domain.dto.rep;

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
public class ConversationMessageRep {

    @Schema(description = "消息ID")
    private String id;

    @Schema(description = "消息角色：user / assistant")
    private String role;

    @Schema(description = "消息内容")
    private String content;

    @Schema(description = "思考内容")
    private String thinking;

    @Schema(description = "创建时间")
    private Instant createTime;
}
