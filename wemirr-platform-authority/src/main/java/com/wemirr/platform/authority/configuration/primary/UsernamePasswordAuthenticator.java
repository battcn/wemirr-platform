package com.wemirr.platform.authority.configuration.primary;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatis.conditions.Wraps;
import com.wemirr.framework.security.configuration.server.support.integration.IntegrationAuthentication;
import com.wemirr.framework.security.configuration.server.support.integration.IntegrationAuthenticator;
import com.wemirr.framework.security.domain.UserInfoDetails;
import com.wemirr.framework.security.exception.OAuth2InvalidException;
import com.wemirr.platform.authority.domain.entity.baseinfo.User;
import com.wemirr.platform.authority.domain.entity.tenant.Tenant;
import com.wemirr.platform.authority.repository.TenantMapper;
import com.wemirr.platform.authority.repository.UserMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

/**
 * 默认登录处理
 *
 * @author Levin
 * @since 2019-04-03
 **/
@Slf4j
@Primary
@Component
public class UsernamePasswordAuthenticator implements IntegrationAuthenticator {


    private static final String GRANT_TYPE = "grant_type";
    private static final String REFRESH_TOKEN = "refresh_token";

    @Resource
    private UserMapper userMapper;
    @Resource
    private TenantMapper tenantMapper;


    @Override
    public int getOrder() {
        return 100;
    }


    @Override
    public UserInfoDetails authenticate(IntegrationAuthentication integrationAuthentication) {
        String username = integrationAuthentication.getUsername();
        String tenantCode = integrationAuthentication.getTenantCode();
        if (StringUtils.isBlank(username)) {
            throw new UsernameNotFoundException("账号不存在");
        }
        if (StringUtils.isBlank(tenantCode)) {
            throw new OAuth2InvalidException("租户编码不能为空");
        }
        final String grantType = integrationAuthentication.getAuthParameter(GRANT_TYPE);
        if (StringUtils.isBlank(grantType) || !StringUtils.equalsIgnoreCase(grantType, REFRESH_TOKEN)) {
            // 如果说是每次登陆都要清空以前的信息那么需要调用一下注销，这个注销的功能就是注销以前的token信息
        }
        final Tenant tenant = Optional.ofNullable(tenantMapper.selectOne(Wraps.<Tenant>lbQ().eq(Tenant::getCode, tenantCode)))
                .orElseThrow(() -> CheckedException.notFound("{0}租户不存在", tenantCode));
        if (tenant.getLocked()) {
            throw CheckedException.badRequest("租户已被禁用,请联系管理员");
        }
        final User user = Optional.ofNullable(this.userMapper.selectUserByTenantId(username, tenant.getId())).orElseThrow(() -> CheckedException.notFound("账户不存在"));
        final UserInfoDetails info = new UserInfoDetails();
        info.setTenantCode(tenantCode);
        info.setTenantId(user.getTenantId());
        info.setUserId(user.getId());
        info.setUsername(username);
        info.setRealName(user.getNickName());
        info.setNickName(user.getNickName());
        info.setMobile(user.getMobile());
        info.setEmail(user.getEmail());
        info.setDescription(user.getDescription());
        info.setSex(Objects.isNull(user.getSex()) ? null : user.getSex().getValue());
        info.setEnabled(user.getStatus());
        info.setAvatar(user.getAvatar());
        info.setPassword(user.getPassword());
        return info;
    }


    @Override
    public void prepare(IntegrationAuthentication integrationAuthentication) {
        log.info("[用户密码登陆] - [{}]", JSON.toJSONString(integrationAuthentication));
    }

    @Override
    public boolean support(IntegrationAuthentication integrationAuthentication) {
        return StrUtil.equals(integrationAuthentication.getLoginType(), loginType()) || loginType() == null;
    }
}
