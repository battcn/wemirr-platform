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

package com.wemirr.framework.robot.message;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 机器人消息实体（JDK21优化）
 *
 * @author Levin
 */
@Data
@Builder
public class RobotMessage {

    /**
     * 消息类型
     */
    private MessageType type;

    /**
     * 消息标题（用于markdown和卡片消息）
     */
    private String title;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 是否@所有人
     */
    @Builder.Default
    private boolean atAll = false;

    /**
     * @指定人（手机号或用户ID）
     */
    private List<String> atMobiles;

    /**
     * @指定用户ID（飞书等平台）
     */
    private List<String> atUserIds;

    /**
     * 图片URL（用于图片消息）
     */
    private String imageUrl;

    /**
     * 图片Base64（用于图片消息）
     */
    private String imageBase64;

    /**
     * 链接URL（用于链接和卡片消息）
     */
    private String linkUrl;

    /**
     * 消息类型枚举
     */
    public enum MessageType {
        /**
         * 文本消息
         */
        TEXT,
        /**
         * Markdown消息
         */
        MARKDOWN,
        /**
         * 图片消息
         */
        IMAGE,
        /**
         * 链接消息
         */
        LINK,
        /**
         * 卡片消息
         */
        CARD
    }

    // ==================== 静态工厂方法 ====================

    /**
     * 创建文本消息
     */
    public static RobotMessage text(String content) {
        return RobotMessage.builder()
                .type(MessageType.TEXT)
                .content(content)
                .build();
    }

    /**
     * 创建文本消息（@所有人）
     */
    public static RobotMessage textAtAll(String content) {
        return RobotMessage.builder()
                .type(MessageType.TEXT)
                .content(content)
                .atAll(true)
                .build();
    }

    /**
     * 创建Markdown消息
     */
    public static RobotMessage markdown(String title, String content) {
        return RobotMessage.builder()
                .type(MessageType.MARKDOWN)
                .title(title)
                .content(content)
                .build();
    }

    /**
     * 创建图片消息
     */
    public static RobotMessage image(String imageUrl) {
        return RobotMessage.builder()
                .type(MessageType.IMAGE)
                .imageUrl(imageUrl)
                .build();
    }

    /**
     * 创建链接消息
     */
    public static RobotMessage link(String title, String content, String linkUrl, String imageUrl) {
        return RobotMessage.builder()
                .type(MessageType.LINK)
                .title(title)
                .content(content)
                .linkUrl(linkUrl)
                .imageUrl(imageUrl)
                .build();
    }
}
