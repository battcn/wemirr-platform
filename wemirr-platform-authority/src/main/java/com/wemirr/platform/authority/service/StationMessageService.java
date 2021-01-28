package com.wemirr.platform.authority.service;

import com.wemirr.framework.boot.service.SuperService;
import com.wemirr.platform.authority.domain.dto.StationMessageReq;
import com.wemirr.platform.authority.domain.entity.StationMessage;

/**
 * @author Levin
 */
public interface StationMessageService extends SuperService<StationMessage> {


    /**
     * 推送消息
     *
     * @param req 消息
     */
    void pushMessage(StationMessageReq req);

}
