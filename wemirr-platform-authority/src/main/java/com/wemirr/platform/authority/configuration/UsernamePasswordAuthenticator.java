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

package com.wemirr.platform.authority.configuration;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson2.JSON;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.security.configuration.server.support.integration.IntegrationAuthentication;
import com.wemirr.framework.security.configuration.server.support.integration.IntegrationAuthenticator;
import com.wemirr.framework.security.domain.UserInfoDetails;
import com.wemirr.framework.security.exception.OAuth2InvalidException;
import com.wemirr.platform.authority.domain.baseinfo.entity.Role;
import com.wemirr.platform.authority.domain.baseinfo.entity.User;
import com.wemirr.platform.authority.domain.tenant.entity.Tenant;
import com.wemirr.platform.authority.repository.baseinfo.ResourceMapper;
import com.wemirr.platform.authority.repository.baseinfo.RoleMapper;
import com.wemirr.platform.authority.repository.baseinfo.UserMapper;
import com.wemirr.platform.authority.repository.tenant.TenantMapper;
import com.wemirr.platform.authority.service.LoginLogService;
import com.wemirr.platform.authority.service.impl.DataScopeServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 默认登录处理.
 *
 * @author Levin
 **/
@Slf4j
@Primary
@Component
public class UsernamePasswordAuthenticator implements IntegrationAuthenticator {
    
    @Resource
    private UserMapper userMapper;
    
    @Resource
    private TenantMapper tenantMapper;
    
    @Resource
    private RoleMapper roleMapper;
    
    @Resource
    private ResourceMapper resourceMapper;
    
    @Resource
    private LoginLogService loginLogService;
    
    @Resource
    private DataScopeServiceImpl dataScopeServiceImpl;
    
    @Override
    public int getOrder() {
        return 100;
    }
    
    @Override
    public void prepare(final IntegrationAuthentication authentication) {
        log.info("[用户密码登陆] - [{}]", JSON.toJSONString(authentication));
        String username = authentication.getUsername();
        String tenantCode = authentication.getTenantCode();
        if (StringUtils.isBlank(username)) {
            throw new OAuth2InvalidException("用户名不能为空");
        }
        if (StringUtils.isBlank(tenantCode)) {
            throw new OAuth2InvalidException("租户编码不能为空");
        }
    }
    
    @Override
    public UserInfoDetails authenticate(final IntegrationAuthentication authentication) {
        String username = authentication.getUsername();
        String password = authentication.getPassword();
        String tenantCode = authentication.getTenantCode();
        final Tenant tenant = Optional.ofNullable(tenantMapper.selectOne(Tenant::getCode, tenantCode))
                .orElseThrow(() -> CheckedException.notFound("{0}租户不存在", tenantCode));
        if (tenant.getLocked()) {
            throw CheckedException.badRequest("租户已被禁用,请联系管理员");
        }
        final User user = Optional.ofNullable(this.userMapper.selectUserByTenantId(username, tenant.getId())).orElseThrow(() -> CheckedException.notFound("账户不存在"));
        final PasswordEncoder passwordEncoder = SpringUtil.getBean(PasswordEncoder.class);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw CheckedException.badRequest("用户名或密码错误");
        }
        final UserInfoDetails info = new UserInfoDetails();
        info.setTenantCode(tenantCode);
        info.setTenantName(tenant.getName());
        info.setTenantId(user.getTenantId());
        info.setUserId(user.getId());
        info.setUsername(username);
        info.setRealName(user.getNickName());
        info.setNickName(user.getNickName());
        info.setMobile(user.getMobile());
        info.setEmail(user.getEmail());
        info.setDescription(user.getDescription());
        info.setBirthday(user.getBirthday());
        info.setSex(Objects.isNull(user.getSex()) ? null : user.getSex().getValue());
        info.setEnabled(user.getStatus());
        info.setAvatar(user.getAvatar());
        info.setPassword(user.getPassword());
        final List<Role> roles = this.roleMapper.findRoleByUserId(user.getId());
        info.setRoles(roles.stream().map(Role::getCode).toList());
        final List<String> permissions = this.resourceMapper.selectPermissionByUserId(user.getId());
        info.setFuncPermissions(permissions);
        // 为了减少一次数据库查询,所以用了这个不规范写法
        info.setDataPermission(dataScopeServiceImpl.getDataPermissionById(user.getId(), user.getOrgId()));
        this.loginLogService.addLog(info);
        return info;
    }
    
    @Override
    public boolean support(final IntegrationAuthentication integrationAuthentication) {
        return StrUtil.equals(integrationAuthentication.getLoginType(), loginType()) || loginType() == null;
    }
}
