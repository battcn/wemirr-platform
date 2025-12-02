package com.wemirr.platform.ai.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author xJh
 * @since 2025/10/11
 * AI记忆消息持久化
 **/
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ai_chat_message_store")
public class ChatMsgStore {

    @TableId(type = IdType.INPUT)
    private String uid;


    private String message;
}
