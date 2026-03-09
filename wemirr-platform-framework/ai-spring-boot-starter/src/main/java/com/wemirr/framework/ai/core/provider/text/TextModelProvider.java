package com.wemirr.framework.ai.core.provider.text;

import com.wemirr.framework.ai.core.enums.ModelType;
import com.wemirr.framework.ai.core.model.ModelConfig;
import com.wemirr.framework.ai.core.provider.ModelProvider;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;

/**
 * 文本模型提供者接口
 *
 * @author Levin
 * @since 2025/10/11
 */
public interface TextModelProvider extends ModelProvider<ChatModel> {

    @Override
    default ModelType getModelType() {
        return ModelType.TEXT;
    }

    /**
     * 创建流式聊天模型
     *
     * @param config 模型配置
     * @return 流式聊天模型实例
     */
    StreamingChatModel createStreamingModel(ModelConfig config);
}
