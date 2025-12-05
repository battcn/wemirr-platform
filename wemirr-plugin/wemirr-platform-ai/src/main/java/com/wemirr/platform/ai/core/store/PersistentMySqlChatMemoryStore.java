package com.wemirr.platform.ai.core.store;

import com.wemirr.platform.ai.domain.entity.ChatMsgStore;
import com.wemirr.platform.ai.repository.ChatMsgStoreMapper;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static dev.langchain4j.data.message.ChatMessageDeserializer.messagesFromJson;
import static dev.langchain4j.data.message.ChatMessageSerializer.messagesToJson;

/**
 * @author xJh
 * @since 2025/10/11
 **/

@Slf4j
@Component
@RequiredArgsConstructor
public class PersistentMySqlChatMemoryStore implements ChatMemoryStore {

    private final ChatMsgStoreMapper chatMsgMapper;


    @SneakyThrows
    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        ChatMsgStore chatMsg = chatMsgMapper.selectById(memoryId.toString());
        return chatMsg != null ? messagesFromJson(chatMsg.getMessage()) : new ArrayList<>();
    }

    @SneakyThrows
    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> messages) {
        ChatMsgStore chatMsg = ChatMsgStore.builder().uid(memoryId.toString())
                .message(messagesToJson(messages)).build();

        if (chatMsgMapper.selectById(memoryId.toString()) != null) {
            chatMsgMapper.updateById(chatMsg);
        } else {
            chatMsgMapper.insert(chatMsg);
        }
    }

    @SneakyThrows
    @Override
    public void deleteMessages(Object memoryId) {
        chatMsgMapper.deleteById(memoryId.toString());
    }
}
