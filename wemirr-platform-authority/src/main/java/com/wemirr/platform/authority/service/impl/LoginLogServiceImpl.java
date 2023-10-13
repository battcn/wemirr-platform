package com.wemirr.platform.authority.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.http.useragent.*;
import com.wemirr.framework.boot.RegionUtils;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.security.domain.UserInfoDetails;
import com.wemirr.platform.authority.domain.common.entity.LoginLog;
import com.wemirr.platform.authority.repository.common.LoginLogMapper;
import com.wemirr.platform.authority.service.LoginLogService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 系统日志
 * </p>
 *
 * @author Levin
 * @since 2019-10-20
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginLogServiceImpl extends SuperServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

    private final HttpServletRequest request;

    private static final String USER_AGENT = "User-Agent";


    @Override
    public LoginLog addLog(UserInfoDetails details) {
        final String clientId = request.getParameter("client_id");
        final String username = request.getParameter("username");
        String ip = JakartaServletUtil.getClientIP(request);
        String region = RegionUtils.getRegion(ip);
        String ua = request.getHeader(USER_AGENT);
        final UserAgent userAgent = UserAgentUtil.parse(ua);
        final Browser browser = userAgent.getBrowser();
        LoginLog loginLog = LoginLog.builder().userId(details.getUserId())
                .tenantId(details.getTenantId())
                .principal(username).location(region).ip(ip)
                .platform(userAgent.getPlatform().getName())
                .engine(userAgent.getEngine().getName())
                .browser(browser.getName())
                .os(userAgent.getOs().getName())
                .clientId(clientId).name(details.getNickName())
                .build();
        super.save(loginLog);
        return loginLog;
    }
}
