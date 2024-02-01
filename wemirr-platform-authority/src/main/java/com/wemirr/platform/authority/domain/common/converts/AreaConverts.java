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

import com.wemirr.framework.commons.entity.BaseConverts;
import com.wemirr.platform.authority.domain.common.entity.AreaEntity;
import com.wemirr.platform.authority.domain.common.req.AreaReq;
import com.wemirr.platform.authority.domain.common.resp.AreaNodeResp;
import org.springframework.beans.BeanUtils;

/**
 * @author Levin
 */
public class AreaConverts {
    
    public static final AreaConverts.AreaDto2PoConverts AREA_DTO_2_PO_CONVERTS = new AreaConverts.AreaDto2PoConverts();
    public static final AreaConverts.AreaEntity2NodeRespConverts AREA_ENTITY_2_NODE_RESP_CONVERTS = new AreaConverts.AreaEntity2NodeRespConverts();
    
    public static class AreaDto2PoConverts implements BaseConverts<AreaReq, AreaEntity> {
        
        @Override
        public AreaEntity convert(AreaReq source) {
            if (source == null) {
                return null;
            }
            AreaEntity target = new AreaEntity();
            BeanUtils.copyProperties(source, target);
            target.setId(source.getId());
            return target;
        }
    }
    
    public static class AreaEntity2NodeRespConverts implements BaseConverts<AreaEntity, AreaNodeResp> {
        
        @Override
        public AreaNodeResp convert(AreaEntity source) {
            if (source == null) {
                return null;
            }
            return AreaNodeResp.builder().value(source.getId()).label(source.getName()).parentId(source.getParentId())
                    .level(source.getLevel()).latitude(source.getLatitude()).longitude(source.getLongitude())
                    .isLeaf(source.getLevel() != null && source.getLevel() > 2).build();
        }
    }
    
}
