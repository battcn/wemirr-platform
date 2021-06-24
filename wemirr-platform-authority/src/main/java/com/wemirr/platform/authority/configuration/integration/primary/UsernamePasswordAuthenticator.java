package com.wemirr.platform.authority.configuration.integration.primary;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.security.client.entity.UserInfoDetails;
import com.wemirr.framework.security.client.exception.Auth2Exception;
import com.wemirr.platform.authority.configuration.integration.AbstractPreparedIntegrationAuthenticator;
import com.wemirr.platform.authority.configuration.integration.IntegrationAuthentication;
import com.wemirr.platform.authority.domain.entity.User;
import com.wemirr.platform.authority.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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
public class UsernamePasswordAuthenticator extends AbstractPreparedIntegrationAuthenticator {


    private static final String GRANT_TYPE = "grant_type";
    private static final String REFRESH_TOKEN = "refresh_token";
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private UserService userService;

    @Override
    public int getOrder() {
        return 100;
    }


    @Override
    public UserInfoDetails authenticate(IntegrationAuthentication integrationAuthentication) {
        String username = integrationAuthentication.getUsername();
        if (StringUtils.isBlank(username)) {
            throw new Auth2Exception("账号名不能为空");
        }
        final String grantType = integrationAuthentication.getAuthParameter(GRANT_TYPE);
        if (StringUtils.isBlank(grantType) || !StringUtils.equalsIgnoreCase(grantType, REFRESH_TOKEN)) {
            // 如果说是每次登陆都要清空以前的信息那么需要调用一下注销，这个注销的功能就是注销以前的token信息
        }
        final User user = Optional.ofNullable(this.userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username)))
                .orElseThrow(() -> CheckedException.notFound("账户不存在"));
        final UserInfoDetails info = new UserInfoDetails();
        info.setUserId(user.getId());
        info.setUsername(username);
        info.setRealName(user.getNickName());
        info.setNickName(user.getNickName());
        info.setMobile(user.getMobile());
        info.setEmail(user.getEmail());
        info.setDescription(user.getDescription());
        info.setSex(user.getSex());
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
        String authType = integrationAuthentication.getAuthType();
        return StringUtils.isEmpty(authType);
    }


}
