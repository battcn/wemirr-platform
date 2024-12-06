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

import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.plugins.IgnoreStrategy;
import com.baomidou.mybatisplus.core.plugins.InterceptorIgnoreHelper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.annotation.remote.RemoteResult;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.datascope.handler.DataPermissionRule;
import com.wemirr.framework.db.mybatisplus.datascope.service.DataScopeService;
import com.wemirr.framework.db.mybatisplus.datascope.util.DataPermissionUtils;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.log.diff.core.annotation.DiffLog;
import com.wemirr.framework.log.diff.core.context.DiffLogContext;
import com.wemirr.framework.security.domain.UserInfoDetails;
import com.wemirr.framework.security.utils.PasswordEncoderHelper;
import com.wemirr.platform.iam.base.domain.dto.req.ChangeUserInfoReq;
import com.wemirr.platform.iam.base.domain.entity.LoginLog;
import com.wemirr.platform.iam.system.domain.dto.req.UserOnlinePageReq;
import com.wemirr.platform.iam.system.domain.dto.req.UserPageReq;
import com.wemirr.platform.iam.system.domain.dto.req.UserSaveReq;
import com.wemirr.platform.iam.system.domain.dto.req.UserUpdateReq;
import com.wemirr.platform.iam.system.domain.dto.resp.UserResp;
import com.wemirr.platform.iam.system.domain.entity.Role;
import com.wemirr.platform.iam.system.domain.entity.User;
import com.wemirr.platform.iam.system.domain.entity.UserRole;
import com.wemirr.platform.iam.system.repository.RoleMapper;
import com.wemirr.platform.iam.system.repository.UserMapper;
import com.wemirr.platform.iam.system.repository.UserRoleMapper;
import com.wemirr.platform.iam.system.service.OrgService;
import com.wemirr.platform.iam.system.service.ResourceService;
import com.wemirr.platform.iam.system.service.UserService;
import com.wemirr.platform.iam.tenant.domain.entity.Tenant;
import com.wemirr.platform.iam.tenant.repository.TenantMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends SuperServiceImpl<UserMapper, User> implements UserService {

    private final UserRoleMapper userRoleMapper;
    private final AuthenticationContext context;
    private final OrgService orgService;
    private final ResourceService resourceService;
    private final RoleMapper roleMapper;
    private final TenantMapper tenantMapper;
    private final DataScopeService dataScopeService;

    @Override
    public void addUser(UserSaveReq req) {
        final long count = super.count(Wraps.<User>lbQ().eq(User::getUsername, req.getUsername()));
        if (count > 0) {
            throw CheckedException.badRequest("账号已存在");
        }
        final User user = BeanUtil.toBean(req, User.class);
        user.setPassword(PasswordEncoderHelper.encode(req.getPassword()));
        user.setTenantId(context.tenantId());
        super.save(user);
    }


    @Override
    @DiffLog(group = "用户管理", tag = "编辑用户", businessKey = "{{#id}}",
            success = "更新用户信息 {_DIFF{#_newObj}}",
            fail = "更新用户信息异常 {{#id}} 需要更新的数据 {{#req}}")
    public void modify(Long id, UserUpdateReq req) {
        User oldVal = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("用户不存在"));
        User newVal = BeanUtilPlus.toBean(id, req, User.class);
        DiffLogContext.putDiffItem(oldVal, newVal);
        this.baseMapper.updateById(newVal);
    }

    @Override
    public List<User> list() {
        return baseMapper.list();
    }

    @Override
    @RemoteResult
    public IPage<UserResp> pageList(UserPageReq req) {
        return DataPermissionUtils.executeWithDataPermissionRule(DataPermissionRule.builder()
                .columns(List.of(new DataPermissionRule.Column()))
                .build(), () -> baseMapper.selectPage(req.buildPage(), Wraps.<User>lbQ()
                .like(User::getUsername, req.getUsername())
                .like(User::getNickName, req.getNickName())
                .like(User::getEmail, req.getEmail())
                .eq(User::getStatus, req.getStatus())
                .eq(User::getEducation, req.getEducation())
                .eq(User::getDeleted, false)
                .eq(User::getSex, req.getSex())
                .in(User::getOrgId, orgService.getFullTreeIdPath(req.getOrgId()))
                .eq(User::getMobile, req.getMobile())).convert(x -> BeanUtil.toBean(x, UserResp.class)));
    }

    @Override
    public void changePassword(Long userId, String orgPassword, String newPassword) {
        final User user = Optional.ofNullable(this.baseMapper.selectById(userId)).orElseThrow(() -> CheckedException.notFound("用户不存在"));
//        if (!passwordEncoder.matches(orgPassword, user.getPassword())) {
//            throw CheckedException.badRequest("原始密码错误");
//        }
//        this.baseMapper.updateById(User.builder().id(userId).password(passwordEncoder.encode(newPassword)).build());
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        final User user = Optional.ofNullable(getById(id)).orElseThrow(() -> CheckedException.notFound("用户不存在"));
        if (user.getReadonly()) {
            throw CheckedException.badRequest("内置用户不允许删除");
        }
        baseMapper.deleteById(id);
        userRoleMapper.delete(Wraps.<UserRole>lbQ().eq(UserRole::getUserId, id));
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void changeInfo(ChangeUserInfoReq req) {
        final Long userId = context.userId();
        User bean = User.builder().id(userId).email(req.getEmail()).mobile(req.getMobile())
                .nickName(req.getNickName()).birthday(req.getBirthday())
                .description(req.getDescription()).build();
        this.baseMapper.updateById(bean);
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void resetPassword(Long id) {
        final User user = Optional.ofNullable(getById(id)).orElseThrow(() -> CheckedException.notFound("用户不存在"));
        if (user.getReadonly()) {
            throw CheckedException.badRequest("禁止重置内置用户密码");
        }
        String prefix = RandomUtil.randomString(4);
        String suffix = RandomUtil.randomNumbers(6);
        String password = prefix + suffix;
        String encodePassword = SaSecureUtil.md5(password);
        log.info("随机生成的新密码 - {} - {}", password, encodePassword);
        this.baseMapper.updateById(User.builder().id(id).password(encodePassword).build());
    }

    @Override
    public UserInfoDetails userinfo(Long userId) {
        // TODO 后续通过注解和 API 方式动态控制
        InterceptorIgnoreHelper.handle(IgnoreStrategy.builder().tenantLine(true).build());
        final User user = Optional.ofNullable(this.baseMapper.selectById(userId))
                .orElseThrow(() -> CheckedException.notFound("用户信息不存在"));
        Tenant tenant = this.tenantMapper.selectById(user.getTenantId());
        final UserInfoDetails info = new UserInfoDetails();
        info.setTenantCode(tenant.getCode());
        info.setTenantName(tenant.getName());
        info.setTenantId(user.getTenantId());
        info.setUserId(user.getId());
        info.setUsername(user.getUsername());
        info.setNickName(user.getNickName());
        info.setMobile(user.getMobile());
        info.setEmail(user.getEmail());
        info.setDescription(user.getDescription());
        info.setBirthday(user.getBirthday());
        info.setEnabled(user.getStatus());
        info.setAvatar(user.getAvatar());
        info.setPassword(user.getPassword());
        final List<Role> roles = this.roleMapper.findRoleByUserId(user.getId());
        info.setRoles(roles.stream().map(Role::getCode).toList());
        final List<String> permissions = this.resourceService.selectPermissionByUserId(user.getId());
        info.setFuncPermissions(permissions);
        // 为了减少一次数据库查询,所以用了这个不规范写法
        info.setDataPermission(dataScopeService.getDataScopeById(user.getId()));
        return info;
    }


    private final SaTokenDao saTokenDao;

    @Override
    public IPage<Object> userOnlineList(UserOnlinePageReq req) {
        List<Object> list = Lists.newArrayList();
        // 查询所有在线 Token
        List<String> tokenKeyList = StpUtil.searchTokenValue(StrUtil.EMPTY, 0, -1, false);
        for (String tokenKey : tokenKeyList) {
            String token = StrUtil.subAfter(tokenKey, StrUtil.COLON, true);
            // 如果已经过期则跳过
            if (StpUtil.stpLogic.getTokenActiveTimeoutByToken(token) < -1) {
                continue;
            }
            // TODO 需要优化,不应该暴露 token key 给开发
            UserInfoDetails info = JSONObject.parseObject((String) saTokenDao.getObject("wp-token:userinfo:" + token), UserInfoDetails.class);
            if (info == null || info.getLoginLog() == null) {
                continue;
            }
            LoginLog loginLog = JSONObject.from(info.getLoginLog()).toJavaObject(LoginLog.class);
            if (StrUtil.isNotBlank(req.getClientId()) && !StrUtil.equals(req.getClientId(), loginLog.getClientId())) {
                continue;
            }
            if (StrUtil.isNotBlank(req.getPrincipal()) && !StrUtil.equals(req.getPrincipal(), loginLog.getPrincipal())) {
                continue;
            }
            if (StrUtil.isNotBlank(req.getPlatform()) && !StrUtil.equals(req.getPlatform(), loginLog.getPlatform())) {
                continue;
            }
            if (ObjUtil.isNotNull(req.getTenantId()) && !ObjUtil.equals(req.getClientId(), loginLog.getClientId())) {
                continue;
            }
            JSONObject item = JSONObject.from(info.getLoginLog());
            item.put("token", token);
            list.add(item);
        }
        IPage<Object> page = new Page<>(req.getCurrent(), req.getSize(), list.size());
        List<Object> records = CollUtil.page((int) (ObjUtil.defaultIfNull(req.getCurrent(), 1L) - 1), (int) req.getSize(), list);
        page.setRecords(records);
        return page;
    }
}
