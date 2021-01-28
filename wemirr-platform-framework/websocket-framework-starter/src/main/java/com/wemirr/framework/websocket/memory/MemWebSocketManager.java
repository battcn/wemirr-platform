package com.wemirr.framework.websocket.memory;

import com.wemirr.framework.websocket.WebSocket;
import com.wemirr.framework.websocket.WebSocketCloseEvent;
import com.wemirr.framework.websocket.WebSocketConnectEvent;
import com.wemirr.framework.websocket.WebSocketManager;
import com.wemirr.framework.websocket.utils.WebSocketUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Levin
 */
public class MemWebSocketManager implements WebSocketManager, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 因为全局只有一个 WebSocketManager ，所以才敢定义为非static
     */
    private final Map<String, WebSocket> connections = new ConcurrentHashMap<>(100);

    @Override
    public WebSocket get(String identifier) {
        return connections.get(identifier);
    }

    @Override
    public void put(String identifier, WebSocket webSocket) {
        connections.put(identifier, webSocket);
        //发送连接事件
        getApplicationContext().publishEvent(new WebSocketConnectEvent(webSocket));
    }

    @Override
    public void remove(String identifier) {
        WebSocket removedWebSocket = connections.remove(identifier);
        //发送关闭事件
        if (null != removedWebSocket) {
            getApplicationContext().publishEvent(new WebSocketCloseEvent(removedWebSocket));
        }
    }


    @Override
    public Map<String, WebSocket> localWebSocketMap() {
        return connections;
    }

    @Override
    public void sendMessage(String identifier, String message) {
        WebSocket webSocket = get(identifier);
        if (null == webSocket) {
            throw new RuntimeException("identifier 不存在");
        }
        WebSocketUtil.sendMessage(webSocket.getSession(), message);
    }

    @Override
    public void broadcast(String message) {
        localWebSocketMap().values().forEach(
                webSocket -> WebSocketUtil.sendMessage(
                        webSocket.getSession(), message));
    }

    @Override
    public void onMessage(String identifier, String message) {

    }
}
