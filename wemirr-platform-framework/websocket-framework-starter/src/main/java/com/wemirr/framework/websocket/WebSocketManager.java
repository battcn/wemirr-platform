package com.wemirr.framework.websocket;

import java.util.Map;

/**
 * 管理websocket的session,可以使用Map，可以自己添加接口功能实现，并放到容器中
 * 注意：放在容器中的名字必须是 WebSocketManager.WEBSOCKET_MANAGER_NAME 即：webSocketManager
 *
 * @author Levin
 */
public interface WebSocketManager {
    /**
     * 在容器中的名字
     */
    String WEBSOCKET_MANAGER_NAME = "webSocketManager";

    /**
     * 根据标识获取websocket session
     *
     * @param identifier 标识
     * @return WebSocket
     */
    WebSocket get(String identifier);

    /**
     * 放入一个 websocket session
     *
     * @param identifier 标识
     * @param webSocket  websocket
     */
    void put(String identifier, WebSocket webSocket);

    /**
     * 删除
     *
     * @param identifier 标识
     */
    void remove(String identifier);

    /**
     * 获取当前机器上的保存的WebSocket
     *
     * @return WebSocket Map
     */
    Map<String, WebSocket> localWebSocketMap();

    /**
     * 统计所有在线人数
     *
     * @return 所有在线人数
     */
    default int size() {
        return localWebSocketMap().size();
    }

    /**
     * 给某人发送消息
     *
     * @param identifier 标识
     * @param message    消息
     */
    void sendMessage(String identifier, String message);

    /**
     * 广播
     *
     * @param message 消息
     */
    void broadcast(String message);

    /**
     * WebSocket接收到消息的函数调用
     *
     * @param identifier 标识
     * @param message    消息内容
     */
    void onMessage(String identifier, String message);

    /**
     * 在OnMessage中判断是否是心跳,
     * 从客户端的消息判断是否是ping消息
     *
     * @param identifier 标识
     * @param message    消息
     * @return 是否是ping消息
     */
    default boolean isPing(String identifier, String message) {
        return "ping".equalsIgnoreCase(message);
    }

    /**
     * 返回心跳信息
     *
     * @param identifier 标识
     * @param message    消息
     * @return 返回的pong消息
     */
    default String pong(String identifier, String message) {
        return "pong";
    }
}
