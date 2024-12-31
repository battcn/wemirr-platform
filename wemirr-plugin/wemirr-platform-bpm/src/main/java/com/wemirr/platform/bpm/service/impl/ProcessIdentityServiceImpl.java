package com.wemirr.platform.bpm.service.impl;

import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.platform.bpm.service.ProcessIdentityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.authorization.Authorization;
import org.camunda.bpm.engine.authorization.Permission;
import org.camunda.bpm.engine.authorization.Permissions;
import org.camunda.bpm.engine.authorization.Resources;
import org.camunda.bpm.engine.identity.Tenant;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.persistence.entity.AuthorizationEntity;
import org.camunda.bpm.engine.impl.persistence.entity.UserEntity;
import org.camunda.bpm.spring.boot.starter.property.CamundaBpmProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessIdentityServiceImpl implements ProcessIdentityService {

    private final AuthenticationContext context;
    private final IdentityService identityService;
    private final AuthorizationService authorizationService;
    private final CamundaBpmProperties camundaBpmProperties;

    public void createTenant(String tenantId, String tenantName) {
        long count = identityService.createTenantQuery().tenantId(tenantId).count();
        if (count > 0) {
            log.warn("[{}] 租户已存在", tenantName);
            return;
        }
        Tenant tenant = identityService.newTenant(tenantId);
        tenant.setName(tenantName);
        identityService.saveTenant(tenant);
    }

    public void setAuthentication() {
        var userId = context.userId().toString();
        var tenantId = context.tenantId().toString();
        createUser(userId, tenantId);
        createTenant(tenantId, context.tenantName());
        identityService.setAuthentication(userId, null, List.of(tenantId));
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public <T> T execute(Supplier<T> supplier) {
        try {
            setAuthentication();
            return supplier.get();
        } finally {
            identityService.clearAuthentication();
        }
    }


    public void createUser(String userId, String nickName) {
        final long count = this.identityService.createUserQuery().userId(userId).count();
        if (count > 0) {
            log.warn("用户已存在,跳过创建用户步骤");
            return;
        }
        try {
            final String adminId = camundaBpmProperties.getAdminUser().getId();
            identityService.setAuthentication(adminId, null);
            User user = new UserEntity();
            user.setId(userId);
            user.setFirstName(nickName);
            this.identityService.saveUser(user);
            final List<Authorization> list = this.authorizationService.createAuthorizationQuery().userIdIn(userId).list();
            if (list != null && !list.isEmpty()) {
                for (Authorization authorization : list) {
                    this.authorizationService.deleteAuthorization(authorization.getId());
                }
            }
            saveAuthorization(userId);
        } catch (Exception e) {
            log.error("操作异常", e);
        } finally {
            identityService.clearAuthentication();
        }
    }

    private static final List<Resources> DEFAULT_RESOURCES = List.of(Resources.TASK, Resources.PROCESS_DEFINITION, Resources.PROCESS_INSTANCE, Resources.DEPLOYMENT, Resources.HISTORIC_TASK);

    private void saveAuthorization(String bpmUserId) {
        DEFAULT_RESOURCES.stream().map(res -> {
            Authorization authorization = new AuthorizationEntity(1);
            authorization.setUserId(bpmUserId);
            authorization.setResourceId(Authorization.ANY);
            authorization.setResourceType(res.resourceType());
            authorization.setPermissions(new Permission[]{Permissions.ALL});
            return authorization;
        }).forEach(authorizationService::saveAuthorization);
    }
}
