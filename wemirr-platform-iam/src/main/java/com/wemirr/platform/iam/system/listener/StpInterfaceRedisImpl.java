package com.wemirr.platform.iam.system.listener;

import cn.dev33.satoken.stp.StpInterface;
import com.wemirr.framework.commons.security.AuthenticationContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Levin
 */
@Slf4j
@Primary
@Component
@RequiredArgsConstructor
public class StpInterfaceRedisImpl implements StpInterface {

    private final AuthenticationContext context;

    /**
     * 返回一个账号所拥有的权限码集合
     *
     * @param loginId   账号id
     * @param loginType 账号类型
     * @return 返回一个账号所拥有的权限码集合
     */
    @Override

    public List<String> getPermissionList(Object loginId, String loginType) {
        return context.funcPermissionList();
    }

    /**
     * 返回一个账号所拥有的角色标识集合
     *
     * @param loginId   账号id
     * @param loginType 账号类型
     * @return 返回一个账号所拥有的角色标识集合
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return context.rolePermissionList();
    }
}
