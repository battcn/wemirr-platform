package com.wemirr.platform.workflow.handler;

import cn.hutool.core.collection.CollUtil;
import com.wemirr.framework.commons.security.AuthenticationContext;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.Lists;
import org.dromara.warm.flow.core.handler.PermissionHandler;
import org.dromara.warm.flow.core.utils.StreamUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 权限处理器 返回当前登录 以及当前登录人的权限信息范围
 *
 * @author battcn
 * @since 2025/5/22
 **/
@Component
@RequiredArgsConstructor
public class CustomPermissionHandler implements PermissionHandler {

    private final AuthenticationContext context;

    /**
     * 获取当前登陆人的权限集合范围 包含 userId role dep
     *
     * @return 权限范围
     */
    @Override
    public List<String> permissions() {
        List<String> permissionList = Lists.newArrayList();
        permissionList.add(String.valueOf(context.userId()));
        if (CollUtil.isNotEmpty(context.rolePermissionList())) {
            permissionList.addAll(StreamUtils.toList(context.rolePermissionList(), role -> "role:" + role));
        }
        return permissionList;
    }

    /**
     * 用于获取当前处理人的信息
     *
     * @return 当前处理人
     */
    @Override
    public String getHandler() {
        return String.valueOf(context.userId());
    }

    public String getHandlerName() {
        return context.nickName();
    }

    public String getTenantId() {
        return String.valueOf(context.tenantId());
    }

    public boolean isIgnoreAuth() {
        //todo 通过逻辑判断当前用户是否需要忽略权限.
        //1.可以通过是否为租户管理员 或者其他逻辑,暂存

        return false;
    }
}
