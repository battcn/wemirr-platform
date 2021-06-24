package com.wemirr.platform.authority.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.boot.service.impl.SuperServiceImpl;
import com.wemirr.framework.database.mybatis.auth.DataScope;
import com.wemirr.framework.database.mybatis.conditions.query.LbqWrapper;
import com.wemirr.platform.authority.domain.entity.User;
import com.wemirr.platform.authority.domain.vo.UserResp;
import com.wemirr.platform.authority.mapper.UserMapper;
import com.wemirr.platform.authority.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<User> list(DataScope scope) {
        return baseMapper.list(scope);
    }

    @Override
    public IPage<UserResp> findPage(IPage<User> page, LbqWrapper<User> wrapper) {
        return baseMapper.findPage(page, wrapper);
    }
}
