package com.wemirr.framework.websocket;

import org.springframework.context.ApplicationEvent;

/**
 * @author Levin
 */
public class WebSocketCloseEvent extends ApplicationEvent {

    public WebSocketCloseEvent(WebSocket webSocket) {
        super(webSocket);
    }

}
