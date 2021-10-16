package com.wemirr.framework.websocket;

import org.springframework.context.ApplicationEvent;

/**
 * @author Levin
 */
public class WebSocketConnectEvent extends ApplicationEvent {
    public WebSocketConnectEvent(WebSocket webSocket) {
        super(webSocket);
    }
}
