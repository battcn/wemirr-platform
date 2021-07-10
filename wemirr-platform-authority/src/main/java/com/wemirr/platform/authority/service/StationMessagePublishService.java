package com.wemirr.platform.authority.service;

import com.wemirr.framework.boot.service.SuperService;
import com.wemirr.platform.authority.domain.entity.message.StationMessagePublish;
import com.wemirr.platform.authority.domain.enums.ReceiverType;
import com.wemirr.platform.authority.domain.vo.CommonDataResp;

import java.util.List;

public interface StationMessagePublishService extends SuperService<StationMessagePublish> {

    List<CommonDataResp> queryReceiverByType(ReceiverType type, String search);
}
