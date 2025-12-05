package com.wemirr.platform.ai.core.assistant.interfaces;

import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.service.*;
import dev.langchain4j.service.memory.ChatMemoryAccess;

import java.util.List;

/**
 * @author xJh
 * @since 2025/10/11
 **/
public interface ChatAssistant extends ChatMemoryAccess {

    /**
     * 流式聊天助手
     */
    TokenStream chatStream(@MemoryId Long memoryId, @UserMessage String userMessage);

    /**
     * 普通rag问答
     */
    TokenStream chatRag(@MemoryId Long memoryId, @UserMessage String message);

    /**
     * 聊天助手，附带系统消息
     * @param memoryId
     * @param systemMessage
     * @param prompt
     * @param images
     * @return
     */
    @SystemMessage("{{sm}}")
    TokenStream chatWithSystem(@MemoryId Long memoryId, @V("sm") String systemMessage, @UserMessage String prompt, @UserMessage List<ImageContent> images);

    /**
     * 聊天助手，不带系统消息
     * @param memoryId
     * @param prompt
     * @param images
     * @return
     */
    TokenStream chat(@MemoryId Long memoryId, @UserMessage String prompt, @UserMessage List<ImageContent> images);

    // 提供访问和清除特定用户记忆的方法，增强可控性
    boolean evictChatMemory(@MemoryId int memoryId);
    ChatMemory getChatMemory(@MemoryId int memoryId);

}
