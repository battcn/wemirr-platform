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

import com.wemirr.framework.robot.emums.NotifyType;

/**
 * 机器人发送结果（使用JDK21 Record + Sealed类型）
 *
 * @author Levin
 */
public sealed interface RobotSendResult permits RobotSendResult.Success, RobotSendResult.Failure {

    /**
     * 是否成功
     */
    boolean isSuccess();

    /**
     * 获取通知类型
     */
    NotifyType notifyType();

    /**
     * 获取原始响应
     */
    String response();

    /**
     * 成功结果
     */
    record Success(NotifyType notifyType, String response) implements RobotSendResult {
        @Override
        public boolean isSuccess() {
            return true;
        }
    }

    /**
     * 失败结果
     */
    record Failure(NotifyType notifyType, String response, Throwable error) implements RobotSendResult {
        @Override
        public boolean isSuccess() {
            return false;
        }

        public Failure(NotifyType notifyType, Throwable error) {
            this(notifyType, null, error);
        }
    }

    /**
     * 创建成功结果
     */
    static RobotSendResult success(NotifyType type, String response) {
        return new Success(type, response);
    }

    /**
     * 创建失败结果
     */
    static RobotSendResult failure(NotifyType type, Throwable error) {
        return new Failure(type, error);
    }

    /**
     * 创建失败结果（带响应）
     */
    static RobotSendResult failure(NotifyType type, String response, Throwable error) {
        return new Failure(type, response, error);
    }
}
