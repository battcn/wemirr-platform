/*
 * Copyright (c) 2023 WEMIRR-PLATFORM Authors. All Rights Reserved.
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wemirr.framework.robot;

import com.wemirr.framework.robot.emums.NotifyType;
import com.wemirr.framework.robot.message.RobotMessage;
import com.wemirr.framework.robot.message.RobotSendResult;
import com.wemirr.framework.robot.message.push.RobotMessageHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 机器人消息发送客户端（流式API）
 *
 * <pre>{@code
 * // 发送文本消息到所有机器人
 * RobotClient.send("服务异常告警");
 *
 * // 发送到指定机器人
 * RobotClient.to(NotifyType.DING_TALK)
 *     .text("系统告警")
 *     .atAll()
 *     .send();
 *
 * // 发送Markdown消息
 * RobotClient.to(NotifyType.FEI_SHU)
 *     .markdown("告警标题", "**错误信息**: 数据库连接失败")
 *     .send();
 *
 * // 异步发送
 * RobotClient.sendAsync("异步消息").thenAccept(results -> {
 *     results.forEach(r -> log.info("发送结果: {}", r.isSuccess()));
 * });
 * }</pre>
 *
 * @author Levin
 */
@Slf4j
public final class RobotClient {

    private static final Map<NotifyType, RobotMessageHandler> HANDLERS = new ConcurrentHashMap<>();

    private RobotClient() {
    }

    /**
     * 注册消息处理器（由Spring自动配置调用）
     */
    public static void registerHandler(RobotMessageHandler handler) {
        HANDLERS.put(handler.notifyType(), handler);
        log.info("Registered robot handler: {}", handler.notifyType());
    }

    /**
     * 获取所有已注册的处理器类型
     */
    public static List<NotifyType> getRegisteredTypes() {
        return new ArrayList<>(HANDLERS.keySet());
    }

    // ==================== 快捷发送方法 ====================

    /**
     * 发送文本消息到所有已注册机器人
     */
    public static List<RobotSendResult> send(String message) {
        return HANDLERS.values().stream()
                .map(handler -> sendToHandler(handler, message))
                .collect(Collectors.toList());
    }

    /**
     * 发送消息对象到所有已注册机器人
     */
    public static List<RobotSendResult> send(RobotMessage message) {
        return HANDLERS.values().stream()
                .map(handler -> sendMessageToHandler(handler, message))
                .collect(Collectors.toList());
    }

    /**
     * 异步发送文本消息
     */
    public static CompletableFuture<List<RobotSendResult>> sendAsync(String message) {
        return CompletableFuture.supplyAsync(() -> send(message));
    }

    /**
     * 异步发送消息对象
     */
    public static CompletableFuture<List<RobotSendResult>> sendAsync(RobotMessage message) {
        return CompletableFuture.supplyAsync(() -> send(message));
    }

    // ==================== 链式调用入口 ====================

    /**
     * 指定发送目标
     */
    public static MessageBuilder to(NotifyType... types) {
        return new MessageBuilder(types);
    }

    /**
     * 发送到所有机器人
     */
    public static MessageBuilder toAll() {
        return new MessageBuilder(HANDLERS.keySet().toArray(new NotifyType[0]));
    }

    // ==================== 内部方法 ====================

    private static RobotSendResult sendToHandler(RobotMessageHandler handler, String message) {
        try {
            String response = handler.notify(message);
            return RobotSendResult.success(handler.notifyType(), response);
        } catch (Exception e) {
            log.error("Send message failed for {}: {}", handler.notifyType(), e.getMessage());
            return RobotSendResult.failure(handler.notifyType(), e);
        }
    }

    private static RobotSendResult sendMessageToHandler(RobotMessageHandler handler, RobotMessage message) {
        try {
            String content = buildContent(message);
            String response = handler.notify(content);
            return RobotSendResult.success(handler.notifyType(), response);
        } catch (Exception e) {
            log.error("Send message failed for {}: {}", handler.notifyType(), e.getMessage());
            return RobotSendResult.failure(handler.notifyType(), e);
        }
    }

    private static String buildContent(RobotMessage message) {
        return switch (message.getType()) {
            case TEXT, MARKDOWN -> message.getContent();
            case LINK -> String.format("[%s](%s)\n%s", message.getTitle(), message.getLinkUrl(), message.getContent());
            default -> message.getContent();
        };
    }

    // ==================== 消息构建器 ====================

    /**
     * 消息构建器（支持链式调用）
     */
    public static class MessageBuilder {
        private final NotifyType[] targets;
        private RobotMessage.MessageType type = RobotMessage.MessageType.TEXT;
        private String title;
        private String content;
        private boolean atAll = false;
        private List<String> atMobiles;

        MessageBuilder(NotifyType... targets) {
            this.targets = targets;
        }

        /**
         * 文本消息
         */
        public MessageBuilder text(String content) {
            this.type = RobotMessage.MessageType.TEXT;
            this.content = content;
            return this;
        }

        /**
         * Markdown消息
         */
        public MessageBuilder markdown(String title, String content) {
            this.type = RobotMessage.MessageType.MARKDOWN;
            this.title = title;
            this.content = content;
            return this;
        }

        /**
         * @所有人
         */
        public MessageBuilder atAll() {
            this.atAll = true;
            return this;
        }

        /**
         * @指定人
         */
        public MessageBuilder at(String... mobiles) {
            this.atMobiles = List.of(mobiles);
            return this;
        }

        /**
         * 同步发送
         */
        public List<RobotSendResult> send() {
            List<RobotSendResult> results = new ArrayList<>();
            for (NotifyType target : targets) {
                RobotMessageHandler handler = HANDLERS.get(target);
                if (handler == null) {
                    log.warn("No handler registered for: {}", target);
                    continue;
                }
                results.add(sendToHandler(handler, buildFinalContent()));
            }
            return results;
        }

        /**
         * 异步发送
         */
        public CompletableFuture<List<RobotSendResult>> sendAsync() {
            return CompletableFuture.supplyAsync(this::send);
        }

        private String buildFinalContent() {
            StringBuilder sb = new StringBuilder();
            if (title != null) {
                sb.append("### ").append(title).append("\n\n");
            }
            sb.append(content);
            if (atAll) {
                sb.append("\n@all");
            } else if (atMobiles != null && !atMobiles.isEmpty()) {
                sb.append("\n");
                atMobiles.forEach(m -> sb.append("@").append(m).append(" "));
            }
            return sb.toString();
        }
    }
}
