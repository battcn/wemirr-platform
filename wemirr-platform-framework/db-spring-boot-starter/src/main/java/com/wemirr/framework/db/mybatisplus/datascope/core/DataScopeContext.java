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

package com.wemirr.framework.db.mybatisplus.datascope.core;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 数据权限上下文
 * <p>使用栈结构支持嵌套调用场景</p>
 *
 * @author Levin
 */
public final class DataScopeContext {

    /**
     * 使用栈存储规则，支持方法嵌套调用时的不同数据权限控制
     */
    private static final TransmittableThreadLocal<Deque<DataScopeRule>> RULE_STACK =
            TransmittableThreadLocal.withInitial(ArrayDeque::new);

    private DataScopeContext() {
    }

    /**
     * 获取当前生效的规则（不移除）
     *
     * @return 当前规则，可能为null
     */
    public static DataScopeRule current() {
        Deque<DataScopeRule> deque = RULE_STACK.get();
        return deque.peek();
    }

    /**
     * 压入新规则
     *
     * @param rule 数据权限规则
     */
    static void push(DataScopeRule rule) {
        RULE_STACK.get().push(rule);
    }

    /**
     * 弹出当前规则
     */
    static void pop() {
        Deque<DataScopeRule> deque = RULE_STACK.get();
        deque.poll();
        if (deque.isEmpty()) {
            clear();
        }
    }

    /**
     * 清除上下文
     */
    public static void clear() {
        RULE_STACK.remove();
    }

    /**
     * 判断是否有规则生效
     *
     * @return true表示有规则
     */
    public static boolean hasRule() {
        Deque<DataScopeRule> deque = RULE_STACK.get();
        return deque != null && !deque.isEmpty();
    }
}
