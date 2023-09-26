package com.wemirr.platform.authority.service;


import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.framework.security.domain.UserInfoDetails;
import com.wemirr.platform.authority.domain.common.entity.LoginLog;

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
     * @param details details
     * @return LoginLog
     */
    LoginLog addLog(UserInfoDetails details);

}
