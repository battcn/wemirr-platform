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

package com.wemirr.platform.authority.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.annotation.remote.RemoteResult;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.log.diff.core.annotation.DiffLog;
import com.wemirr.framework.log.diff.core.context.DiffLogContext;
import com.wemirr.platform.authority.domain.baseinfo.entity.User;
import com.wemirr.platform.authority.domain.baseinfo.entity.UserRole;
import com.wemirr.platform.authority.domain.baseinfo.req.UserPageReq;
import com.wemirr.platform.authority.domain.baseinfo.req.UserSaveReq;
import com.wemirr.platform.authority.domain.baseinfo.req.UserUpdateReq;
import com.wemirr.platform.authority.domain.baseinfo.resp.UserResp;
import com.wemirr.platform.authority.domain.common.req.ChangeUserInfoReq;
import com.wemirr.platform.authority.repository.baseinfo.UserMapper;
import com.wemirr.platform.authority.repository.baseinfo.UserRoleMapper;
import com.wemirr.platform.authority.service.OrgService;
import com.wemirr.platform.authority.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.wemirr.platform.authority.domain.baseinfo.converts.UserConverts.USER_DTO_2_PO_CONVERTS;

/**
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends SuperServiceImpl<UserMapper, User> implements UserService {

    private final UserRoleMapper userRoleMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationContext context;
    private final OrgService orgService;

    @Override
    public void addUser(UserSaveReq req) {
        final long count = super.count(Wraps.<User>lbQ().eq(User::getUsername, req.getUsername()));
        if (count > 0) {
            throw CheckedException.badRequest("账号已存在");
        }
        final User user = BeanUtil.toBean(req, User.class);
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setTenantId(context.tenantId());
        super.save(user);
    }


    @Override
    @DiffLog(group = "用户管理", tag = "编辑用户", businessKey = "{{#id}}",
            success = "更新用户信息 {_DIFF{#_newObj}}",
            fail = "更新用户信息异常 {{#id}} 需要更新的数据 {{#req}}")
    public void modify(Long id, UserUpdateReq req) {
        User oldVal = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("用户不存在"));
        User newVal = USER_DTO_2_PO_CONVERTS.convert(req, id);
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
        return baseMapper.findPage(req.buildPage(), Wraps.<User>lbQ()
                .like(User::getUsername, req.getUsername())
                .like(User::getNickName, req.getNickName())
                .like(User::getEmail, req.getEmail())
                .eq(User::getStatus, req.getStatus())
                .eq(User::getEducation, req.getEducation())
                .eq(User::getDeleted, false)
                .eq(User::getSex, req.getSex())
                .in(User::getOrgId, orgService.getFullTreeIdPath(req.getOrgId()))
                .eq(User::getMobile, req.getMobile()));
    }

    @Override
    public void changePassword(Long userId, String orgPassword, String newPassword) {
        final User user = Optional.ofNullable(this.baseMapper.selectById(userId)).orElseThrow(() -> CheckedException.notFound("用户不存在"));
        if (!passwordEncoder.matches(orgPassword, user.getPassword())) {
            throw CheckedException.badRequest("原始密码错误");
        }
        this.baseMapper.updateById(User.builder().id(userId).password(passwordEncoder.encode(newPassword)).build());
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
        String encodePassword = passwordEncoder.encode(password);
        log.info("随机生成的新密码 - {} - {}", password, encodePassword);
        this.baseMapper.updateById(User.builder().id(id).password(encodePassword).build());
    }
}
