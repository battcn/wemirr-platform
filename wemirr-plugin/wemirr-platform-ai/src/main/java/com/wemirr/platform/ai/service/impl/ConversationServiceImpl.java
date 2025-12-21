package com.wemirr.platform.ai.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.ai.domain.dto.rep.ConversationDetailRep;
import com.wemirr.platform.ai.domain.dto.rep.ConversationMessageRep;
import com.wemirr.platform.ai.domain.dto.rep.ConversationPageRep;
import com.wemirr.platform.ai.domain.dto.req.ConversationPageReq;
import com.wemirr.platform.ai.domain.dto.req.ConversationSaveReq;
import com.wemirr.platform.ai.domain.entity.Conversation;
import com.wemirr.platform.ai.domain.entity.ConversationMessage;
import com.wemirr.platform.ai.repository.ConversationMapper;
import com.wemirr.platform.ai.repository.ConversationMessageMapper;
import com.wemirr.platform.ai.service.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xJh
 * @since 2025/10/11
 **/
@Service
@RequiredArgsConstructor
public class ConversationServiceImpl extends SuperServiceImpl<ConversationMapper, Conversation> implements ConversationService {

    private final AuthenticationContext authenticationContext;
    private final ConversationMessageMapper conversationMessageMapper;

    @Override
    public List<Conversation> getUserConversations(Long userId) {
        return this.list(Wraps.<Conversation>lbQ()
                .eq(Conversation::getUserId, userId)
                .orderByDesc(Conversation::getPinned)
                .orderByDesc(Conversation::getLastModifyTime));
    }

