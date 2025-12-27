package com.wemirr.framework.ai.core.model;

import java.util.Map;

/**
 * 模型配置接口（通用抽象）
 * <p>
 * 定义 AI 模型配置的通用契约，业务模块的实体类（如 ModelEntity）实现此接口即可与 AI Starter 集成。
 * </p>
 *
 * <h3>使用示例</h3>
 * <pre>{@code
 * // 方式1：业务实体实现接口
 * public class ModelEntity implements ModelConfig {
 *     // 实现接口方法...
 * }
 *
 * // 方式2：使用简单实现
 * ModelConfig config = SimpleModelConfig.builder()
 *     .name("qwen-plus")
 *     .providerCode("qwen")
 *     .apiKey("sk-xxx")
 *     .build();
 * }</pre>
 *
 * @author Levin
 * @since 2025/12/27
 */
public interface ModelConfig {

    /**
     * 获取模型名称
     * <p>
     * 如：qwen-plus、gpt-4、deepseek-chat-r1
     * </p>
     *
     * @return 模型名称
     */
    String getName();

    /**
     * 获取 API Key
     *
     * @return API 密钥
     */
    String getApiKey();

    /**
     * 获取 API Base URL
     * <p>
     * 用于自定义 API 端点，如私有部署或代理
     * </p>
     *
     * @return Base URL，可为 null 使用默认值
     */
    String getBaseUrl();

    /**
     * 获取提供商编码
     * <p>
     * 如：qwen、openai、deepseek、ernie
     * </p>
     *
     * @return 提供商编码
     */
    String getProviderCode();

    /**
     * 获取模型类型
     * <p>
     * 如：TEXT、EMBEDDING、RERANK、IMAGE、AUDIO
     * </p>
     *
     * @return 模型类型编码
     */
    String getModelType();

    /**
     * 获取扩展参数
     * <p>
     * 包含模型特定参数，如：
     * <ul>
     *   <li>max_tokens - 最大生成 Token 数</li>
     *   <li>temperature - 温度参数</li>
     *   <li>top_p - Top-P 采样</li>
     *   <li>frequency_penalty - 频率惩罚</li>
     *   <li>presence_penalty - 存在惩罚</li>
     * </ul>
     * </p>
     *
     * @return 参数 Map，可为 null
     */
    Map<String, Object> getVariables();

    /**
     * 是否启用深度思考模式
     * <p>
     * 部分模型（如 DeepSeek R1）支持返回思考过程
     * </p>
     *
     * @return 是否启用，默认 false
     */
    default Boolean getReturnThinking() {
        return false;
    }

    /**
     * 是否启用联网搜索
     * <p>
     * 部分模型（如 Qwen）支持联网搜索增强
     * </p>
     *
     * @return 是否启用，默认 false
     */
    default Boolean getEnableWebSearch() {
        return false;
    }
}
