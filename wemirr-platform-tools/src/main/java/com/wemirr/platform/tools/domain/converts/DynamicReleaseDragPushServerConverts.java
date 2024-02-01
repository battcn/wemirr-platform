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
package com.wemirr.platform.tools.domain.converts;

import com.wemirr.framework.db.mybatisplus.page.BasePageConverts;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseDragPushServer;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseDragPushServerResp;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Levin
 */
public class DynamicReleaseDragPushServerConverts {
    
    public static final DynamicReleaseDragPushServerConverts.DynamicReleaseDragPushServerPo2Resp DYNAMIC_RELEASE_DRAG_PUSH_SERVER_PO_2_RESP =
            new DynamicReleaseDragPushServerConverts.DynamicReleaseDragPushServerPo2Resp();
    
    public static class DynamicReleaseDragPushServerPo2Resp implements BasePageConverts<DynamicReleaseDragPushServer, DynamicReleaseDragPushServerResp> {
        
        @Override
        public DynamicReleaseDragPushServerResp convert(DynamicReleaseDragPushServer source) {
            if (source == null) {
                return null;
            }
            DynamicReleaseDragPushServerResp resp = DynamicReleaseDragPushServerResp.builder()
                    .id(source.getId()).method(source.getMethod()).createdTime(source.getCreatedTime())
                    .secret(source.getSecret()).url(source.getUrl())
                    .locked(source.getLocked()).build();
            if (StringUtils.isNotBlank(source.getOpt())) {
                resp.setOpt(Arrays.stream(source.getOpt().split(",")).collect(Collectors.toList()));
            }
            return resp;
        }
    }
    
}
