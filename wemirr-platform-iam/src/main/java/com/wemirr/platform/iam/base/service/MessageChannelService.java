package com.wemirr.platform.iam.base.service;

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.iam.base.domain.dto.req.MessageChannelSaveReq;
import com.wemirr.platform.iam.base.domain.dto.resp.MessageChannelDetailResp;
import com.wemirr.platform.iam.base.domain.entity.MessageChannel;

/**
 * @author levin
 */
public interface MessageChannelService extends SuperService<MessageChannel> {

    /**
     * 渠道设置
     *
     * @param req req
     */
    void setting(MessageChannelSaveReq req);

    /**
     * 明细
     *
     * @param type 类型
     * @return 查询结果
     */
    MessageChannelDetailResp detail(String type);

}
