package com.wemirr.platform.authority.service;

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.authority.domain.entity.message.SiteNotify;
import com.wemirr.platform.authority.domain.enums.ReceiverType;
import com.wemirr.platform.authority.domain.resp.CommonDataResp;

import java.util.List;

/**
 * @author levin
 */
public interface SiteNotifyService extends SuperService<SiteNotify> {

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
