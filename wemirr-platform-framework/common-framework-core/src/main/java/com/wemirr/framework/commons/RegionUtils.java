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

package com.wemirr.framework.commons;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;

import java.io.IOException;

/**
 * IP地址归属地查询工具类
 * <p>基于ip2region实现，启动时加载数据到内存，查询速度快</p>
 *
 * <h3>线程安全</h3>
 * <p>Searcher实例在初始化后不可变，多线程访问安全</p>
 *
 * @author Levin
 * @since 1.0.0
 */
@Slf4j
public final class RegionUtils {

    private static final String DEFAULT_REGION = "内网";

    /**
     * IP查询器（启动时加载到内存，不可变对象）
     * 使用volatile确保多线程可见性
     */
    private static volatile Searcher SEARCHER;

    /**
     * 私有构造函数，防止实例化
     */
    private RegionUtils() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
    
    static {
        try {
            long now = System.currentTimeMillis();
            byte[] bytes = ResourceUtil.readBytes("ip2region.xdb");
            SEARCHER = Searcher.newWithBuffer(bytes);
            log.info("启动加载 RegionUtils 成功，耗时 ({}) 毫秒", System.currentTimeMillis() - now);
        } catch (IOException e) {
            log.error("启动加载 RegionUtils 失败", e);
        }
    }
    
    /**
     * 根据IP地址查询归属地
     *
     * @param ip IP地址
     * @return 归属地信息，格式：国家|区域|省份|城市|ISP
     */
    public static String getRegion(String ip) {
        if (SEARCHER == null) {
            log.warn("IP查询器未初始化");
            return DEFAULT_REGION;
        }
        if (StrUtil.isBlank(ip)) {
            return DEFAULT_REGION;
        }
        try {
            long startTime = System.nanoTime();
            String result = SEARCHER.search(ip);
            if (log.isDebugEnabled()) {
                log.debug("IP查询耗时: {}μs, IP: {}, 结果: {}",
                        (System.nanoTime() - startTime) / 1000, ip, result);
            }
            return result;
        } catch (Exception e) {
            log.warn("IP查询失败: {} - {}", ip, e.getMessage());
            return DEFAULT_REGION;
        }
    }

    /**
     * 检查查询器是否可用
     *
     * @return true表示可用
     */
    public static boolean isAvailable() {
        return SEARCHER != null;
    }
}
