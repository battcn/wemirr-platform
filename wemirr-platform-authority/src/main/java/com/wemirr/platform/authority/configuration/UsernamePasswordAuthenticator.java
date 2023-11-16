package com.wemirr.platform.authority.configuration;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.security.configuration.server.support.integration.IntegrationAuthentication;
import com.wemirr.framework.security.configuration.server.support.integration.IntegrationAuthenticator;
import com.wemirr.framework.security.domain.UserInfoDetails;
import com.wemirr.framework.security.exception.OAuth2InvalidException;
import com.wemirr.platform.authority.domain.baseinfo.entity.Role;
import com.wemirr.platform.authority.domain.baseinfo.entity.User;
import com.wemirr.platform.authority.domain.tenant.entity.Tenant;
import com.wemirr.platform.authority.repository.baseinfo.ResourceMapper;
import com.wemirr.platform.authority.repository.baseinfo.RoleMapper;
import com.wemirr.platform.authority.repository.baseinfo.UserMapper;
import com.wemirr.platform.authority.repository.tenant.TenantMapper;
import com.wemirr.platform.authority.service.LoginLogService;
import com.wemirr.platform.authority.service.impl.DataScopeService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 默认登录处理
 *
 * @author Levin
 **/
@Slf4j
@Primary
@Component
public class UsernamePasswordAuthenticator implements IntegrationAuthenticator {

    @Resource
    private UserMapper userMapper;
    @Resource
    private TenantMapper tenantMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private ResourceMapper resourceMapper;
    @Resource
    private LoginLogService loginLogService;
    @Resource
    private DataScopeService dataScopeService;


    @Override
    public int getOrder() {
        return 100;
    }

    @Override
    public void prepare(IntegrationAuthentication authentication) {
        log.info("[用户密码登陆] - [{}]", JSON.toJSONString(authentication));
        String username = authentication.getUsername();
        String tenantCode = authentication.getTenantCode();
        if (StringUtils.isBlank(username)) {
            throw new OAuth2InvalidException("用户名不能为空");
        }
        if (StringUtils.isBlank(tenantCode)) {
            throw new OAuth2InvalidException("租户编码不能为空");
        }
    }


    @Override
    public UserInfoDetails authenticate(IntegrationAuthentication authentication) {
        String username = authentication.getUsername();
        String tenantCode = authentication.getTenantCode();
        final Tenant tenant = Optional.ofNullable(tenantMapper.selectOne(Wraps.<Tenant>lbQ().eq(Tenant::getCode, tenantCode)))
                .orElseThrow(() -> CheckedException.notFound("{0}租户不存在", tenantCode));
        if (tenant.getLocked()) {
            throw CheckedException.badRequest("租户已被禁用,请联系管理员");
        }
        final User user = Optional.ofNullable(this.userMapper.selectUserByTenantId(username, tenant.getId())).orElseThrow(() -> CheckedException.notFound("账户不存在"));
        final UserInfoDetails info = new UserInfoDetails();
        info.setTenantCode(tenantCode);
        info.setTenantName(tenant.getName());
        info.setTenantId(user.getTenantId());
        info.setUserId(user.getId());
        info.setUsername(username);
        info.setRealName(user.getNickName());
        info.setNickName(user.getNickName());
        info.setMobile(user.getMobile());
        info.setEmail(user.getEmail());
        info.setDescription(user.getDescription());
        info.setBirthday(user.getBirthday());
        info.setSex(Objects.isNull(user.getSex()) ? null : user.getSex().getValue());
        info.setEnabled(user.getStatus());
        info.setAvatar(user.getAvatar());
        info.setPassword(user.getPassword());
        final List<Role> roles = this.roleMapper.findRoleByUserId(user.getId());
        info.setRoles(roles.stream().map(Role::getCode).toList());
        final List<String> permissions = this.resourceMapper.queryPermissionByUserId(user.getId());
        info.setFuncPermissions(permissions);
        info.setDataPermission(dataScopeService.getDataPermissionById(user.getId(), user.getOrgId()));
        this.loginLogService.addLog(info);
        return info;
    }


    @Override
    public boolean support(IntegrationAuthentication integrationAuthentication) {
        return StrUtil.equals(integrationAuthentication.getLoginType(), loginType()) || loginType() == null;
    }
}
