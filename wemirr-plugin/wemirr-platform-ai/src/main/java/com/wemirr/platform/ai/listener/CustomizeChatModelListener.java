package com.wemirr.platform.ai.listener;

import dev.langchain4j.model.chat.listener.ChatModelErrorContext;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import dev.langchain4j.model.chat.listener.ChatModelRequestContext;
import dev.langchain4j.model.chat.listener.ChatModelResponseContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author xJh
 * @since 2025/10/16
 **/
@Slf4j
@Component
public class CustomizeChatModelListener implements ChatModelListener {
    @Override
    public void onRequest(final ChatModelRequestContext requestContext) {
        final var chatRequest = requestContext.chatRequest();
        var messages = chatRequest.messages();
        log.debug("onRequest: {}", messages);
    }

    @Override
    public void onResponse(final ChatModelResponseContext responseContext) {
        final var chatResponse = responseContext.chatResponse();
        var aiMessage = chatResponse.aiMessage();
        log.debug("onResponse: {}", aiMessage);
    }

    @Override
    public void onError(final ChatModelErrorContext errorContext) {
        ChatModelListener.super.onError(errorContext);
    }


}
