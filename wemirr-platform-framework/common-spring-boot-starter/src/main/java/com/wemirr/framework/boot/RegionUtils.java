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
package com.wemirr.framework.boot;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;

import java.io.IOException;

/**
 * 根据ip查询地址
 *
 * @author Levin
 * @since 2019/10/30
 */
@Slf4j
public final class RegionUtils {
    
    /**
     * IP 查询器，启动加载到内存中
     */
    private static Searcher SEARCHER;
    
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
     * 解析IP
     *
     * @param ip ip
     * @return 查询结果
     */
    public static String getRegion(String ip) {
        try {
            if (SEARCHER == null || StrUtil.isEmpty(ip)) {
                log.error("searcher or ip is null");
                return StrUtil.EMPTY;
            }
            long startTime = System.currentTimeMillis();
            String result = SEARCHER.search(ip);
            long endTime = System.currentTimeMillis();
            log.debug("region use time[{}] result[{}]", endTime - startTime, result);
            return result;
            
        } catch (Exception e) {
            log.error("error - {}", e.getLocalizedMessage());
        }
        return StrUtil.EMPTY;
    }
    
}
