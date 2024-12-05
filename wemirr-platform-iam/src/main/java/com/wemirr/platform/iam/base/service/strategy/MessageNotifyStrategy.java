package com.wemirr.platform.iam.base.service.strategy;

import com.wemirr.platform.iam.base.domain.entity.MessageChannel;
import com.wemirr.platform.iam.base.domain.entity.MessageNotify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Levin
 * 消息推送策略
 */
public interface MessageNotifyStrategy {

    Logger log = LoggerFactory.getLogger(MessageNotifyStrategy.class);


    String channelType();

    /**
     * 消息处理
     *
     * @param channel 通知渠道
     * @param notify  通知内容
     */

    void handler(MessageChannel channel, MessageNotify notify);

}
