package com.wemirr.framework.websocket.redis.action;

import com.alibaba.fastjson2.JSONObject;
import com.wemirr.framework.websocket.WebSocketManager;
import com.wemirr.framework.websocket.utils.WebSocketUtil;

/**
 * {
 * "action":"broadcast",
 * "message":"xxxxxxxxxxxxx"
 * }
 * 广播给所有的websocket发送消息 action
 *
 * @author Levin
 */
public class BroadCastAction implements Action {
    @Override
    public void doMessage(WebSocketManager manager, JSONObject object) {
        if (!object.containsKey(MESSAGE)) {
            return;
        }
        String message = object.getString(MESSAGE);
        //从本地取出所有的websocket发送消息
        manager.localWebSocketMap().values().forEach(
                webSocket -> WebSocketUtil.sendMessage(
                        webSocket.getSession(), message));
    }
}
