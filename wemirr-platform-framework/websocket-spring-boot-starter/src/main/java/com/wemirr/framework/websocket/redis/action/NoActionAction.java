package com.wemirr.framework.websocket.redis.action;

import com.alibaba.fastjson2.JSONObject;
import com.wemirr.framework.websocket.WebSocketManager;

/**
 * do nothing action
 *
 * @author Levin
 */
public class NoActionAction implements Action {
    @Override
    public void doMessage(WebSocketManager manager, JSONObject object) {
        // do no thing
    }
}
