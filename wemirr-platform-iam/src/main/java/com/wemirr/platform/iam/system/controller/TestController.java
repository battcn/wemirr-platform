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

package com.wemirr.platform.iam.system.controller;

import com.wemirr.framework.commons.concurrent.AsyncExecutor;
import com.wemirr.platform.iam.system.domain.entity.User;
import com.wemirr.platform.iam.system.repository.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试控制器
 * <p>
 * 用于测试数据权限过滤和异步执行功能
 * </p>
 *
 * @author Levin
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
@Tag(name = "测试接口", description = "数据权限和异步执行测试")
public class TestController {

    private final UserMapper userMapper;

    /**
     * API 驱动数据权限过滤
     * <p>
     * 直接通过 Mapper 查询，不使用 @DataScope 注解，
     * 由 MyBatis-Plus 拦截器自动根据用户权限过滤数据
     * </p>
     */
    @GetMapping("/data-scope/api")
    @Operation(summary = "API驱动数据权限过滤", description = "通过API方式触发数据权限过滤")
    public List<User> dataScopeByApi() {
        log.info("=== API驱动数据权限过滤测试 ===");
        // 直接查询，依赖全局数据权限拦截器
        return userMapper.selectList(null);
    }

    /**
     * 注解驱动数据权限过滤
     * <p>
     * 通过 @DataScope 注解指定数据权限过滤字段
     * </p>
     */
    @GetMapping("/data-scope/annotation")
    @Operation(summary = "注解驱动数据权限过滤", description = "通过@DataScope注解触发数据权限过滤")
    public List<User> dataScopeByAnnotation() {
        log.info("=== 注解驱动数据权限过滤测试 ===");
        // 调用带 @DataScope 注解的方法
        return userMapper.list();
    }

    /**
     * 单线程执行（休眠5秒*2）
     * <p>
     * 串行执行两个耗时任务，总耗时约 10 秒
     * </p>
     */
    @GetMapping("/sync")
    @Operation(summary = "单线程执行", description = "串行执行两个5秒任务，总耗时约10秒")
    public Map<String, Object> syncExecution() {
        log.info("=== 单线程执行测试开始 ===");
        long startTime = System.currentTimeMillis();

        // 任务1：休眠5秒
        String result1 = executeTask("任务1", 2000);

        // 任务2：休眠5秒
        String result2 = executeTask("任务2", 2000);

        long elapsed = System.currentTimeMillis() - startTime;
        log.info("=== 单线程执行测试完成，总耗时: {}ms ===", elapsed);

        Map<String, Object> response = new HashMap<>();
        response.put("result1", result1);
        response.put("result2", result2);
        response.put("elapsedMs", elapsed);
        response.put("mode", "sync");
        return response;
    }

    /**
     * 多线程执行（使用 AsyncExecutor）
     * <p>
     * 并行执行两个耗时任务，总耗时约 5 秒
     * </p>
     */
    @GetMapping("/async")
    @Operation(summary = "多线程执行", description = "并行执行两个5秒任务，总耗时约5秒")
    public Map<String, Object> asyncExecution() {
        log.info("=== 多线程执行测试开始 ===");
        long startTime = System.currentTimeMillis();

        // 使用 AsyncExecutor 并行执行两个任务
        List<String> results = AsyncExecutor.parallel(
                () -> executeTask("任务1", 2000),
                () -> executeTask("任务2", 2000)
        );

        long elapsed = System.currentTimeMillis() - startTime;
        log.info("=== 多线程执行测试完成，总耗时: {}ms ===", elapsed);

        Map<String, Object> response = new HashMap<>();
        response.put("result1", results.get(0));
        response.put("result2", results.get(1));
        response.put("elapsedMs", elapsed);
        response.put("mode", "async");
        return response;
    }

    /**
     * 执行耗时任务
     *
     * @param taskName 任务名称
     * @param sleepMs  休眠时间（毫秒）
     * @return 执行结果
     */
    private String executeTask(String taskName, long sleepMs) {
        log.info("[{}] 开始执行，线程: {}", taskName, Thread.currentThread().getName());
        try {
            Thread.sleep(sleepMs);
            userMapper.selectList(null);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("[{}] 执行被中断", taskName);
            return taskName + " - 被中断";
        }
        log.info("[{}] 执行完成", taskName);
        return taskName + " - 完成";
    }
}
