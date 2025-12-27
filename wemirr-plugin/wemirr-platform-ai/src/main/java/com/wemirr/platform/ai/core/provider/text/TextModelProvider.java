package com.wemirr.platform.ai.core.provider.text;

import com.wemirr.platform.ai.domain.entity.ModelEntity;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;

/**
 * @author xJh
 * @since 2025/10/11
 **/
public interface TextModelProvider {

    /**
     * 是否支持该配置
     */
    boolean supports(ModelEntity config);

    /**
     * 创建同步聊天模型
     */
    ChatModel createModel(ModelEntity config);

    /**
     * 创建流式聊天模型
     */
    StreamingChatModel createStreamModel(ModelEntity config);

    /**
     * TODO Tokenizer
     */

    /**
     * 获取提供商标识（使用枚举 code）
     */
    String providerId();
}
