package com.wemirr.platform.authority.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.TenantEnvironment;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.intercept.data.DataPermission;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.db.mybatisplus.wrap.query.LbqWrapper;
import com.wemirr.platform.authority.domain.baseinfo.entity.User;
import com.wemirr.platform.authority.domain.baseinfo.entity.UserRole;
import com.wemirr.platform.authority.domain.baseinfo.req.UserSaveReq;
import com.wemirr.platform.authority.domain.baseinfo.resp.UserResp;
import com.wemirr.platform.authority.domain.common.req.ChangeUserInfoReq;
import com.wemirr.platform.authority.repository.baseinfo.UserMapper;
import com.wemirr.platform.authority.repository.baseinfo.UserRoleMapper;
import com.wemirr.platform.authority.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final UserMapper userMapper;
    private final UserRoleMapper userRoleMapper;
    private final PasswordEncoder passwordEncoder;
    private final TenantEnvironment tenantEnvironment;


    @Override
    public void addUser(UserSaveReq dto) {
        final long count = super.count(Wraps.<User>lbQ().eq(User::getUsername, dto.getUsername()));
        if (count > 0) {
            throw CheckedException.badRequest("账号已存在");
        }
        final User user = BeanUtil.toBean(dto, User.class);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setTenantId(tenantEnvironment.tenantId());
        super.save(user);
    }

    @Override
    public List<User> list(DataPermission scope) {
        return baseMapper.list(scope);
    }

    @Override
    public IPage<UserResp> findPage(IPage<User> page, LbqWrapper<User> wrapper) {
        return baseMapper.findPage(page, wrapper);
    }

    @Override
    public void changePassword(Long userId, String orgPassword, String newPassword) {
        final User user = Optional.ofNullable(this.baseMapper.selectById(userId)).orElseThrow(() -> CheckedException.notFound("用户不存在"));
        if (!passwordEncoder.matches(orgPassword, user.getPassword())) {
            throw CheckedException.badRequest("原始密码错误");
        }
        User record = new User();
        record.setId(userId);
        record.setPassword(passwordEncoder.encode(newPassword));
        this.userMapper.updateById(record);
    }

    @Override
    @DSTransactional
    public void deleteById(Long id) {
        final User user = Optional.ofNullable(getById(id)).orElseThrow(() -> CheckedException.notFound("用户不存在"));
        if (user.getReadonly()) {
            throw CheckedException.badRequest("内置用户不允许删除");
        }
        baseMapper.deleteById(id);
        userRoleMapper.delete(Wraps.<UserRole>lbQ().eq(UserRole::getUserId, id));
    }

    @Override
    public void changeInfo(ChangeUserInfoReq req) {
        final Long userId = tenantEnvironment.userId();
        User record = User.builder().id(userId).email(req.getEmail()).mobile(req.getMobile())
                .nickName(req.getNickName()).birthday(req.getBirthday()).description(req.getDescription()).build();
        this.userMapper.updateById(record);
    }
}
