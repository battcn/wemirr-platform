package com.wemirr.platform.ai.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.ai.domain.dto.rep.ChatAgentDetailRep;
import com.wemirr.platform.ai.domain.dto.rep.ChatAgentPageRep;
import com.wemirr.platform.ai.domain.dto.req.ChatAgentPageReq;
import com.wemirr.platform.ai.domain.dto.req.ChatAgentSaveReq;
import com.wemirr.platform.ai.domain.entity.ChatAgent;
import com.wemirr.platform.ai.domain.entity.Conversation;
import com.wemirr.platform.ai.repository.ChatAgentMapper;
import com.wemirr.platform.ai.service.ChatAgentService;
import com.wemirr.platform.ai.service.ConversationService;
import com.wemirr.platform.suite.feign.OssFileFeign;
import com.wemirr.platform.suite.feign.domain.resp.OssFileResp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author xJh
 * @since 2025/11/4
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class ChatAgentServiceImpl extends SuperServiceImpl<ChatAgentMapper, ChatAgent> implements ChatAgentService {

    private final AuthenticationContext context;
    private final OssFileFeign ossFileFeign;
    private final ConversationService conversationService;

    @Override
    public IPage<ChatAgentPageRep> pageList(ChatAgentPageReq req) {
        return this.baseMapper.selectPage(req.buildPage(), Wraps.<ChatAgent>lbQ().like(ChatAgent::getName, req.getName())
                .eq(ChatAgent::getUserId, req.getUserId())
                .orderByDesc(ChatAgent::getLastModifyTime)).convert(x -> BeanUtil.toBean(x, ChatAgentPageRep.class));
    }

    @Override
    public ChatAgentDetailRep detail(Long id) {
        ChatAgent chatAgent = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("智能体不存在"));
        return BeanUtil.toBean(chatAgent, ChatAgentDetailRep.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(ChatAgentSaveReq req) {
        Long userId = context.userId();
        // 检查智能体名称是否重复
        if (existsByName(req.getName(), userId, null)) {
            throw CheckedException.badRequest("智能体名称已存在");
        }
        ChatAgent chatAgent = BeanUtil.toBean(req, ChatAgent.class);
        chatAgent.setUserId(userId);
        this.baseMapper.insert(chatAgent);
        log.info("创建智能体成功，ID: {}, 名称: {}", chatAgent.getId(), chatAgent.getName());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modify(Long id, ChatAgentSaveReq req) {
        ChatAgent existAgent = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("智能体不存在"));
        Long userId = context.userId();
        // 检查权限：只能修改自己创建的智能体
        if (!Objects.equals(existAgent.getUserId(), userId)) {
            throw CheckedException.forbidden("无权限修改此智能体");
        }
        // 检查智能体名称是否重复（排除当前记录）
        if (existsByName(req.getName(), userId, id)) {
            throw CheckedException.badRequest("智能体名称已存在");
        }
        ChatAgent chatAgent = BeanUtil.toBean(req, ChatAgent.class);
        chatAgent.setId(id);
        // 保持原用户ID
        chatAgent.setUserId(existAgent.getUserId());
        this.baseMapper.updateById(chatAgent);
        log.info("修改智能体成功，ID: {}, 名称: {}", id, chatAgent.getName());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(Long id) {
        ChatAgent existAgent = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("智能体不存在"));
        Long userId = context.userId();
        // 检查权限：只能删除自己创建的智能体
        if (!Objects.equals(existAgent.getUserId(), userId)) {
            throw CheckedException.forbidden("无权限删除此智能体");
        }
        this.baseMapper.deleteById(id);
        log.info("删除智能体成功，ID: {}", id);
    }

    @Override
    public List<ChatAgent> listByUserId(Long userId) {
        return this.baseMapper.selectList(Wraps.<ChatAgent>lbQ().eq(ChatAgent::getUserId, userId).orderByDesc(ChatAgent::getLastModifyTime));
    }


    @Override
    public boolean existsByName(String name, Long userId, Long id) {
        if (!StringUtils.hasText(name) || userId == null) {
            return false;
        }
        return this.baseMapper.selectCount(Wraps.<ChatAgent>lbQ().eq(ChatAgent::getName, name)
                .eq(ChatAgent::getUserId, userId).ne(id != null, ChatAgent::getId, id)) > 0;
    }

    @Override
    public String uploadAvatar(MultipartFile file) {
        OssFileResp upload = ossFileFeign.upload(file);
        return upload.getUrl();

    }

    @Override
    public ChatAgentDetailRep detailByAgentId(Long agentId) {
        Conversation one = conversationService.getOne(Wraps.<Conversation>lbQ().eq(Conversation::getId, agentId));
        return BeanUtilPlus.toBean(one, ChatAgentDetailRep.class);
    }

    @Override
    public List<ChatAgent> listByModelId(String modelId) {
        return this.baseMapper.selectList(Wraps.<ChatAgent>lbQ().eq(ChatAgent::getChatModelId, modelId).orderByDesc(ChatAgent::getLastModifyTime));

    }
}
