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

package com.wemirr.platform.iam.auth.configuration;

import com.wemirr.platform.iam.system.domain.enums.ThirdAuthType;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.zhyd.oauth.config.AuthConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 三方授权配置
 * 其他平台对接方式可以参考 <a href="https://www.justauth.cn/guide/"/>
 *
 * @author Levin
 */
@Data
@Component
@NoArgsConstructor
@ConfigurationProperties(prefix = ThirdAuthProperties.PREFIX)
public class ThirdAuthProperties {
    
    public static final String PREFIX = "extend.third-auth";
    
    /**
     * 配置MAP
     */
    private Map<ThirdAuthType, AuthConfig> configMap;
    
}
