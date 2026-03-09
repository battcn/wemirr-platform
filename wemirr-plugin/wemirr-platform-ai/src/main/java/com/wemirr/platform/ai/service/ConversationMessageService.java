package com.wemirr.platform.ai.service;

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.ai.domain.dto.req.AssistantMessageSaveReq;
import com.wemirr.platform.ai.domain.dto.req.UserMessageSaveReq;
import com.wemirr.platform.ai.domain.entity.ConversationTurn;

/**
 * 会话消息服务接口
 *
 * @author xiao1
 * @since 2025-10
 */
public interface ConversationMessageService extends SuperService<ConversationTurn> {

    /**
     * 保存用户消息
     *
     * @param req 用户消息保存请求
     * @return 会话轮次
     */
    ConversationTurn saveUserMessage(UserMessageSaveReq req);

    /**
     * 异步保存 AI 回复消息
     *
     * @param req AI助手消息保存请求
     */
    void saveAssistantMessageAsync(AssistantMessageSaveReq req);

    /**
     * 更新消息状态（如重试、失败）
     *
     * @param messageId 消息ID
     * @param status    状态
     */
    void updateMessageStatusAsync(Long messageId, Integer status);
}