    @Override
    public IPage<Conversation> pageUserConversations(Long userId, Page<Conversation> page) {
        return this.page(page, Wraps.<Conversation>lbQ()
                .eq(Conversation::getUserId, userId)
                .orderByDesc(Conversation::getPinned)
                .orderByDesc(Conversation::getLastModifyTime));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteConversation(Long conversationId) {
        Conversation conversation = this.getById(conversationId);
        if (conversation == null) {
            throw new RuntimeException("会话不存在");
        }
        if (!conversation.getUserId().equals(authenticationContext.userId())) {
            throw new RuntimeException("无权删除该会话");
        }
        this.removeById(conversationId);
    }

    @Override
    public IPage<ConversationPageRep> pageList(ConversationPageReq req) {
        Long userId = authenticationContext.userId();
        Page<Conversation> page = new Page<>(req.getCurrent(), req.getSize());
        Page<Conversation> result = this.page(page, Wraps.<Conversation>lbQ()
                .eq(Conversation::getUserId, userId)
                .eq(Conversation::getType, req.getType())
                .like(req.getTitle() != null, Conversation::getTitle, req.getTitle())
                .orderByDesc(Conversation::getPinned)
                .orderByDesc(Conversation::getLastModifyTime));

        return result.convert(this::convertToPageRep);
    }

    @Override
    public ConversationDetailRep detail(Long id) {
        Long userId = authenticationContext.userId();
        Conversation conversation = this.getById(id);
        if (conversation == null) {
            throw new RuntimeException("会话不存在");
        }
        if (!conversation.getUserId().equals(userId)) {
            throw new RuntimeException("无权访问该会话");
        }
        return convertToDetailRep(conversation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(ConversationSaveReq req) {
        Long userId = authenticationContext.userId();
//        Conversation conversation = new Conversation();
//        BeanUtils.copyProperties(req, conversation);
        Conversation conversation = BeanUtilPlus.toBean(req, Conversation.class);
        conversation.setUserId(userId);
        conversation.setMessageCount(0);
        conversation.setPinned(false);
        this.save(conversation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modify(Long id, ConversationSaveReq req) {
        Long userId = authenticationContext.userId();
        Conversation conversation = this.getById(id);
        if (conversation == null) {
            throw new RuntimeException("会话不存在");
        }
        if (!conversation.getUserId().equals(userId)) {
            throw new RuntimeException("无权修改该会话");
        }
        // 只允许修改名称
        conversation.setTitle(req.getTitle());
        this.updateById(conversation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(Long id) {
        Long userId = authenticationContext.userId();
        Conversation conversation = this.getById(id);
        if (conversation == null) {
            throw new RuntimeException("会话不存在");
        }
        if (!conversation.getUserId().equals(userId)) {
            throw new RuntimeException("无权删除该会话");
        }
        this.removeById(id);
    }

//    @Override
//    public ChatAgentDetailRep detailByAgentId(Long agentId) {
//        Conversation one = this.getOne(Wraps.<Conversation>lbQ().eq(Conversation::getAgentId, agentId));
//        return BeanUtilPlus.copyProperties(one, ChatAgentDetailRep.class);
//
//    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void clearMessages(Long id) {
        Long userId = authenticationContext.userId();
        Conversation conversation = this.getById(id);
        if (conversation == null) {
            throw new RuntimeException("会话不存在");
        }
        if (!conversation.getUserId().equals(userId)) {
            throw new RuntimeException("无权清空该会话消息");
        }
        conversation.setMessageCount(0);
        conversation.setLastMessage(null);
        this.updateById(conversation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pin(Long id) {
        Long userId = authenticationContext.userId();
        Conversation conversation = this.getById(id);
        if (conversation == null) {
            throw new RuntimeException("会话不存在");
        }
        if (!conversation.getUserId().equals(userId)) {
            throw new RuntimeException("无权置顶该会话");
        }
        conversation.setPinned(true);
        this.updateById(conversation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void unpin(Long id) {
        Long userId = authenticationContext.userId();
        Conversation conversation = this.getById(id);
        if (conversation == null) {
            throw new RuntimeException("会话不存在");
        }
        if (!conversation.getUserId().equals(userId)) {
            throw new RuntimeException("无权取消置顶该会话");
        }
        conversation.setPinned(false);
        this.updateById(conversation);
    }

    private ConversationPageRep convertToPageRep(Conversation conversation) {
        if (conversation == null) {
            return null;
        }
        return BeanUtilPlus.toBean(conversation, ConversationPageRep.class);

    }

    private ConversationDetailRep convertToDetailRep(Conversation conversation) {
        if (conversation == null) {
            return null;
        }
        ConversationDetailRep rep = new ConversationDetailRep();
        BeanUtils.copyProperties(conversation, rep);
        return rep;
    }

    @Override
    public List<ConversationMessageRep> getMessages(Long conversationId) {
        Long userId = authenticationContext.userId();
        Conversation conversation = this.getById(conversationId);
        if (conversation == null) {
            throw new RuntimeException("会话不存在");
        }
        if (!conversation.getUserId().equals(userId)) {
            throw new RuntimeException("无权访问该会话");
        }
        
        List<ConversationMessage> messages = conversationMessageMapper.selectList(
                Wraps.<ConversationMessage>lbQ()
                        .eq(ConversationMessage::getConversationId, conversationId)
                        .orderByAsc(ConversationMessage::getSequenceNum)
        );
        
        return messages.stream().map(msg -> ConversationMessageRep.builder()
                .id(String.valueOf(msg.getId()))
                .role(msg.getRole())
                .content(msg.getDisplayContent() != null ? msg.getDisplayContent() : msg.getRawContent())
                .thinking(msg.getThinkingContent())
                .createTime(msg.getCreateTime())
                .build()
        ).toList();
    }

    @Override
    public ConversationDetailRep detailByKbid(Long id) {

        return null;
    }

    @Override
    public List<ConversationMessageRep> messagesByKbid(Long id) {
        Long userId = authenticationContext.userId();
        Conversation conversation = this.getOne(Wraps.<Conversation>lbQ()
                .eq(Conversation::getKnowledgeBaseIds, id)
                .eq(Conversation::getUserId, userId)
        );
        if (conversation == null) {
            return List.of();
        }
        if (!conversation.getUserId().equals(userId)) {
            throw new RuntimeException("无权访问该会话");
        }

        List<ConversationMessage> messages = conversationMessageMapper.selectList(
                Wraps.<ConversationMessage>lbQ()
                        .eq(ConversationMessage::getConversationId, conversation.getId())
                        .orderByAsc(ConversationMessage::getSequenceNum)
        );

        return messages.stream().map(msg -> ConversationMessageRep.builder()
                .id(String.valueOf(msg.getId()))
                .role(msg.getRole())
                .content(msg.getDisplayContent() != null ? msg.getDisplayContent() : msg.getRawContent())
                .thinking(msg.getThinkingContent())
                .createTime(msg.getCreateTime())
                .build()
        ).toList();
    }

    @Override
    public List<ConversationMessageRep> messagesByAgent(Long id) {
        Long userId = authenticationContext.userId();
        Conversation conversation = this.getOne(Wraps.<Conversation>lbQ()
                .eq(Conversation::getAgentId, id)
                .eq(Conversation::getUserId, userId)
        );
        if (conversation == null) {
            return List.of();
        }
        if (!conversation.getUserId().equals(userId)) {
            throw new RuntimeException("无权访问该会话");
        }

        List<ConversationMessage> messages = conversationMessageMapper.selectList(
                Wraps.<ConversationMessage>lbQ()
                        .eq(ConversationMessage::getConversationId, conversation.getId())
                        .orderByAsc(ConversationMessage::getSequenceNum)
        );

        return messages.stream().map(msg -> ConversationMessageRep.builder()
                .id(String.valueOf(msg.getId()))
                .role(msg.getRole())
                .content(msg.getDisplayContent() != null ? msg.getDisplayContent() : msg.getRawContent())
                .thinking(msg.getThinkingContent())
                .createTime(msg.getCreateTime())
                .build()
        ).toList();
    }

}
