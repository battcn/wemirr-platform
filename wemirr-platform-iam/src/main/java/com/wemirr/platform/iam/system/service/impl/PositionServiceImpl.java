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

package com.wemirr.platform.iam.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.db.mybatisplus.wrap.query.LbqWrapper;
import com.wemirr.platform.iam.system.domain.dto.req.PositionPageReq;
import com.wemirr.platform.iam.system.domain.dto.resp.PositionPageResp;
import com.wemirr.platform.iam.system.domain.entity.Position;
import com.wemirr.platform.iam.system.repository.SysPositionMapper;
import com.wemirr.platform.iam.system.service.OrgService;
import com.wemirr.platform.iam.system.service.SysPositionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 岗位
 * </p>
 *
 * @author Levin
 * @since 2019-07-22
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PositionServiceImpl extends SuperServiceImpl<SysPositionMapper, Position> implements SysPositionService {

    private final OrgService orgService;

    @Override
    public IPage<PositionPageResp> pageList(PositionPageReq req) {
        final LbqWrapper<Position> wrapper = Wraps.<Position>lbQ()
                .like(Position::getTitle, req.getTitle())
                .eq(Position::getStatus, req.getStatus())
                .in(Position::getOrgId, orgService.getFullTreeIdPath(req.getOrgId()))
                .eq(Position::getDeleted, false)
                .orderByAsc(Position::getSequence);
        return baseMapper.selectPage(req.buildPage(), wrapper)
                .convert(x -> BeanUtil.toBean(x, PositionPageResp.class));
    }

}
