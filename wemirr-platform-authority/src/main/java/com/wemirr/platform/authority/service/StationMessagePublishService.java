package com.wemirr.platform.authority.service;

import com.wemirr.framework.db.mybatis.SuperService;
import com.wemirr.platform.authority.domain.entity.message.StationMessagePublish;
import com.wemirr.platform.authority.domain.enums.ReceiverType;
import com.wemirr.platform.authority.domain.vo.CommonDataResp;

import java.util.List;

/**
 * @author levin
 */
public interface StationMessagePublishService extends SuperService<StationMessagePublish> {

    /**
     * 根据类型和条件查询
     *
     * @param type   类型
     * @param search 条件
     * @return 查询结果
     */
    List<CommonDataResp> queryReceiverByType(ReceiverType type, String search);

    /**
     * 发布消息
     *
     * @param id id
     */
    void publish(Long id);
}
