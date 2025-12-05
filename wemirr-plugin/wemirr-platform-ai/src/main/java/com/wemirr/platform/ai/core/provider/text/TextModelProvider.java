package com.wemirr.platform.ai.core.provider.text;

import com.wemirr.platform.ai.domain.entity.ModelConfig;
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
    boolean supports(ModelConfig config);

    /**
     * 创建同步聊天模型
     */
    ChatModel createModel(ModelConfig config);

    /**
     * 创建流式聊天模型
     */
    StreamingChatModel createStreamModel(ModelConfig config);

    /**
     * TODO Tokenizer
     */

    /**
     * 获取提供商标识（使用枚举 code）
     */
    String providerId();
}
