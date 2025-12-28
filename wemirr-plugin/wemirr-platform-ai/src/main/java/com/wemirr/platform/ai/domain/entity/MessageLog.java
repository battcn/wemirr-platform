package com.wemirr.platform.ai.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.Entity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@TableName("ai_message_log")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "AI聊天消息存储")
public class MessageLog extends Entity<Long> {

    @Schema(description = "消息内容")
    private String content;
}
