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

package com.wemirr.platform.authority.domain.common.converts;

import com.wemirr.framework.db.mybatisplus.page.BasePageConverts;
import com.wemirr.platform.authority.domain.common.entity.SiteNotify;
import com.wemirr.platform.authority.domain.common.resp.SiteMessageResp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Levin
 */
public class SiteNotifyConverts {
    
    public static final SiteNotify2VoConverts SITE_NOTIFY_2_VO_CONVERTS = new SiteNotify2VoConverts();
    
    public static class SiteNotify2VoConverts implements BasePageConverts<SiteNotify, SiteMessageResp> {
        
        @Override
        public SiteMessageResp convert(SiteNotify source) {
            if (source == null) {
                return null;
            }
            SiteMessageResp target = new SiteMessageResp();
            BeanUtils.copyProperties(source, target);
            target.setId(source.getId());
            if (StringUtils.isNotBlank(source.getReceiver())) {
                final List<Long> receiver = Arrays.stream(source.getReceiver()
                        .split(",")).map(Long::parseLong).collect(Collectors.toList());
                target.setReceiver(receiver);
            }
            return target;
        }
    }
    
}
