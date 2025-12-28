package com.wemirr.platform.ai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.ai.domain.dto.req.ConversationPageReq;
import com.wemirr.platform.ai.domain.dto.req.ConversationSaveReq;
import com.wemirr.platform.ai.domain.dto.resp.ConversationDetailResp;
import com.wemirr.platform.ai.domain.dto.resp.ConversationMessageResp;
import com.wemirr.platform.ai.domain.dto.resp.ConversationPageResp;
import com.wemirr.platform.ai.domain.entity.Conversation;

import java.util.List;

/**
 * @author xJh
 * @since 2025/10/11
 **/
public interface ConversationService extends SuperService<Conversation> {

    /**
     * 清空会话消息
     *
     * @param id 会话ID
     */
    void clearMessages(Long id);

    /**
     * 置顶会话
     *
     * @param id 会话ID
     */
    void pin(Long id);

    /**
     * 取消置顶会话
     *
     * @param id 会话ID
     */
    void unpin(Long id);

    /**
     * 分页查询会话
     *
     * @param req 查询条件
     * @return 分页结果
     */
    IPage<ConversationPageResp> pageList(ConversationPageReq req);

    /**
     * 获取会话详情
     *
     * @param id 会话ID
     * @return 会话详情
     */
    ConversationDetailResp detail(Long id);

    /**
     * 新增会话
     *
     * @param req 会话信息
     * @return 会话内容
     */
    Conversation create(ConversationSaveReq req);

    /**
     * 修改会话
     *
     * @param id  会话ID
     * @param req 会话信息
     */
    void modify(Long id, ConversationSaveReq req);

    /**
     * 删除会话
     *
     * @param id 会话ID
     */
    void remove(Long id);

    /**
     * 获取会话消息列表
     *
     * @param conversationId 会话ID
     * @return 消息列表
     */
    List<ConversationMessageResp> turnList(Long conversationId);

    List<ConversationMessageResp> messageList(Long kbId, Long agentId);

}
