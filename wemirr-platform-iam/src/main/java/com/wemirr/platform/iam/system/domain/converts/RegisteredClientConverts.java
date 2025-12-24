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

package com.wemirr.platform.iam.system.domain.converts;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.wemirr.framework.commons.JacksonUtils;
import com.wemirr.framework.db.mybatisplus.page.BasePageConverts;
import com.wemirr.platform.iam.auth.domain.entity.RegisteredClient;
import com.wemirr.platform.iam.system.domain.dto.resp.RegisteredClientResp;
import lombok.SneakyThrows;

/**
 * @author Levin
 */
public class RegisteredClientConverts {

    public static final RegisteredClientConverts.RegisteredClientRef2RespConverts REGISTERED_CLIENT_REF_2_RESP_CONVERTS = new RegisteredClientConverts.RegisteredClientRef2RespConverts();

    public static class RegisteredClientRef2RespConverts implements BasePageConverts<RegisteredClient, RegisteredClientResp> {

        @SneakyThrows
        @Override
        public RegisteredClientResp convert(RegisteredClient source) {
            if (source == null) {
                return null;
            }
            RegisteredClientResp target = new RegisteredClientResp();
            target.setId(source.getId());
            target.setClientName(source.getClientName());
            target.setClientId(source.getClientId());
            target.setClientSecret(source.getClientSecret());
            target.setClientIdIssuedAt(source.getClientIdIssuedAt());
            target.setClientName(source.getClientName());
            target.setClientSecretExpiresAt(source.getClientSecretExpiresAt());
            target.setGrantTypes(StrUtil.split(source.getGrantTypes(), ','));
            target.setRedirectUris(source.getRedirectUris());
            target.setPostLogoutRedirectUris(source.getPostLogoutRedirectUris());
            target.setScopes(StrUtil.split(source.getScopes(), ','));
            target.setStatus(source.getStatus());
            final String tokenSettings = source.getTokenSettings();
            if (StrUtil.isNotBlank(tokenSettings)) {
                JsonNode settings = JacksonUtils.toObj(tokenSettings);
                target.setAccessTokenTimeToLive(settings.get("accessTokenTimeToLive").asLong());
                target.setRefreshTokenTimeToLive(settings.get("refreshTokenTimeToLive").asLong());
            }
            return target;
        }
    }

}
