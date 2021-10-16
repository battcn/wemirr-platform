package com.wemirr.framework.websocket.utils;

import javax.websocket.Session;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.Future;

/**
 * @author Levin
 */
public class WebSocketUtil {
    /**
     * 发送消息
     */
    public static boolean sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 异步发送消息
     */
    public static boolean sendMessageAsync(Session session, String message) {
        Future<Void> voidFuture = session.getAsyncRemote().sendText(message);
        return voidFuture.isDone();
    }

    /**
     * 发送字节消息
     */
    public static boolean sendBytes(Session session, byte[] bytes) {
        try {
            session.getBasicRemote().sendBinary(ByteBuffer.wrap(bytes));
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 异步发送字节
     */
    public static boolean sendBytesAsync(Session session, byte[] bytes) {
        Future<Void> voidFuture = session.getAsyncRemote().sendBinary(ByteBuffer.wrap(bytes));
        return voidFuture.isDone();
    }

    /**
     * 发送对象消息
     */
    public static boolean sendObject(Session session, Object o) {
        try {
            session.getBasicRemote().sendObject(o);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 异步发送对象
     */
    public static boolean sendObjectAsync(Session session, Object o) {
        Future<Void> voidFuture = session.getAsyncRemote().sendObject(o);
        return voidFuture.isDone();
    }
}
