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

package com.wemirr.framework.storage;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Levin
 */
public class FileUtils {

    private static final String SEPARATOR = "/";

    /**
     * 根据旧的名称生成新的名称
     *
     * @param originName originName
     * @return 生成结果
     */
    public static String randomName(String originName) {
        final String uuid = UUID.randomUUID().toString();
        if (StringUtils.isBlank(originName)) {
            return uuid;
        }
        final String extension = FileUtil.extName(originName);
        return uuid + "." + extension;
    }

    public static String targetName(boolean random, String prefix, String originName) {
        return buildTargetName(random, prefix, originName).replaceAll("//", "/");
    }

    private static String buildTargetName(boolean random, String prefix, String originName) {
        if (!random) {
            return StringUtils.join(SEPARATOR, originName);
        }
        final String name = randomName(originName);
        if (StringUtils.isNotBlank(name)) {
            return StringUtils.isBlank(prefix) ? name : StringUtils.join(prefix, SEPARATOR, name);
        }
        return StringUtils.isBlank(prefix) ? name : StringUtils.join(prefix, name);
    }

}
