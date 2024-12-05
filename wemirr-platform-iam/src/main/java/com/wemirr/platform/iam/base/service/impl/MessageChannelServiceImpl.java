package com.wemirr.platform.iam.base.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.platform.iam.base.domain.dto.req.MessageChannelSaveReq;
import com.wemirr.platform.iam.base.domain.dto.resp.MessageChannelDetailResp;
import com.wemirr.platform.iam.base.domain.entity.MessageChannel;
import com.wemirr.platform.iam.base.repository.MessageChannelMapper;
import com.wemirr.platform.iam.base.service.MessageChannelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MessageChannelServiceImpl extends SuperServiceImpl<MessageChannelMapper, MessageChannel> implements MessageChannelService {
    @Override
    public void setting(MessageChannelSaveReq req) {
        MessageChannel bean = BeanUtilPlus.toBean(req, MessageChannel.class);
        if (req.getId() == null) {
            this.baseMapper.insert(bean);
        } else {
            this.baseMapper.updateById(bean);
        }
    }

    @Override
    public MessageChannelDetailResp detail(String type) {
        MessageChannel channel = this.baseMapper.selectOne(MessageChannel::getType, type);
        MessageChannelDetailResp bean = BeanUtilPlus.toBeanIgnoreError(channel, MessageChannelDetailResp.class);
        if (StrUtil.isNotBlank(channel.getSetting())) {
            bean.setSetting(JSONObject.parseObject(channel.getSetting()));
        }
        return bean;
    }
}
