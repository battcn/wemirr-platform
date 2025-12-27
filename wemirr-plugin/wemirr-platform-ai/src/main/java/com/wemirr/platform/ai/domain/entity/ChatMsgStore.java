package com.wemirr.platform.ai.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * AI聊天消息持久化存储
 *
 * @author xJh
 * @since 2025/10/11
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ai_message_store")
@Schema(description = "AI聊天消息存储")
public class ChatMsgStore {

    @TableId(type = IdType.INPUT)
    @Schema(description = "消息唯一标识")
    private String messageId;

    @Schema(description = "消息内容")
    private String content;
}
