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

package com.wemirr.platform.authority.repository.baseinfo;

import com.wemirr.framework.db.dynamic.annotation.TenantDS;
import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.authority.domain.baseinfo.entity.RoleRes;
import com.wemirr.platform.authority.domain.baseinfo.resp.RoleResMenuMapperResp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 角色的资源
 * </p>
 *
 * @author Levin
 * @since 2019-07-03
 */
@TenantDS
@Repository
public interface RoleResMapper extends SuperMapper<RoleRes> {
    
    /**
     * 根据角色ID查询角色资源菜单信息
     *
     * @param roleId 角色ID
     * @return 查询结果
     */
    List<RoleResMenuMapperResp> selectRoleResByRoleId(@Param("roleId") Long roleId);
    
}
