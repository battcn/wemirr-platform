package com.wemirr.platform.ai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.ai.domain.dto.rep.ConversationDetailRep;
import com.wemirr.platform.ai.domain.dto.rep.ConversationMessageRep;
import com.wemirr.platform.ai.domain.dto.rep.ConversationPageRep;
import com.wemirr.platform.ai.domain.dto.req.ConversationPageReq;
import com.wemirr.platform.ai.domain.dto.req.ConversationSaveReq;
import com.wemirr.platform.ai.domain.entity.Conversation;

import java.util.List;

/**
 * @author xJh
 * @since 2025/10/11
 **/
public interface ConversationService extends SuperService<Conversation> {

    /**
     * 获取用户的所有会话
     */
    List<Conversation> getUserConversations(Long userId);

    /**
     * 分页获取用户会话
     */
    IPage<Conversation> pageUserConversations(Long userId, Page<Conversation> page);

    /**
     * 删除会话及其所有消息
     */
    void deleteConversation(Long conversationId);

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
    IPage<ConversationPageRep> pageList(ConversationPageReq req);

    /**
     * 获取会话详情
     *
     * @param id 会话ID
     * @return 会话详情
     */
    ConversationDetailRep detail(Long id);

    /**
     * 新增会话
     *
     * @param req 会话信息
     */
    void create(ConversationSaveReq req);

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
    List<ConversationMessageRep> getMessages(Long conversationId);

    ConversationDetailRep detailByKbid(Long id);

    List<ConversationMessageRep> messagesByKbid(Long id);

    List<ConversationMessageRep> messagesByAgent(Long id);
}
