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

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.wemirr.framework.db.dynamic.annotation.TenantDS;
import com.wemirr.framework.db.mybatisplus.datascope.annotation.DataColumn;
import com.wemirr.framework.db.mybatisplus.datascope.annotation.DataScope;
import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.authority.domain.baseinfo.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Levin
 */
@TenantDS
@Repository
public interface RoleMapper extends SuperMapper<Role> {
    
    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 查询结果
     */
    @InterceptorIgnore(tenantLine = "true")
    List<Role> findRoleByUserId(Long userId);
    
    /**
     * 根据范围查询角色
     *
     * @return 查询结果
     */
    @DataScope(columns = @DataColumn)
    List<Role> list();
    
    /**
     * 根据租户ID删除
     *
     * @param tenantId 租户ID
     */
    @InterceptorIgnore(tenantLine = "true")
    @Delete("delete from sys_role where tenant_id = #{tenantId}")
    void deleteByTenantId(@Param("tenantId") Long tenantId);
}
