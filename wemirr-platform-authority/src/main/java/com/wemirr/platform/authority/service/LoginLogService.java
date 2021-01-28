package com.wemirr.platform.authority.service;


import com.wemirr.framework.boot.service.SuperService;
import com.wemirr.platform.authority.domain.entity.log.LoginLog;

/**
 * <p>
 * 业务接口
 * 系统日志
 *
 * </p>
 *
 * @author Levin
 * @since 2019-10-20
 */
public interface LoginLogService extends SuperService<LoginLog> {

    /**
     * 记录登录日志
     *
     * @param userId    用户ID
     * @param principal 账号
     * @param realName  名字
     * @return LoginLog
     */
    LoginLog saveLoginLog(Long userId, String principal, String realName);

}
