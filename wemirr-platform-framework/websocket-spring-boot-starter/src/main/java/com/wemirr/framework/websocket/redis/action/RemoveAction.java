package com.wemirr.framework.websocket.redis.action;

import com.alibaba.fastjson2.JSONObject;
import com.wemirr.framework.websocket.WebSocket;
import com.wemirr.framework.websocket.WebSocketManager;

import java.util.Map;

/**
 * {
 * "action":"remove",
 * "identifier":"xxx"
 * }
 * 给webSocket发送消息的action
 *
 * @author Levin
 */
public class RemoveAction implements Action {
    @Override
    public void doMessage(WebSocketManager manager, JSONObject object) {
        if (!object.containsKey(IDENTIFIER)) {
            return;
        }
        String identifier = object.getString(IDENTIFIER);
        Map<String, WebSocket> localWebSocketMap = manager.localWebSocketMap();
        localWebSocketMap.remove(identifier);
    }
}
