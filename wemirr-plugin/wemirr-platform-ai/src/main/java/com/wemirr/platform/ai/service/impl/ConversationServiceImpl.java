package com.wemirr.platform.ai.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.ai.domain.dto.req.ConversationPageReq;
import com.wemirr.platform.ai.domain.dto.req.ConversationSaveReq;
import com.wemirr.platform.ai.domain.dto.resp.ConversationDetailResp;
import com.wemirr.platform.ai.domain.dto.resp.ConversationMessageResp;
import com.wemirr.platform.ai.domain.dto.resp.ConversationPageResp;
import com.wemirr.platform.ai.domain.entity.Conversation;
import com.wemirr.platform.ai.domain.entity.ConversationTurn;
import com.wemirr.platform.ai.repository.ConversationMapper;
import com.wemirr.platform.ai.repository.ConversationMessageMapper;
import com.wemirr.platform.ai.service.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

/**
 * @author xJh
 * @since 2025/10/11
 **/
@Service
@RequiredArgsConstructor
public class ConversationServiceImpl extends SuperServiceImpl<ConversationMapper, Conversation> implements ConversationService {

    private final AuthenticationContext context;
    private final ConversationMessageMapper conversationMessageMapper;


    @Override
    public IPage<ConversationPageResp> pageList(ConversationPageReq req) {
        Long userId = context.userId();
        Page<Conversation> result = this.page(req.buildPage(), Wraps.<Conversation>lbQ().eq(Conversation::getUserId, userId)
                .like(Conversation::getTitle, req.getTitle()).eq(Conversation::getType, req.getType())
                .eq(Conversation::getAgentId, req.getAgentId())
                .orderByDesc(Conversation::getPinned).orderByDesc(Conversation::getLastModifyTime).orderByDesc(Conversation::getId));
        return result.convert(this::convertToPageRep);
    }

    @Override
    public ConversationDetailResp detail(Long id) {
        Long userId = context.userId();
        Conversation conversation = this.getById(id);
        if (conversation == null) {
            throw new RuntimeException("会话不存在");
        }
        if (!conversation.getUserId().equals(userId)) {
            throw new RuntimeException("无权访问该会话");
        }
        return convertToDetailResp(conversation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Conversation create(ConversationSaveReq req) {
        Long userId = context.userId();
        Conversation conversation = BeanUtilPlus.toBean(req, Conversation.class);
        conversation.setUserId(userId);
        conversation.setMessageCount(0);
        conversation.setPinned(false);
        conversation.setLastModifyTime(Instant.now());
        this.save(conversation);
        return conversation;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modify(Long id, ConversationSaveReq req) {
        Long userId = context.userId();
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
        Long userId = context.userId();
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
        Long userId = context.userId();
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
        Long userId = context.userId();
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
        Long userId = context.userId();
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

    private ConversationPageResp convertToPageRep(Conversation conversation) {
        if (conversation == null) {
            return null;
        }
        return BeanUtilPlus.toBean(conversation, ConversationPageResp.class);

    }

    private ConversationDetailResp convertToDetailResp(Conversation conversation) {
        if (conversation == null) {
            return null;
        }
        ConversationDetailResp resp = new ConversationDetailResp();
        BeanUtils.copyProperties(conversation, resp);
        return resp;
    }

    @Override
    public List<ConversationMessageResp> turnList(Long conversationId) {
        Long userId = context.userId();
        Conversation conversation = this.getById(conversationId);
        if (conversation == null) {
            throw new RuntimeException("会话不存在");
        }
        if (!conversation.getUserId().equals(userId)) {
            throw new RuntimeException("无权访问该会话");
        }
        List<ConversationTurn> turnList = conversationMessageMapper.selectList(Wraps.<ConversationTurn>lbQ()
                .eq(ConversationTurn::getConversationId, conversationId).orderByAsc(ConversationTurn::getSequenceNum));
        return turnList.stream().map(turn -> BeanUtilPlus.toBean(turn, ConversationMessageResp.class)).toList();
    }

    @Override
    public List<ConversationMessageResp> messageList(Long kbId, Long agentId) {
        Long userId = context.userId();
        var conversation = this.baseMapper.selectOne(Wraps.<Conversation>lbQ().eq(Conversation::getKnowledgeBaseIds, kbId)
                .eq(Conversation::getAgentId, agentId).eq(Conversation::getUserId, userId));
        if (conversation == null) {
            return List.of();
        }
        List<ConversationTurn> turnList = conversationMessageMapper.selectList(Wraps.<ConversationTurn>lbQ()
                .eq(ConversationTurn::getConversationId, conversation.getId()).orderByAsc(ConversationTurn::getSequenceNum));
        return turnList.stream().map(turn -> BeanUtilPlus.toBean(turn, ConversationMessageResp.class)).toList();
    }
}
