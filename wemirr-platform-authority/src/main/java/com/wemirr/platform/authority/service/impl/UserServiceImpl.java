package com.wemirr.platform.authority.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.boot.service.impl.SuperServiceImpl;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.database.mybatis.auth.DataScope;
import com.wemirr.framework.database.mybatis.conditions.Wraps;
import com.wemirr.framework.database.mybatis.conditions.query.LbqWrapper;
import com.wemirr.platform.authority.domain.dto.UserSaveDTO;
import com.wemirr.platform.authority.domain.entity.baseinfo.User;
import com.wemirr.platform.authority.domain.vo.UserResp;
import com.wemirr.platform.authority.repository.UserMapper;
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

    private static final String PHONE_REGEX = "^[1][0-9]{10}$";
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void addUser(UserSaveDTO dto) {
        final int count = super.count(Wraps.<User>lbQ().eq(User::getUsername, dto.getUsername()));
        if (count > 0) {
            throw CheckedException.badRequest("账号已存在");
        }
        final User user = BeanUtil.toBean(dto, User.class);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        super.save(user);
    }

    @Override
    public List<User> list(DataScope scope) {
        return baseMapper.list(scope);
    }

    @Override
    public IPage<UserResp> findPage(IPage<User> page, LbqWrapper<User> wrapper) {
        return baseMapper.findPage(page, wrapper);
    }

    @Override
    public void changePassword(Long userId, String orgPassword, String newPassword) {
        final User user = Optional.ofNullable(this.baseMapper.selectById(userId))
                .orElseThrow(() -> CheckedException.notFound("用户不存在"));
        if (!passwordEncoder.matches(orgPassword, user.getPassword())) {
            throw CheckedException.badRequest("原始密码错误");
        }
        User record = new User();
        record.setId(userId);
        record.setPassword(passwordEncoder.encode(newPassword));
        this.userMapper.updateById(record);
    }
}
