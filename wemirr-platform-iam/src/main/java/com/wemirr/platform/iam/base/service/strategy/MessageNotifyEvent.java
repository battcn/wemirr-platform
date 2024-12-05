package com.wemirr.platform.iam.base.service.strategy;

import com.wemirr.platform.iam.base.domain.entity.MessageNotify;
import com.wemirr.platform.iam.base.domain.entity.MessageTemplate;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

/**
 * @author Levin
 */
@Getter
public class MessageNotifyEvent extends ApplicationEvent {


    /**
     * 消息模板
     */
    private final MessageTemplate template;

    /**
     * 需要投递的消息
     */
    private final List<MessageNotify> messageNotifyList;


    public MessageNotifyEvent(MessageTemplate template, List<MessageNotify> messageNotifyList) {
        super(template);
        this.template = template;
        this.messageNotifyList = messageNotifyList;
    }
}
