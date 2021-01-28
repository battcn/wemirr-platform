package com.wemirr.framework.websocket.redis.action;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Levin
 * 将所有的Action配置进容器，通过名字找到
 */
@Configuration
@Import({SendMessageAction.class, BroadCastAction.class, RemoveAction.class, NoActionAction.class})
public class ActionConfig {

}