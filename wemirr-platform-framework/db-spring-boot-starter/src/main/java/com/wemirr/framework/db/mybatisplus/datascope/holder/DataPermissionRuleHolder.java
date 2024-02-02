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

package com.wemirr.framework.db.mybatisplus.datascope.holder;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.wemirr.framework.db.mybatisplus.datascope.annotation.DataScope;
import com.wemirr.framework.db.mybatisplus.datascope.handler.DataPermissionRule;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 数据权限规则的持有者，使用栈存储调用链中的数据权限规则
 * <p>
 * 区别于{@link DataScope}
 * {@link DataPermissionRule} 是编程式数据权限控制的使用，优先级高于注解
 *
 * @author Levin
 */
public final class DataPermissionRuleHolder {
    
    private DataPermissionRuleHolder() {
    }
    
    /**
     * 使用栈存储 DataPermissionRule，便于在方法嵌套调用时使用不同的数据权限控制。
     */
    private static final TransmittableThreadLocal<Deque<DataPermissionRule>> DATA_PERMISSION_RULES = TransmittableThreadLocal
            .withInitial(ArrayDeque::new);
    
    /**
     * 获取当前的 DataPermissionRule
     *
     * @return DataPermissionRule
     */
    public static DataPermissionRule peek() {
        Deque<DataPermissionRule> deque = DATA_PERMISSION_RULES.get();
        return deque == null ? null : deque.peek();
    }
    
    /**
     * 入栈一个 DataPermissionRule
     *
     * @return DataPermissionRule
     */
    public static DataPermissionRule push(DataPermissionRule dataPermissionRule) {
        Deque<DataPermissionRule> deque = DATA_PERMISSION_RULES.get();
        if (deque == null) {
            deque = new ArrayDeque<>();
        }
        deque.push(dataPermissionRule);
        return dataPermissionRule;
    }
    
    /**
     * 弹出最顶部 DataPermissionRule
     */
    public static void poll() {
        Deque<DataPermissionRule> deque = DATA_PERMISSION_RULES.get();
        deque.poll();
        // 当没有元素时，清空 ThreadLocal
        if (deque.isEmpty()) {
            clear();
        }
    }
    
    /**
     * 清除 TreadLocal
     */
    public static void clear() {
        DATA_PERMISSION_RULES.remove();
    }
    
}
