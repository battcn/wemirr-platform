package com.wemirr.platform.iam.system.listener;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.context.model.SaStorage;
import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.http.useragent.Browser;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.wemirr.framework.commons.RegionUtils;
import com.wemirr.framework.security.configuration.SecurityExtProperties;
import com.wemirr.framework.security.domain.UserInfoDetails;
import com.wemirr.platform.iam.base.domain.entity.LoginLog;
import com.wemirr.platform.iam.base.repository.LoginLogMapper;
import com.wemirr.platform.iam.system.domain.entity.User;
import com.wemirr.platform.iam.system.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * 登录监听器
 *
 * @author Levin
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class WpTokenListener implements SaTokenListener {
    private static final String USER_AGENT = "User-Agent";
    private final SecurityExtProperties extProperties;
    private final SaTokenDao saTokenDao;
    private final LoginLogMapper loginLogMapper;
    private final UserService userService;
    private final HttpServletRequest request;

    /**
     * @param loginType  账号类别
     * @param loginId    账号id
     * @param tokenValue 本次登录产生的 token 值
     * @param loginModel 登录参数
     */
    @Override
    public void doLogin(String loginType, Object loginId, String tokenValue, SaLoginModel loginModel) {
        SaStorage storage = SaHolder.getContext().getStorage();
        String ip = JakartaServletUtil.getClientIP(request);
        String region = RegionUtils.getRegion(ip);
        String ua = request.getHeader(USER_AGENT);
        final UserAgent userAgent = UserAgentUtil.parse(ua);
        final Browser browser = userAgent.getBrowser();
        final Long userId = Long.parseLong(loginId.toString());
        Long tenantId = storage.getLong("tenantId");
        String tenantCode = storage.getString("tenantCode");
        String clientId = storage.getString("clientId");
        String username = storage.getString("username");
        String nickName = storage.getString("nickName");
        LoginLog loginLog = LoginLog.builder().principal(username).clientId(clientId)
                .tenantId(tenantId).tenantCode(tenantCode)
                .location(region).ip(ip)
                .platform(userAgent.getPlatform().getName())
                .engine(userAgent.getEngine().getName())
                .browser(browser.getName())
                .os(userAgent.getOs().getName())
                .loginType(loginType)
                .createdBy(userId).createdName(nickName)
                .build();
        // 记录登录日志
        this.loginLogMapper.insert(loginLog);
        // 刷新登录时间和IP
        this.userService.updateById(User.builder().id(userId).lastLoginIp(ip).lastLoginTime(Instant.now()).build());
        UserInfoDetails info = this.userService.userinfo(userId);
        this.saTokenDao.setObject(buildCacheKey(tokenValue), info, loginModel.getTimeout());
    }

    private String buildCacheKey(String tokenValue) {
        return String.format("USER_INFO_KEY:%s", tokenValue);
    }

    /**
     * @param tokenValue token 值
     * @param loginId    账号id
     * @param timeout    续期时间
     */
    @Override
    public void doRenewTimeout(String tokenValue, Object loginId, long timeout) {
        this.saTokenDao.updateTimeout(buildCacheKey(tokenValue), timeout);
    }

    /**
     * @param loginType  账号类别
     * @param loginId    账号id
     * @param tokenValue token值
     */
    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {
        this.saTokenDao.delete(buildCacheKey(tokenValue));
    }

    /**
     * 每次被踢下线时触发
     *
     * @param loginType  账号类别
     * @param loginId    账号id
     * @param tokenValue token值
     */
    @Override
    public void doKickout(String loginType, Object loginId, String tokenValue) {

    }

    @Override
    public void doReplaced(String loginType, Object loginId, String tokenValue) {

    }

    @Override
    public void doDisable(String loginType, Object loginId, String service, int level, long disableTime) {

    }

    @Override
    public void doUntieDisable(String loginType, Object loginId, String service) {

    }

    @Override
    public void doOpenSafe(String loginType, String tokenValue, String service, long safeTime) {

    }

    @Override
    public void doCloseSafe(String loginType, String tokenValue, String service) {

    }

    @Override
    public void doCreateSession(String id) {

    }

    @Override
    public void doLogoutSession(String id) {

    }
}